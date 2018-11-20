package shippo.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.entities.protobuf.Message;
import shippo.protobuf.parser.MessageParser;

import java.io.IOException;

public abstract class ProtobufEventAbstractHandler<T> {
    protected Logger LOG = LoggerFactory.getLogger(ProtobufEventAbstractHandler.class);
    protected char crudOperation;
    protected String eventString;
    protected T before;
    protected T after;
    protected Class<T> typeClass;
    protected final MessageParser instance = new MessageParser();


    public abstract void putMsg(ConsumerRecord<byte[], byte[]> record);

    protected void parseEvent(ConsumerRecord<byte[], byte[]> record) {
        final Message copiedMessage = instance.instantiateMessageFromBinary(record.value());
        JSONObject eventObject = new JSONObject(copiedMessage);
        LOG.info("Event Object {}",eventObject);
        // Neu payload = null bo qua event
        if (eventObject == null) return;
        boolean objectValid = checkEventObject(eventObject);
        if(!objectValid) return;
        if(!needSync()) return;
        handleEvent(record);
    }

    protected abstract void handleEvent(ConsumerRecord<byte[], byte[]> record);

    protected abstract boolean needSync();

    protected boolean isLoop(){

        JSONObject jsonObject = new JSONObject(after);
        //chỉ xử lý event từ shippo mới
        if(jsonObject.getInt("version") % 2 == 0) return false;
        else return true;

    }

    protected boolean checkEventObject(JSONObject eventObject) {
        crudOperation = getOperation(eventObject);
        String type = getType(eventObject);
        switch (crudOperation) {
            case 'c': {

                JSONObject data = new JSONObject(String.valueOf(eventObject.get("data")));
                after = mappingEvent(data);
                if (after == null) return false;
                return true;
            }
            case 'u': {
                JSONObject data = new JSONObject(String.valueOf(eventObject.get("data")));
                after = mappingEvent(data);
                if (after == null ) return false;
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

    protected T mappingEvent(JSONObject obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(obj.toString().equals("null")) return null;
            return mapper.readValue(obj.toString(),typeClass);
        } catch (IOException e) {
            LOG.error(obj.toString() + "\n" + e.toString());
            return null;
        }
    }

    public char getOperation(JSONObject eventObject) {
        String type = eventObject.getString("action");
        return type.charAt(0);
    }

    public String getType(JSONObject eventObject) {
        String type = eventObject.getString("type");
        return type;
    }

    protected void logError(Logger logger,String message){
        logger.error(message);
    }


}
