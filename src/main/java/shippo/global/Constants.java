package shippo.global;

import java.util.Properties;

public class Constants {
    
    // actor
    // Actor name
    public static final String TRANSPORTATION_TASK_HANDLER = "transportation_task_handler";
    public static final String USERS_AUTH_HANDLER = "users_auth_handler";
    public static final String USER_AUTH_FAIL_HANDLER = "user_auth_fail_handler";

    public static final String PICKUP_REQUEST_ORDER_HANDLER = "pickup_request_order_handler";
    public static final String TRANSPORTATION_TASK_IN_PROCESS_HANDLER = "transportation_task_in_process_handler";


    static Properties properties = ConfigLoader.getInstance().getProperties();
    final public static String TOOKAN_TASK_TOPIC = properties.getProperty("kafka.tookan_task.topic");

}
