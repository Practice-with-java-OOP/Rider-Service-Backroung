package shippo.global.entities.operation_service;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the pickup_request_orders database table.
 * 
 */
@Entity
@Table(name="pickup_request_orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PickupRequestOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SerializedName("id")
	@JsonProperty("id")
	private Integer id;

	@Column(name="created_at")
	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@Column(name="is_night_delivery")
	@SerializedName("is_night_delivery")
	@JsonProperty("is_night_delivery")
	private Boolean isNightDelivery;

//	@Column(name = "metadata")
	@DbJsonB
	@SerializedName("metadata")
	@JsonProperty("metadata")
	private Object metadata;

	@Column(name="order_id")
	@SerializedName("order_id")
	@JsonProperty("order_id")
	private Integer orderId;

	@Column(name="request_id")
	@SerializedName("request_id")
	@JsonProperty("request_id")
	private Integer requestId;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@Column(name = "version")
	@SerializedName("version")
	@JsonProperty("version")
	private Integer version;

	public PickupRequestOrder() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsNightDelivery() {
		return this.isNightDelivery;
	}

	public void setIsNightDelivery(Boolean isNightDelivery) {
		this.isNightDelivery = isNightDelivery;
	}

	public Object getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PickupRequestOrder)) return false;
		PickupRequestOrder pickupRequestOrder = (PickupRequestOrder) o;
		return Objects.equals(getId(), pickupRequestOrder.getId()) &&
				Objects.equals(getRequestId(), pickupRequestOrder.getRequestId()) &&
				Objects.equals(getOrderId(), pickupRequestOrder.getOrderId()) &&
				Objects.equals(getIsNightDelivery(), pickupRequestOrder.getIsNightDelivery()) &&
				Objects.equals(getVersion(), pickupRequestOrder.getVersion());
	}

	@Override
	public String toString() {
		return "PickupRequestOrder{" +
				"id=" + id +
				", createdAt=" + createdAt +
				", isNightDelivery=" + isNightDelivery +
				", metadata=" + metadata +
				", orderId=" + orderId +
				", requestId=" + requestId +
				", updatedAt=" + updatedAt +
				", version=" + version +
				'}';
	}

	@Override
	public int hashCode() {
		return id;
	}
}