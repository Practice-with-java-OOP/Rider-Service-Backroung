package shippo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.Constants;
import shippo.global.DbzEventAbstractHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerManager {
    private static Logger LOG = LoggerFactory.getLogger(HandlerManager.class);

    private static Map<String, DbzEventAbstractHandler> handlerMap = new HashMap();

    public static DbzEventAbstractHandler getHandler(String name){
        if(!handlerMap.containsKey(name)){
            createHandler(name);
        }
        return handlerMap.get(name);
    }

    private static void createHandler(String handlerName){
        switch (handlerName){
            case Constants.TRANSPORTATION_TASK_HANDLER:{
                handlerMap.put(handlerName, new TransportationTaskHandler());
                LOG.info("Init akka handler {}", handlerName);
                break;
            }
            case Constants.USERS_AUTH_HANDLER:{
                handlerMap.put(handlerName,new UsersAuthEventHandler());
                LOG.info("Init akka handler {}", handlerName);
                break;
            }
            case Constants.PICKUP_REQUEST_ORDER_HANDLER:{
                handlerMap.put(handlerName, new PickupRequestOrderHandler());
                LOG.info("Init akka handler {}", handlerName);
                break;
            }
            default:{
                LOG.warn("Handler {} havent been defined yet");
            }
        }
    }
}
