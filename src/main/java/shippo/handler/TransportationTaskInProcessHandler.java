package shippo.handler;

import com.avaje.ebean.EbeanServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.CRUD;
import shippo.global.PostgressDbConf;
import shippo.global.ProtobufEventAbstractHandler;
import shippo.global.entities.rider_service.RiderShift;
import shippo.global.entities.rider_service.RiderShiftComment;
import shippo.global.entities.rider_service.TransportationTask;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransportationTaskInProcessHandler extends ProtobufEventAbstractHandler<TransportationTask> {
    protected EbeanServer server = PostgressDbConf.getRiderDb();
    protected BlockingQueue<ConsumerRecord<byte[], byte[]>> msgQueue = new LinkedBlockingQueue<>(1000);

    public TransportationTaskInProcessHandler() {
        LOG = LoggerFactory.getLogger("TransportationTaskInProcessHandler");
        typeClass = TransportationTask.class;
        starHandle();
    }

    private void starHandle() {
        Thread thread = new Thread() {
            public void run() {
                ConsumerRecord<byte[], byte[]> record = null;
                while (true) {
                    try {
                        record = msgQueue.take();
                        LOG.info("Receive record: {}", record);
                        parseEvent(record);
                    } catch (InterruptedException e) {
                        LOG.error("Error while get msg from queue: {}", e);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public void putMsg(ConsumerRecord<byte[], byte[]> record) {
        try {
            msgQueue.put(record);
            LOG.info("Put record {} to ActionTaskHandle", record);
        } catch (InterruptedException e) {
            LOG.error("Put record fail {}", e);
        }
    }

    @Override
    protected void parseEvent(ConsumerRecord<byte[], byte[]> record) {
        super.parseEvent(record);
    }

    @Override
    protected void handleEvent(ConsumerRecord<byte[], byte[]> record) {

        if (!isStateFromNewtoInProcess()) return;
        if (after.getType().equals("PICKUP")) return;

        RiderShift riderShift = server.find(RiderShift.class)
                .where()
                .and()
                .eq("id", after.getRiderShiftId())
                .findUnique();

        if (riderShift == null) return;
        if (!isBeforeStateInProcess(riderShift)) return;

        riderShift.setState("IN_PROCESS");
        try {
            CRUD.update(riderShift, server);
            LOG.info("update riderShift {} successfully", riderShift);
        } catch (Exception e) {
            LOG.error("Can't update riderShift {}", riderShift);
        }

        addNewRiderShiftComment();
    }

    @Override
    protected boolean checkEventObject(JSONObject eventObject) {
        crudOperation = getOperation(eventObject);
        switch (crudOperation) {
            case 'c': {
                return false;
            }
            case 'u': {
                JSONObject data = new JSONObject(String.valueOf(eventObject.get("data")));
                before = mappingEvent((JSONObject) data.get("before"));
                after = mappingEvent((JSONObject) data.get("after"));
                if (after == null) return false;
                if (before == null) return false;
                return true;
            }
            case 'd': {
                return false;
            }
            case 'r':
                return false;
            default: {
                LOG.error("Event object error " + eventObject.toString());
                return false;
            }
        }
    }

    @Override
    protected boolean needSync() {
        return true;
    }

    private boolean isBeforeStateInProcess(RiderShift riderShift) {
        return riderShift.getState().equals("ASSIGNED") || riderShift.getState().equals("DRAFT");
    }

    private boolean isStateFromNewtoInProcess() {
        return before.getState().equals("NEW") && after.getState().equals("IN_PROCESS");
    }

    private void addNewRiderShiftComment() {
        RiderShiftComment riderShiftComment = new RiderShiftComment();
        riderShiftComment.setContextType("ACTIVITY");
        riderShiftComment.setScope("INTERNAL");
        riderShiftComment.setRiderShiftId(after.getRiderShiftId());
        riderShiftComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        riderShiftComment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        riderShiftComment.setVersion(0);
        riderShiftComment.setContext(_buildJsonContent());

        try {
            CRUD.insert(riderShiftComment, server);
            LOG.info("Created riderShiftComment {} successfully", riderShiftComment);
        } catch (Exception e) {
            LOG.error("Can't created riderShiftComment {}", riderShiftComment);
        }
    }

    private Object _buildJsonContent() {
        Map<String, Object> content = new HashMap<String, Object>();
        Map<String, Object> _attributes = new HashMap<String, Object>();

        _attributes.put("message", "Ca chuyển sang đang thực hiện");

        content.put("_type", "ACTIVITY");
        content.put("_attributes", _attributes);
        return content;
    }
}
