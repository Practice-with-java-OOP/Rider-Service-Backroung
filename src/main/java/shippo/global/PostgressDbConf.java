package shippo.global;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class PostgressDbConf {
    private static EbeanServer oldDb;
    private static EbeanServer operationDb;
    private static EbeanServer deliveryDb;
    private static EbeanServer riderDb;

    private static String oldDbName;
    private static String operationDbName;
    private static String deliveryDbName;
    private static String riderDbName;

    static {
        loadConfig();
    }

    // Load file config , khoi tao ebean server
    private static void loadConfig() {
        // Cấu hình db từ file
        oldDbName = ConfigLoader.getInstance().getProperties().getProperty("old.db.name");
        operationDbName = ConfigLoader.getInstance().getProperties().getProperty("operation.db.name");
        deliveryDbName = ConfigLoader.getInstance().getProperties().getProperty("delivery.db.name");
        riderDbName = ConfigLoader.getInstance().getProperties().getProperty("rider.db.name");
    }



    public static EbeanServer getOldDb() {
        if (oldDb == null)
            oldDb = Ebean.getServer(oldDbName);
        return oldDb;
    }

    public static EbeanServer getOperationDb() {
        if (operationDb == null)
            operationDb = Ebean.getServer(operationDbName);
        return operationDb;
    }

    public static EbeanServer getDeliveryDb() {
        if (deliveryDb == null)
            deliveryDb = Ebean.getServer(deliveryDbName);
        return deliveryDb;
    }

    public static EbeanServer getRiderDb(){
        if (riderDb == null)
            riderDb = Ebean.getServer(riderDbName);
        return riderDb;
    }
}
