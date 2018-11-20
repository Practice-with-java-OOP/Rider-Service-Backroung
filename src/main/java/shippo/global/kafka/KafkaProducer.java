package shippo.global.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.ConfigLoader;

import java.util.*;

public class KafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(org.apache.kafka.clients.producer.KafkaProducer.class);
    public static Producer producer;

    static {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", ConfigLoader.getInstance().getProperties().getProperty("kafka.brokerlist"));
        producer = new Producer(new ProducerConfig(properties));
    }

    public static void send(String eventString, String topic){
        byte[] data = eventString.getBytes();
        KeyedMessage<String, byte[]> msg = new KeyedMessage<>(topic, data);
        send(msg);
    }

    private static void send(KeyedMessage<String, byte[]> msg) {
        int count = 0;
        while (true) {
            try {
                producer.send(msg);
                return;
            } catch (Exception e) {
                LOG.error("Could not send msg now. Kafka exception. Retry: {} time", count++, e);
            }
        }
    }

    public static void main(String[] args) {

        Producer producer1 = KafkaProducer.producer;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hoalv");
        jsonObject.put("age", 22);
        JSONObject meta = new JSONObject();
        meta.put("type", "LOG");
        meta.put("action", "c");
        jsonObject.put("meta", meta);
        String obj = jsonObject.toString();
        producer1.send(new KeyedMessage("test",obj.getBytes()));
//        List<Integer> listMsg = new ArrayList<>();
//        for(int i = 0; i < 10; i++){
//            listMsg.add(i);
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int counter = 0;
//                while ( counter < listMsg.size()){
//                    try {
//                        Thread.sleep(200l);
//                        String message = listMsg.get(counter)+"";
//                        producer1.send(new KeyedMessage("test", message.getBytes()));
//                        counter++;
//                    }catch (Exception ex){
//                        LOG.error("Cant sent message {} ",ex);
//                    }
//                }
//            }
//        }).start();

        List<String> topics = Arrays.asList("test");

        Map<String, Integer> counterMap = new HashMap<>();

        int counter1 = 0;
        counterMap.put("test11", counter1);

        new SingleConsumer("192.168.2.253:9092","test",topics){
            public void processMsg(ConsumerRecord<byte[], byte[]> record) {
                String msgReceive = new String(record.value());
                LOG.info(msgReceive);
            }

        };

    }
}
