package shippo.handler;

import com.avaje.ebean.EbeanServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.CRUD;
import shippo.global.Constants;
import shippo.global.DbzEventAbstractHandler;
import shippo.global.PostgressDbConf;
import shippo.global.entities.operation_service.PickupRequestOrder;
import shippo.global.entities.rider_service.TransportationTask;
import shippo.global.kafka.KafkaProducer;
import shippo.global.services.TransportTaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PickupRequestOrderHandler extends DbzEventAbstractHandler<PickupRequestOrder> {
    EbeanServer server = PostgressDbConf.getDeliveryDb();

    protected BlockingQueue<ConsumerRecord<byte[], byte[]>> msgQueue = new LinkedBlockingQueue<>(1000);

    public PickupRequestOrderHandler() {
        LOG = LoggerFactory.getLogger("PickupRequestOrderHandler");
        typeClass = PickupRequestOrder.class;
        startHandle();
    }

    @Override
    public void putMsg(ConsumerRecord<byte[], byte[]> record) {
        try {
            msgQueue.put(record);
            LOG.info("Put record {} to PickupRequestOrderHandler", record);

        } catch (InterruptedException e) {
            LOG.error("Put record fail {}", e);
        }
    }

    public void startHandle(){
        Thread thread = new Thread(){
            public void run(){
                ConsumerRecord<byte[], byte[]> record = null;
                while (true) {
                    try {
                        record = msgQueue.take();
                        LOG.info("Receive msg: {}",record);
                        parseEvent(record);
                    } catch (InterruptedException e) {
                        LOG.error("Error while get msg from queue: {}",e);
                    }

                }
            }
        };

        thread.start();
    }


    public void parseEvent(ConsumerRecord<byte[], byte[]> record){
        super.parseEvent(record);
    }


    @Override
    protected void handleEvent() {
        if (null == this.after) return;

        EbeanServer riderServer = PostgressDbConf.getRiderDb();
        List<String> processState = new ArrayList<>();
        processState.add(TransportTaskService.STATE_NEW);
//        processState.add(TransportTaskService.STATE_IN_PROCESS);
        List<TransportationTask> tasks = riderServer.find(TransportationTask.class)
                .where()
                .and()
                .eq("objectId", this.after.getRequestId())
                .eq("type", TransportTaskService.TYPE_PICKUP)
                .in("state", processState)
                .endAnd()
                .findList();

        if (null == tasks){
            LOG.debug("Cann't find task with objectId {}, type {}, state {}", after.getRequestId(), TransportTaskService.TYPE_PICKUP, processState.toString());
            return;
        }

        for (TransportationTask task: tasks) {
            String description = TransportTaskService.genPickupNote(task);
            if (!task.getDescription().equals(description)) {
                task.setDescription(description);
                try {
                    LOG.info("update transportation task {} with description {}", task.getId(), task.getDescription());
                    CRUD.update(task, riderServer);
                    LOG.info("update transportation task success!");
                    sendEventUpdateTask2TookanSync(task);
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendEventUpdateTask2TookanSync(TransportationTask task) {

        Integer newVersion = task.getVersion() % 2 == 0 ? task.getVersion() + 2 : task.getVersion() + 1;
        task.setVersion(newVersion);
        ObjectMapper mapper = new ObjectMapper();
        String jsonTask = "";
        try {
            jsonTask = mapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JSONObject event2Tookan = new JSONObject();
        event2Tookan.put("action", "u");
        event2Tookan.put("type","transportation_tasks");
        event2Tookan.put("data", new JSONObject(jsonTask));

        Producer producer = KafkaProducer.producer;
        String event = event2Tookan.toString();
        producer.send(new KeyedMessage(Constants.TOOKAN_TASK_TOPIC, event.getBytes()));
        LOG.info("Send event update task to tookan sync {}", event);

    }

}

