package shippo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.Constants;
import shippo.global.ProtobufEventAbstractHandler;

import java.util.HashMap;
import java.util.Map;

public class ProtobufEventHandlerManager<R> {
    private static Logger LOG = LoggerFactory.getLogger(ProtobufEventHandlerManager.class);

    private static Map<String, ProtobufEventAbstractHandler> handlerMap = new HashMap<>();

    public static ProtobufEventAbstractHandler getHandler(String name) {
        if (!handlerMap.containsKey(name)) {
            createHandler(name);
        }
        return handlerMap.get(name);
    }

    public static void createHandler(String handlerName) {
        switch (handlerName) {
            case Constants.USER_AUTH_FAIL_HANDLER: {
                handlerMap.put(handlerName, new UserAuthFailEventHandler());
                LOG.info("Init akka handler {}", handlerName);
            }

            case Constants.TRANSPORTATION_TASK_IN_PROCESS_HANDLER: {
                handlerMap.put(handlerName, new TransportationTaskInProcessHandler());
                LOG.info("Init akka handler {}", handlerName);
            }

            default: {
                LOG.warn("Handler {} haven't been define yet !");
            }
        }
    }
}
