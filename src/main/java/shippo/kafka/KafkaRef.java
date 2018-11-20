package shippo.kafka;

import shippo.global.ConfigLoader;
import shippo.global.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaRef {
    public static Map<String, String> topicMapper = new HashMap();
    static Properties properties = ConfigLoader.getInstance().getProperties();
    final public static String TRANSPORTATION_TASK_TOPIC = properties.getProperty("old.topic.transportation_task");
    final public static String USER_AUTH_TOPIC =properties.getProperty("auth.topic.users");
    final public static String USER_AUTH_FAIL_TOPIC = properties.getProperty("auth.fail.topic.user");
    final public static String PICKUP_REQUEST_ORDER_TOPIC = properties.getProperty("operation.topic.pickup_request_order");
    final public static String TRANSPORTATION_TASK_IN_PROCESS_TOPIC = properties.getProperty("transportation_task.status.in_process");



    static{

        topicMapper.put(TRANSPORTATION_TASK_TOPIC, Constants.TRANSPORTATION_TASK_HANDLER);
        topicMapper.put(USER_AUTH_TOPIC,Constants.USERS_AUTH_HANDLER);
        topicMapper.put(USER_AUTH_FAIL_TOPIC,Constants.USER_AUTH_FAIL_HANDLER);
        topicMapper.put(PICKUP_REQUEST_ORDER_TOPIC, Constants.PICKUP_REQUEST_ORDER_HANDLER);
        topicMapper.put(TRANSPORTATION_TASK_IN_PROCESS_TOPIC, Constants.TRANSPORTATION_TASK_IN_PROCESS_HANDLER);
    }
    public static String getActorFromTopic(String topic){
        return topicMapper.get(topic);
    }
}
