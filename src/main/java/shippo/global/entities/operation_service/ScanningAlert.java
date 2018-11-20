package shippo.global.entities.operation_service;

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


/**
 * The persistent class for the scanning_alerts database table.
 * 
 */
@Entity
@Table(name="scanning_alerts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanningAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="created_at")
	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	@SerializedName("created_by")
	@JsonProperty("created_by")
	private Integer createdBy;

	@Column(name = "message")
	@SerializedName("message")
	@JsonProperty("message")
	private String message;

	@Column(name="order_id")
	@SerializedName("order_id")
	@JsonProperty("order_id")
	private Integer orderId;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@Column(name = "version")
	@SerializedName("version")
	@JsonProperty("version")
	private Integer version;

	public ScanningAlert() {
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

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public int hashCode() {
		return id;
	}
}