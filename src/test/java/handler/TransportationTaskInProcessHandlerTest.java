package handler;

import com.avaje.ebean.EbeanServer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shippo.global.*;
import shippo.global.entities.protobuf.Message;
import shippo.global.entities.rider_service.RiderShift;
import shippo.global.entities.rider_service.RiderShiftComment;
import shippo.global.entities.rider_service.TransportationTask;
import shippo.global.kafka.SingleConsumer;
import shippo.kafka.KafkaRef;
import shippo.protobuf.parser.MessageParser;
import shippo.protobuf.protos.MessageProtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class TransportationTaskInProcessHandlerTest {

    private static TransportationTask transportationTask;
    private static RiderShift riderShift;
    private static RiderShiftComment riderShiftComment;
    private static EbeanServer server = PostgressDbConf.getRiderDb();
    private static String beforeState;
    private static Producer producer;

    @AfterClass
    public static void clear() {
//        System.out.println("run at " + System.currentTimeMillis());
    }


    @Test
    public void checkRiderShiftTest() {
        assertNotNull(riderShift);
    }

    @Test
    public void isBeforeStateInProcess() {
        if (riderShift.getState().equals("ASSIGNED") || riderShift.getState().equals("DRAFT")) {
            assertEquals("IN_PROCESS", transportationTask.getState());
        }
    }


    @Test
    public void testSetupStateRiderShift() {
        System.out.println("checkDeliveryTest -----------");
        if (transportationTask.getType().equals("DELIVER")) {
            if (riderShift != null) {
                if (riderShift.getState().equals("ASSIGNED") || riderShift.getState().equals("DRAFT")) {
                    assertEquals("IN_PROCESS", riderShift.getState());
                }
            } else {
                assertNull(riderShiftComment);
            }
        } else {
            assertNull(riderShiftComment);
        }
        assertEquals("DELIVER", transportationTask.getType());
    }

    @Test
    public void testMessageRiderShiftComment() {
        if (riderShiftComment != null) {
            JSONObject data = new JSONObject(riderShiftComment);
            assertEquals("{\"_type\":\"ACTIVITY\",\"_attributes\":{\"message\":\"Ca chuyển sang đang thực hiện\"}}", data.get("context").toString());
        }
    }

    @BeforeClass
    public static void setup() {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", ConfigLoader.getInstance().getProperties().getProperty("kafka.brokerlist"));
        producer = new Producer(new ProducerConfig(properties));

        final Message msg = new Message("TransportationTask", "u", "", "{\\\"before\\\":{\\\"id\\\":115942,\\\"created_by\\\":1,\\\"merchant_user_id\\\":3045,\\\"description\\\":\\\"\\\\r\\\\n8032743601: Nothing burns like the cold. \\\\r\\\\nHàng hóa: 14 Aerodynamic Wooden Bottle\\\\n10 Small Cotton Computer\\\\n\\\",\\\"assignee\\\":null,\\\"assignee_type\\\":\\\"Users\\\",\\\"state\\\":\\\"NEW\\\",\\\"note\\\":\\\"\\\",\\\"type\\\":\\\"DELIVER\\\",\\\"pickup_detail\\\":null,\\\"pick_location_ids_path\\\":null,\\\"pickup_contact_name\\\":null,\\\"pickup_contact_phone\\\":null,\\\"pickup_full_address\\\":null,\\\"has_pickup\\\":null,\\\"pickup_latitude\\\":null,\\\"pickup_longitude\\\":null,\\\"deliver_detail\\\":\\\"Số 203 đường Lê Duẩn\\\",\\\"deliver_location_ids_path\\\":\\\"9.18\\\",\\\"deliver_contact_name\\\":\\\"Brenett\\\",\\\"deliver_contact_phone\\\":\\\"07403 631155\\\",\\\"deliver_full_address\\\":\\\"Số 203 đường Lê Duẩn, Long Biên, Hà Nội\\\",\\\"has_delivery\\\":null,\\\"deliver_latitude\\\":null,\\\"deliver_longitude\\\":null,\\\"is_last_mile_delivery\\\":true,\\\"fail_reason\\\":null,\\\"reason_code\\\":[],\\\"tookan_job_id\\\":null,\\\"object_type\\\":\\\"CustomerDeliveryOrder\\\",\\\"object_id\\\":65756,\\\"object_code\\\":\\\"8032743601\\\",\\\"cod\\\":0,\\\"real_cod\\\":null,\\\"task_deadline\\\":null,\\\"rider_shift_id\\\":8883,\\\"request_id\\\":null,\\\"internal_reason_fail\\\":null,\\\"recipient_pay_courier_fee\\\":\\\"SENDER_PAY_FOR_DELIVERY\\\",\\\"tracking_link\\\":null,\\\"metadata\\\":null,\\\"pickup_before\\\":null,\\\"deliver_before\\\":null,\\\"created_at\\\":\\\"2017-12-27T07:37:05.880Z\\\",\\\"updated_at\\\":\\\"2018-11-19T09:16:45.088Z\\\",\\\"assigned_at\\\":null,\\\"accepted_at\\\":null,\\\"started_at\\\":null,\\\"in_progress_at\\\":\\\"2018-11-19T09:16:45.050Z\\\",\\\"cancelled_at\\\":null,\\\"failed_at\\\":null,\\\"decline_at\\\":null,\\\"deleted_at\\\":null,\\\"success_at\\\":null,\\\"version\\\":6,\\\"last_sync_at\\\":null},\\\"after\\\":{\\\"id\\\":115942,\\\"created_by\\\":1,\\\"merchant_user_id\\\":3045,\\\"description\\\":\\\"\\\\r\\\\n8032743601: Nothing burns like the cold. \\\\r\\\\nHàng hóa: 14 Aerodynamic Wooden Bottle\\\\n10 Small Cotton Computer\\\\n\\\",\\\"assignee\\\":null,\\\"assignee_type\\\":\\\"Users\\\",\\\"state\\\":\\\"IN_PROCESS\\\",\\\"note\\\":\\\"\\\",\\\"type\\\":\\\"DELIVER\\\",\\\"pickup_detail\\\":null,\\\"pick_location_ids_path\\\":null,\\\"pickup_contact_name\\\":null,\\\"pickup_contact_phone\\\":null,\\\"pickup_full_address\\\":null,\\\"has_pickup\\\":null,\\\"pickup_latitude\\\":null,\\\"pickup_longitude\\\":null,\\\"deliver_detail\\\":\\\"Số 203 đường Lê Duẩn\\\",\\\"deliver_location_ids_path\\\":\\\"9.18\\\",\\\"deliver_contact_name\\\":\\\"Brenett\\\",\\\"deliver_contact_phone\\\":\\\"07403 631155\\\",\\\"deliver_full_address\\\":\\\"Số 203 đường Lê Duẩn, Long Biên, Hà Nội\\\",\\\"has_delivery\\\":null,\\\"deliver_latitude\\\":null,\\\"deliver_longitude\\\":null,\\\"is_last_mile_delivery\\\":true,\\\"fail_reason\\\":null,\\\"reason_code\\\":[],\\\"tookan_job_id\\\":null,\\\"object_type\\\":\\\"CustomerDeliveryOrder\\\",\\\"object_id\\\":65756,\\\"object_code\\\":\\\"8032743601\\\",\\\"cod\\\":0,\\\"real_cod\\\":null,\\\"task_deadline\\\":null,\\\"rider_shift_id\\\":8883,\\\"request_id\\\":null,\\\"internal_reason_fail\\\":null,\\\"recipient_pay_courier_fee\\\":\\\"SENDER_PAY_FOR_DELIVERY\\\",\\\"tracking_link\\\":null,\\\"metadata\\\":null,\\\"pickup_before\\\":null,\\\"deliver_before\\\":null,\\\"created_at\\\":\\\"2017-12-27T07:37:05.880Z\\\",\\\"updated_at\\\":\\\"2018-11-20T01:58:37.000Z\\\",\\\"assigned_at\\\":null,\\\"accepted_at\\\":null,\\\"started_at\\\":null,\\\"in_progress_at\\\":\\\"2018-11-20T01:58:36.793Z\\\",\\\"cancelled_at\\\":null,\\\"failed_at\\\":null,\\\"decline_at\\\":null,\\\"deleted_at\\\":null,\\\"success_at\\\":null,\\\"version\\\":8,\\\"last_sync_at\\\":null}}");
        final MessageProtos.Message message = protos.MessageProtos.Message.newBuilder()
                .setType(msg.getType())
                .setAction(msg.getAction())
                .setState(msg.getState())
                .setData(msg.getData())
                .build();

        final byte[] binaryMsg = message.toByteArray();
        producer.send(new KeyedMessage("shippo.rider_service.topic.task.switch.status", binaryMsg));

        transportationTask = server.find(TransportationTask.class)
                .where()
                .and()
                .eq("id", 115942)
                .findUnique();


        // init handler
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Properties properties = ConfigLoader.getInstance().getProperties();
                String brokerList = properties.getProperty("kafka.brokerlist");
                String[] topics = properties.getProperty("kafka.rider.background.topic").split(",");
                List<String> listTopic = new ArrayList();
                for (String topic : topics) {
                    listTopic.add(topic.trim());
                }
                String kafkaGroup = properties.getProperty("kafka.rider.background.group");
                new SingleConsumer(brokerList, kafkaGroup, listTopic) {
                    @Override
                    protected void processMsg(ConsumerRecord<byte[], byte[]> record) {
                        System.out.println("Start consumer msg -------------------------");
                        String eventString = new String(record.value());
                        JSONObject eventObject;
                        try {
                            eventObject = new JSONObject(eventString);
                        } catch (Exception e) {
                            final MessageParser instance = new MessageParser();
                            final Message copiedMessage = instance.instantiateMessageFromBinary(record.value());
                            eventObject = new JSONObject(copiedMessage);
                        }
                        if (eventObject.has("payload")) {
                            DbzEventAbstractHandler handler = HandlerManager.getHandler(KafkaRef.topicMapper.get(record.topic()));
                            handler.putMsg(record);
                        } else {
                            System.out.println("receive msg: --------------------- " + record);
                            ProtobufEventAbstractHandler handler = ProtobufEventHandlerManager.getHandler(KafkaRef.topicMapper.get(record.topic()));
                            handler.putMsg(record);
                        }

                    }
                };
            }
        });
        thread.start();

        riderShift = server.find(RiderShift.class)
                .where()
                .and()
                .eq("id", transportationTask.getRiderShiftId())
                .findUnique();

        riderShiftComment = server.find(RiderShiftComment.class)
                .where()
                .and()
                .eq("rider_shift_id", riderShift.getId())
                .findUnique();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
