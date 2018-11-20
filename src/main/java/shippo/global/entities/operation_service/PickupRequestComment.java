package shippo.global.entities.operation_service;

import com.avaje.ebean.annotation.DbJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the pickup_request_comments database table.
 * 
 */
@Entity
@Table(name="pickup_request_comments")
@NamedQuery(name="PickupRequestComment.findAll", query="SELECT p FROM PickupRequestComment p")
public class PickupRequestComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@DbJson
	private Object context;

	@Column(name="context_type")
	@SerializedName("context_type")
	@JsonProperty("context_type")
	private String contextType;

	@Column(name="created_at")
	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	@SerializedName("created_by")
	@JsonProperty("created_by")
	private Integer createdBy;

	private String files;

	@Column(name="pickup_request_id")
	@SerializedName("pickup_request_id")
	@JsonProperty("pickup_request_id")
	private Integer pickupRequestId;

	private String scope;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	private Integer version;

	public PickupRequestComment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Object getContext() {
		return this.context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

	public String getContextType() {
		return this.contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
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

	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Integer getPickupRequestId() {
		return this.pickupRequestId;
	}

	public void setPickupRequestId(Integer pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
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

}