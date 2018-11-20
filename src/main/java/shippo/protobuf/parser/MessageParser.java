package shippo.protobuf.parser;
import com.google.protobuf.InvalidProtocolBufferException;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import shippo.global.entities.protobuf.Message;
import shippo.global.kafka.KafkaProducer;
import shippo.global.kafka.SingleConsumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageParser {
    public static void main(String[] args) {
        Producer producer1 = KafkaProducer.producer;

        final MessageParser instance = new MessageParser();
        final Message msg = instance.generateMessage();
        final protos.MessageProtos.Message message
                = protos.MessageProtos.Message.newBuilder()
                .setType(msg.getType())
                .setAction(msg.getAction())
                .setState(msg.getState())
                .setData(msg.getData())
                .build();
        final byte[] binaryMsg = message.toByteArray();

        producer1.send(new KeyedMessage("test",binaryMsg));

        List<String> topics = Arrays.asList("test");

        Map<String, Integer> counterMap = new HashMap<>();

        int counter1 = 0;
        counterMap.put("test11", counter1);

        new SingleConsumer("192.168.2.253:9092","test",topics){
            public void processMsg(ConsumerRecord<byte[], byte[]> record) {

                System.out.println(new String(record.topic()));
                System.out.println(new String(record.value()));
                final Message copiedMessage = instance.instantiateMessageFromBinary(record.value());
                System.out.println("BEFORE Album (" + System.identityHashCode(msg) + "): " + msg);
                System.out.println("AFTER Album (" + System.identityHashCode(copiedMessage) + "): " + String.valueOf(copiedMessage));
                System.out.println(new JSONObject(copiedMessage));
            }

        };
    }

    public Message generateMessage()
    {
        return new Message("Users", "c", "FAIL", "{\"id\":1, \"name\":\"hoalv\"}");
    }

    public Message instantiateMessageFromBinary(final byte[] binaryMessage)
    {
        Message Message = null;
        try
        {
            final protos.MessageProtos.Message copiedMessageProtos = protos.MessageProtos.Message.parseFrom(binaryMessage);
            Message = new Message(
                    copiedMessageProtos.getType(), copiedMessageProtos.getAction()
                    , copiedMessageProtos.getState(), copiedMessageProtos.getData()
            );

        }
        catch (InvalidProtocolBufferException ipbe)
        {
            System.out.println("ERROR: Unable to instantiate MessageProtos.Message instance from provided binary data - "
                    + ipbe);
        }
        return Message;
    }
}
