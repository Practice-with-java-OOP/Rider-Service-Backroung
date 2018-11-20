package shippo.main;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.ConfigLoader;
import shippo.global.DbzEventAbstractHandler;
import shippo.global.ProtobufEventAbstractHandler;
import shippo.global.entities.protobuf.Message;
import shippo.global.kafka.SingleConsumer;
import shippo.handler.HandlerManager;
import shippo.handler.ProtobufEventHandlerManager;
import shippo.kafka.KafkaRef;
import shippo.protobuf.parser.MessageParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RiderBackgroundHandlerApp {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("RiderBackgroundHandlerApp");

        ExecutorService executer = Executors.newFixedThreadPool(5);
        executer.execute(new Runnable() {
            @Override
            public void run() {
                Properties properties = ConfigLoader.getInstance().getProperties();
                String brokerList = properties.getProperty("kafka.brokerlist");
                String[] topics = properties.getProperty("kafka.rider.background.topic").split(",");
                List<String> listTopic = new ArrayList();
                for(String topic: topics){
                    listTopic.add(topic.trim());
                }
                String kafkaGroup = properties.getProperty("kafka.rider.background.group");
                new SingleConsumer(brokerList, kafkaGroup, listTopic) {
                    @Override
                    protected void processMsg(ConsumerRecord<byte[], byte[]> record) {
                        String eventString = new String(record.value());
                        JSONObject eventObject;
                        try {
                            eventObject = new JSONObject(eventString);
                        }catch (Exception e){
                            final MessageParser instance = new MessageParser();
                            final Message copiedMessage = instance.instantiateMessageFromBinary(record.value());
                            eventObject = new JSONObject(copiedMessage);
                        }
                        if(eventObject.has("payload")){
                            DbzEventAbstractHandler handler = HandlerManager.getHandler(KafkaRef.topicMapper.get(record.topic()));
                            handler.putMsg(record);
                        }else {
                            ProtobufEventAbstractHandler handler = ProtobufEventHandlerManager.getHandler(KafkaRef.topicMapper.get(record.topic()));
                            handler.putMsg(record);
                        }

                    }
                };
            }
        });
    }
}
