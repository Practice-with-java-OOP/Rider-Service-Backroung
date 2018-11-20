package shippo.handler;

import com.avaje.ebean.EbeanServer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.Constants;
import shippo.global.DbzEventAbstractHandler;
import shippo.global.PostgressDbConf;
import shippo.global.entities.rider_service.TransportationTask;
import shippo.global.kafka.KafkaProducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransportationTaskHandler extends DbzEventAbstractHandler<TransportationTask> {
    EbeanServer server = PostgressDbConf.getDeliveryDb();

    protected BlockingQueue<ConsumerRecord<byte[], byte[]>> msgQueue = new LinkedBlockingQueue<>(1000);

    public TransportationTaskHandler() {
        LOG = LoggerFactory.getLogger("TransportationTaskHandler");
        typeClass = TransportationTask.class;
        startHandle();
    }

    @Override
    public void putMsg(ConsumerRecord<byte[], byte[]> record) {
        try {
            msgQueue.put(record);
            LOG.info("Put record {} to TransportationTaskHandler", record);

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
        JSONObject payload = getPayload();

        JSONObject event2Tookan = new JSONObject();
        event2Tookan.put("action", "c");
        event2Tookan.put("type","transportation_tasks");
        event2Tookan.put("data", payload.get("after"));

        Producer producer = KafkaProducer.producer;
        String event = event2Tookan.toString();
        producer.send(new KeyedMessage(Constants.TOOKAN_TASK_TOPIC, event.getBytes()));

        LOG.info("Send event {} to tookan-sync done!", event);
    }

}
