package shippo;

import com.google.protobuf.InvalidProtocolBufferException;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import shippo.global.PostgressDbConf;
import shippo.global.entities.protobuf.Message;
import shippo.global.entities.rider_service.TransportationTask;
import shippo.global.kafka.KafkaProducer;
import shippo.protobuf.protos.MessageProtos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Producer producer1 = KafkaProducer.producer;

        final Test instance = new Test();
        final Message msg = instance.generateMessage();
        final MessageProtos.Message message
                = protos.MessageProtos.Message.newBuilder()
                .setType(msg.getType())
                .setAction(msg.getAction())
                .setState(msg.getState())
                .setData(msg.getData())
                .build();
        final byte[] binaryMsg = message.toByteArray();

        producer1.send(new KeyedMessage("auth.create.users.fail",binaryMsg));

        List<String> topics = Arrays.asList("test.protobuf");

        Map<String, Integer> counterMap = new HashMap<>();

        int counter1 = 0;
        counterMap.put("test11", counter1);

//        new SingleConsumer("192.168.2.253:9092","test",topics){
//            public void processMsg(ConsumerRecord<byte[], byte[]> record) {
//
//                System.out.println(new String(record.topic()));
//                System.out.println(new String(record.value()));
//                final Message copiedMessage = instance.instantiateMessageFromBinary(record.value());
//                System.out.println("BEFORE Album (" + System.identityHashCode(msg) + "): " + msg);
//                System.out.println("AFTER Album (" + System.identityHashCode(copiedMessage) + "): " + String.valueOf(copiedMessage));
//                System.out.println(new JSONObject(copiedMessage).get("data"));
//            }
////
////        };

        TransportationTask task = PostgressDbConf.getRiderDb().find(TransportationTask.class)
                .where()
                .and()
                .eq("id", 63994)
                .endAnd()
                .findUnique();
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(task);
//        JSONObject taskJson = new JSONObject(jsonString);

//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "";
//        try {
//            jsonInString = mapper.writeValueAsString(task);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//
//        JSONObject event2Tookan = new JSONObject();
//        event2Tookan.put("action", "u");
//        event2Tookan.put("type", "transportation_tasks");
//        event2Tookan.put("data", new JSONObject(jsonInString));
//
//        System.out.println(event2Tookan);
    }

    public Message generateMessage()
    {
        return new Message("Users", "u", "FAIL",
                "{\"id\" : 7,\"username\" : \"luuhieu\",\"email\" : \"luutronghieu@admin.com\",\"section\" : \"RIDER\",\"state\" : \"ACTIVE\",\"is_email_verified\" : false,\"is_required_change_pass\" : false,\"password\" : \"$2a$10$bUkIE8S8Jgshre4XUAubPuEcy3QMAVpE7apuQ4.bDulPtw4tLK8De\",\"change_pass_at\" : null,\"verified_email_at\" : null,\"created_at\" : \"2016-11-25T08:26:19Z\",\"updated_at\" : \"2018-08-29T03:46:25Z\",\"version\" : 3,\"level\" : \"ADMIN\"}");
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
