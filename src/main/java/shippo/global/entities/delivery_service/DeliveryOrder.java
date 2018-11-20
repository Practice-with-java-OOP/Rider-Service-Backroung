package shippo.global.entities.delivery_service;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the delivery_orders database table.
 * 
 */
@Entity
@Table(name="delivery_orders")
@NamedQuery(name="DeliveryOrder.findAll", query="SELECT d FROM DeliveryOrder d")
public class DeliveryOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String barcode;

	private Double cod;

	@Column(name = "pickup_address_id")
	@SerializedName("pickup_address_id")
	@JsonProperty("pickup_address_id")
	private Integer pickupAddressId;

	@Column(name="create_from_order")
	@SerializedName("create_from_order")
	@JsonProperty("create_from_order")
	private Integer createFromOrder;

	@Column(name="create_method")
	@SerializedName("create_method")
	@JsonProperty("create_method")
	private String createMethod;

	@Column(name="created_at")
	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@Column(name="current_warehouse_id")
	@SerializedName("current_warehouse_id")
	@JsonProperty("current_warehouse_id")
	private Integer currentWarehouseId;

	@Column(name="current_warehouse_state")
	@SerializedName("current_warehouse_state")
	@JsonProperty("current_warehouse_state")
	private String currentWarehouseState;

	@Column(name="deliver_contact")
	@SerializedName("deliver_contact")
	@JsonProperty("deliver_contact")
	private String deliverContact;

	@Column(name="deliver_detail_address")
	@SerializedName("deliver_detail_address")
	@JsonProperty("deliver_detail_address")
	private String deliverDetailAddress;

	@Column(name="deliver_full_address")
	@SerializedName("deliver_full_address")
	@JsonProperty("deliver_full_address")
	private String deliverFullAddress;

	@Column(name="deliver_location_ids_path")
	@SerializedName("deliver_location_ids_path")
	@JsonProperty("deliver_location_ids_path")
	private String deliverLocationIdsPath;

	@Column(name="deliver_location_names_path")
	@SerializedName("deliver_location_names_path")
	@JsonProperty("deliver_location_names_path")
	private String deliverLocationNamesPath;

	@Column(name="deliver_phone")
	@SerializedName("deliver_phone")
	@JsonProperty("deliver_phone")
	private String deliverPhone;

	@Column(name="deliver_times")
	@SerializedName("deliver_times")
	@JsonProperty("deliver_times")
	private Integer deliverTimes;

	@Column(name="deliver_warehouse_id")
	@SerializedName("deliver_warehouse_id")
	@JsonProperty("deliver_warehouse_id")
	private Integer deliverWarehouseId;

	@Column(name="delivery_note")
	@SerializedName("delivery_note")
	@JsonProperty("delivery_note")
	private String deliveryNote;

	@Column(name="export_warehouse_at")
	@SerializedName("export_warehouse_at")
	@JsonProperty("export_warehouse_at")
	private Timestamp exportWarehouseAt;

	@DbJsonB
	private Object features;

	private String goods;

	@Column(name="import_warehouse_at")
	@SerializedName("import_warehouse_at")
	@JsonProperty("import_warehouse_at")
	private Timestamp importWarehouseAt;

	@Column(name="is_return")
	@SerializedName("is_return")
	@JsonProperty("is_return")
	private Boolean isReturn;

	@Column(name="merchant_user_id")
	@SerializedName("merchant_user_id")
	@JsonProperty("merchant_user_id")
	private Integer merchantUserId;

	@Column(name="merchant_order_code")
	@SerializedName("merchant_order_code")
	@JsonProperty("merchant_order_code")
	private String merchantOrderCode;

	@Column(name="merchant_private_note")
	@SerializedName("merchant_private_note")
	@JsonProperty("merchant_private_note")
	private String merchantPrivateNote;

	@Column(name="order_state")
	@SerializedName("order_state")
	@JsonProperty("order_state")
	private String orderState;

	@Column(name="pickup_contact")
	@SerializedName("pickup_contact")
	@JsonProperty("pickup_contact")
	private String pickupContact;

	@Column(name="pickup_detail_address")
	@SerializedName("pickup_detail_address")
	@JsonProperty("pickup_detail_address")
	private String pickupDetailAddress;

	@Column(name="pickup_full_address")
	@SerializedName("pickup_full_address")
	@JsonProperty("pickup_full_address")
	private String pickupFullAddress;

	@Column(name="pickup_location_ids_path")
	@SerializedName("pickup_location_ids_path")
	@JsonProperty("pickup_location_ids_path")
	private String pickupLocationIdsPath;

	@Column(name="pickup_location_names_path")
	@SerializedName("pickup_location_names_path")
	@JsonProperty("pickup_location_names_path")
	private String pickupLocationNamesPath;

	@Column(name="pickup_note")
	@SerializedName("pickup_note")
	@JsonProperty("pickup_note")
	private String pickupNote;

	@Column(name="pickup_phone")
	@SerializedName("pickup_phone")
	@JsonProperty("pickup_phone")
	private String pickupPhone;

	@Column(name="pickup_times")
	@SerializedName("pickup_times")
	@JsonProperty("pickup_times")
	private Integer pickupTimes;

	@Column(name="pickup_warehouse_id")
	@SerializedName("pickup_warehouse_id")
	@JsonProperty("pickup_warehouse_id")
	private Integer pickupWarehouseId;

	@Column(name="real_cod")
	@SerializedName("real_cod")
	@JsonProperty("real_cod")
	private Double realCod;

	private String state;

	@DbJsonB
	private Object timeline;

	@Column(name="total_fee")
	@SerializedName("total_fee")
	@JsonProperty("total_fee")
	private Double totalFee;

	@Column(name="total_merchant_fee")
	@SerializedName("total_merchant_fee")
	@JsonProperty("total_merchant_fee")
	private Double totalMerchantFee;

	@DbJsonB
	private Object metadata;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	private Integer version;

	private Integer weight;

	@Column(name="prepare_pickup_at")
	@SerializedName("prepare_pickup_at")
	@JsonProperty("prepare_pickup_at")
	private Timestamp preparePickupAt;

	@Column(name="picked_up_at")
	@SerializedName("picked_up_at")
	@JsonProperty("picked_up_at")
	private Timestamp pickedUpAt;

	@Column(name="prepare_delivery_at")
	@SerializedName("prepare_delivery_at")
	@JsonProperty("prepare_delivery_at")
	private Timestamp prepareDeliveryAt;

	@Column(name="start_delivery_at")
	@SerializedName("start_delivery_at")
	@JsonProperty("start_delivery_at")
	private Timestamp startDeliveryAt;

	@Column(name="prepare_redelivery_at")
	@SerializedName("prepare_redelivery_at")
	@JsonProperty("prepare_redelivery_at")
	private Timestamp prepareRedeliveryAt;

	@Column(name="failed_delivery_at")
	@SerializedName("failed_delivery_at")
	@JsonProperty("failed_delivery_at")
	private Timestamp failedDeliveryAt;

	@Column(name="delivered_at")
	@SerializedName("delivered_at")
	@JsonProperty("delivered_at")
	private Timestamp deliveredAt;

	@Column(name="delivery_completed_at")
	@SerializedName("delivery_completed_at")
	@JsonProperty("delivery_completed_at")
	private Timestamp deliveryCompletedAt;

	@Column(name="prepare_return_at")
	@SerializedName("prepare_return_at")
	@JsonProperty("prepare_return_at")
	private Timestamp prepareReturnAt;

	@Column(name="start_return_at")
	@SerializedName("start_return_at")
	@JsonProperty("start_return_at")
	private Timestamp startReturnAt;

	@Column(name="returned_at")
	@SerializedName("returned_at")
	@JsonProperty("returned_at")
	private Timestamp returnedAt;

	@Column(name="cancelled_at")
	@SerializedName("cancelled_at")
	@JsonProperty("cancelled_at")
	private Timestamp cancelledAt;

	@Column(name="returned_completed_at")
	@SerializedName("returned_completed_at")
	@JsonProperty("returned_completed_at")
	private Timestamp returnedCompletedAt;

	@Column(name="delivery_to_hub_at")
	@SerializedName("delivery_to_hub_at")
	@JsonProperty("delivery_to_hub_at")
	private Timestamp deliveryToHubAt;

	public DeliveryOrder() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Double getCod() {
		return this.cod;
	}

	public void setCod(Double cod) {
		this.cod = cod;
	}

	public Integer getCreateFromOrder() {
		return this.createFromOrder;
	}

	public void setCreateFromOrder(Integer createFromOrder) {
		this.createFromOrder = createFromOrder;
	}

	public String getCreateMethod() {
		return this.createMethod;
	}

	public void setCreateMethod(String createMethod) {
		this.createMethod = createMethod;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCurrentWarehouseId() {
		return this.currentWarehouseId;
	}

	public void setCurrentWarehouseId(Integer currentWarehouseId) {
		this.currentWarehouseId = currentWarehouseId;
	}

	public String getCurrentWarehouseState() {
		return this.currentWarehouseState;
	}

	public void setCurrentWarehouseState(String currentWarehouseState) {
		this.currentWarehouseState = currentWarehouseState;
	}

	public String getDeliverContact() {
		return this.deliverContact;
	}

	public void setDeliverContact(String deliverContact) {
		this.deliverContact = deliverContact;
	}

	public String getDeliverDetailAddress() {
		return this.deliverDetailAddress;
	}

	public void setDeliverDetailAddress(String deliverDetailAddress) {
		this.deliverDetailAddress = deliverDetailAddress;
	}

	public String getDeliverFullAddress() {
		return this.deliverFullAddress;
	}

	public void setDeliverFullAddress(String deliverFullAddress) {
		this.deliverFullAddress = deliverFullAddress;
	}

	public String getDeliverLocationIdsPath() {
		return this.deliverLocationIdsPath;
	}

	public void setDeliverLocationIdsPath(String deliverLocationIdsPath) {
		this.deliverLocationIdsPath = deliverLocationIdsPath;
	}

	public String getDeliverLocationNamesPath() {
		return this.deliverLocationNamesPath;
	}

	public void setDeliverLocationNamesPath(String deliverLocationNamesPath) {
		this.deliverLocationNamesPath = deliverLocationNamesPath;
	}

	public String getDeliverPhone() {
		return this.deliverPhone;
	}

	public void setDeliverPhone(String deliverPhone) {
		this.deliverPhone = deliverPhone;
	}

	public Integer getDeliverTimes() {
		return this.deliverTimes;
	}

	public void setDeliverTimes(Integer deliverTimes) {
		this.deliverTimes = deliverTimes;
	}

	public Integer getDeliverWarehouseId() {
		return this.deliverWarehouseId;
	}

	public void setDeliverWarehouseId(Integer deliverWarehouseId) {
		this.deliverWarehouseId = deliverWarehouseId;
	}

	public String getDeliveryNote() {
		return this.deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public Timestamp getExportWarehouseAt() {
		return this.exportWarehouseAt;
	}

	public void setExportWarehouseAt(Timestamp exportWarehouseAt) {
		this.exportWarehouseAt = exportWarehouseAt;
	}

	public Object getFeatures() {
		return this.features;
	}

	public void setFeatures(Object features) {
		this.features = features;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Timestamp getImportWarehouseAt() {
		return this.importWarehouseAt;
	}

	public void setImportWarehouseAt(Timestamp importWarehouseAt) {
		this.importWarehouseAt = importWarehouseAt;
	}

	public Boolean getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Integer getMerchantUserId() {
		return merchantUserId;
	}

	public void setMerchantUserId(Integer merchantUserId) {
		this.merchantUserId = merchantUserId;
	}

	public String getMerchantOrderCode() {
		return this.merchantOrderCode;
	}

	public void setMerchantOrderCode(String merchantOrderCode) {
		this.merchantOrderCode = merchantOrderCode;
	}

	public String getMerchantPrivateNote() {
		return this.merchantPrivateNote;
	}

	public void setMerchantPrivateNote(String merchantPrivateNote) {
		this.merchantPrivateNote = merchantPrivateNote;
	}

	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getPickupContact() {
		return this.pickupContact;
	}

	public void setPickupContact(String pickupContact) {
		this.pickupContact = pickupContact;
	}

	public String getPickupDetailAddress() {
		return this.pickupDetailAddress;
	}

	public void setPickupDetailAddress(String pickupDetailAddress) {
		this.pickupDetailAddress = pickupDetailAddress;
	}

	public String getPickupFullAddress() {
		return this.pickupFullAddress;
	}

	public void setPickupFullAddress(String pickupFullAddress) {
		this.pickupFullAddress = pickupFullAddress;
	}

	public String getPickupLocationIdsPath() {
		return this.pickupLocationIdsPath;
	}

	public void setPickupLocationIdsPath(String pickupLocationIdsPath) {
		this.pickupLocationIdsPath = pickupLocationIdsPath;
	}

	public String getPickupLocationNamesPath() {
		return this.pickupLocationNamesPath;
	}

	public void setPickupLocationNamesPath(String pickupLocationNamesPath) {
		this.pickupLocationNamesPath = pickupLocationNamesPath;
	}

	public String getPickupNote() {
		return this.pickupNote;
	}

	public void setPickupNote(String pickupNote) {
		this.pickupNote = pickupNote;
	}

	public String getPickupPhone() {
		return this.pickupPhone;
	}

	public void setPickupPhone(String pickupPhone) {
		this.pickupPhone = pickupPhone;
	}

	public Integer getPickupTimes() {
		return this.pickupTimes;
	}

	public void setPickupTimes(Integer pickupTimes) {
		this.pickupTimes = pickupTimes;
	}

	public Integer getPickupWarehouseId() {
		return this.pickupWarehouseId;
	}

	public void setPickupWarehouseId(Integer pickupWarehouseId) {
		this.pickupWarehouseId = pickupWarehouseId;
	}

	public Double getRealCod() {
		return this.realCod;
	}

	public void setRealCod(Double realCod) {
		this.realCod = realCod;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getTimeline() {
		return this.timeline;
	}

	public void setTimeline(Object timeline) {
		this.timeline = timeline;
	}

	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getTotalMerchantFee() {
		return this.totalMerchantFee;
	}

	public void setTotalMerchantFee(Double totalMerchantFee) {
		this.totalMerchantFee = totalMerchantFee;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getPickupAddressId() {
		return pickupAddressId;
	}

	public void setPickupAddressId(Integer pickupAddressId) {
		this.pickupAddressId = pickupAddressId;
	}

	public Boolean getReturn() {
		return isReturn;
	}

	public void setReturn(Boolean aReturn) {
		isReturn = aReturn;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Timestamp getPreparePickupAt() {
		return preparePickupAt;
	}

	public void setPreparePickupAt(Timestamp preparePickupAt) {
		this.preparePickupAt = preparePickupAt;
	}

	public Timestamp getPickedUpAt() {
		return pickedUpAt;
	}

	public void setPickedUpAt(Timestamp pickedUpAt) {
		this.pickedUpAt = pickedUpAt;
	}

	public Timestamp getPrepareDeliveryAt() {
		return prepareDeliveryAt;
	}

	public void setPrepareDeliveryAt(Timestamp prepareDeliveryAt) {
		this.prepareDeliveryAt = prepareDeliveryAt;
	}

	public Timestamp getStartDeliveryAt() {
		return startDeliveryAt;
	}

	public void setStartDeliveryAt(Timestamp startDeliveryAt) {
		this.startDeliveryAt = startDeliveryAt;
	}

	public Timestamp getPrepareRedeliveryAt() {
		return prepareRedeliveryAt;
	}

	public void setPrepareRedeliveryAt(Timestamp prepareRedeliveryAt) {
		this.prepareRedeliveryAt = prepareRedeliveryAt;
	}

	public Timestamp getFailedDeliveryAt() {
		return failedDeliveryAt;
	}

	public void setFailedDeliveryAt(Timestamp failedDeliveryAt) {
		this.failedDeliveryAt = failedDeliveryAt;
	}

	public Timestamp getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(Timestamp deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public Timestamp getDeliveryCompletedAt() {
		return deliveryCompletedAt;
	}

	public void setDeliveryCompletedAt(Timestamp deliveryCompletedAt) {
		this.deliveryCompletedAt = deliveryCompletedAt;
	}

	public Timestamp getPrepareReturnAt() {
		return prepareReturnAt;
	}

	public void setPrepareReturnAt(Timestamp prepareReturnAt) {
		this.prepareReturnAt = prepareReturnAt;
	}

	public Timestamp getStartReturnAt() {
		return startReturnAt;
	}

	public void setStartReturnAt(Timestamp startReturnAt) {
		this.startReturnAt = startReturnAt;
	}

	public Timestamp getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(Timestamp returnedAt) {
		this.returnedAt = returnedAt;
	}

	public Timestamp getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Timestamp cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	public Timestamp getReturnedCompletedAt() {
		return returnedCompletedAt;
	}

	public void setReturnedCompletedAt(Timestamp returnedCompletedAt) {
		this.returnedCompletedAt = returnedCompletedAt;
	}

	public Timestamp getDeliveryToHubAt() {
		return deliveryToHubAt;
	}

	public void setDeliveryToHubAt(Timestamp deliveryToHubAt) {
		this.deliveryToHubAt = deliveryToHubAt;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return "DeliveryOrder{" +
				"id=" + id +
				", barcode='" + barcode + '\'' +
				", cod=" + cod +
				", pickupAddressId=" + pickupAddressId +
				", createFromOrder=" + createFromOrder +
				", createMethod='" + createMethod + '\'' +
				", createdAt=" + createdAt +
				", currentWarehouseId=" + currentWarehouseId +
				", currentWarehouseState='" + currentWarehouseState + '\'' +
				", deliverContact='" + deliverContact + '\'' +
				", deliverDetailAddress='" + deliverDetailAddress + '\'' +
				", deliverFullAddress='" + deliverFullAddress + '\'' +
				", deliverLocationIdsPath='" + deliverLocationIdsPath + '\'' +
				", deliverLocationNamesPath='" + deliverLocationNamesPath + '\'' +
				", deliverPhone='" + deliverPhone + '\'' +
				", deliverTimes=" + deliverTimes +
				", deliverWarehouseId=" + deliverWarehouseId +
				", deliveryNote='" + deliveryNote + '\'' +
				", exportWarehouseAt=" + exportWarehouseAt +
				", features=" + features +
				", goods=" + goods +
				", importWarehouseAt=" + importWarehouseAt +
				", isReturn=" + isReturn +
				", merchantUserId=" + merchantUserId +
				", merchantOrderCode='" + merchantOrderCode + '\'' +
				", merchantPrivateNote='" + merchantPrivateNote + '\'' +
				", orderState='" + orderState + '\'' +
				", pickupContact='" + pickupContact + '\'' +
				", pickupDetailAddress='" + pickupDetailAddress + '\'' +
				", pickupFullAddress='" + pickupFullAddress + '\'' +
				", pickupLocationIdsPath='" + pickupLocationIdsPath + '\'' +
				", pickupLocationNamesPath='" + pickupLocationNamesPath + '\'' +
				", pickupNote='" + pickupNote + '\'' +
				", pickupPhone='" + pickupPhone + '\'' +
				", pickupTimes=" + pickupTimes +
				", pickupWarehouseId=" + pickupWarehouseId +
				", realCod=" + realCod +
				", state='" + state + '\'' +
				", timeline=" + timeline +
				", totalFee=" + totalFee +
				", totalMerchantFee=" + totalMerchantFee +
				", metadata=" + metadata +
				", updatedAt=" + updatedAt +
				", version=" + version +
				", weight=" + weight +
				'}';
	}
}
