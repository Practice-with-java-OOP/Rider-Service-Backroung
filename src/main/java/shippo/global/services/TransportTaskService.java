package shippo.global.services;

import com.avaje.ebean.EbeanServer;
import shippo.global.PostgressDbConf;
import shippo.global.entities.delivery_service.DeliveryOrder;
import shippo.global.entities.operation_service.PickupRequestOrder;
import shippo.global.entities.rider_service.TransportationTask;

import java.util.ArrayList;
import java.util.List;

public class TransportTaskService {
    //region --constant--
    public static final String TYPE_PICKUP = "PICKUP";
    public static final String TYPE_DELIVER = "DELIVER";
    public static final String TYPE_PICKUP_AND_DELIVER = "PICKUP_AND_DELIVER";

    public static final String STATE_NEW = "NEW";
    public static final String STATE_IN_PROCESS = "IN_PROCESS";
    public static final String STATE_DONE = "DONE";
    public static final String STATE_FAILED = "FAILED";
    public static final String STATE_INTERNAL_FAILED = "INTERNAL_FAILED";
    public static final String STATE_CANCELLED = "CANCELLED";
    public static final String STATE_REJECT = "REJECT";
    //endregion

    public static String genPickupNote(TransportationTask task) {
        if (null == task) return "";
        if (!task.getType().equals(TransportTaskService.TYPE_PICKUP)) return "";

        EbeanServer operationServer = PostgressDbConf.getOperationDb();
        EbeanServer deliveryServer = PostgressDbConf.getDeliveryDb();
        List<PickupRequestOrder> pickupRequestOrders = operationServer.find(PickupRequestOrder.class)
                .where()
                .and()
                .eq("requestId", task.getObjectId())
                .endAnd()
                .findList();
        if (null == pickupRequestOrders) return "";

        List<Integer> orderIds = new ArrayList<>();
        for (int ii = 0; ii < pickupRequestOrders.size(); ii++) {
            orderIds.add(pickupRequestOrders.get(ii).getOrderId());
        }

        List<DeliveryOrder> orders = deliveryServer.find(DeliveryOrder.class)
                .where()
                .and()
                .in("id", orderIds)
                .endAnd()
                .findList();

        String description = "";
//        String[] pickupNotes = new String[orders.size()];

        for (DeliveryOrder order : orders) {
            String pickupNotes = null == order.getPickupNote() ? "" : order.getPickupNote();
            description = description + order.getBarcode() + ": " + pickupNotes + "\r\n" ;
        }

        return description;
    }
}
