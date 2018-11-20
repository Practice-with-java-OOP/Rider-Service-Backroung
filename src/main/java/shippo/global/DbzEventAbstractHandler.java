package shippo.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class DbzEventAbstractHandler<T> {
    protected Logger LOG = LoggerFactory.getLogger(DbzEventAbstractHandler.class);
    protected char crudOperation;
    protected String eventString;
    protected T before;
    protected T after;
    protected Class<T> typeClass;


    public abstract void putMsg(ConsumerRecord<byte[], byte[]> record);

    protected void parseEvent(ConsumerRecord<byte[], byte[]> record) {
        eventString = new String(record.value());
        JSONObject payload = getPayload();
        LOG.info("Payload {}",payload);
        // Neu payload = null bo qua event
        if (payload == null) return;
        boolean payloadValid = checkPayload(payload);
        if(!payloadValid) return;
        handleEvent();
    }

    protected abstract void handleEvent();

    protected boolean checkPayload(JSONObject payload) {
        crudOperation = getOperation(payload);
        switch (crudOperation) {
            case 'c': {
                after = mappingEvent((JSONObject)payload.get("after"));
                if (after == null) return false;
                return true;
            }
            case 'u': {
                return false;
            }
            case 'd': {
                return false;
            }
            case 'r':
                return false;
            default: {
                LOG.error("payload error " + payload.toString());
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

    public char getOperation(JSONObject payload) {
        String type = payload.getString("op");
        return type.charAt(0);
    }

    protected JSONObject getPayload() {
        JSONObject event = new JSONObject(eventString);

        if (event.isNull("payload")) {
            LOG.info("Payload is nullllllllllllllllllllllllllll!");
            return null;
        }

        JSONObject payload = (JSONObject) event.get("payload");
        return payload;
    }

    protected void logError(Logger logger,String message){
        logger.error(message);
    }


}
