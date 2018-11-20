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
 * The persistent class for the operators database table.
 * 
 */
@Entity
@Table(name="operators")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SerializedName("id")
	@JsonProperty("id")
	private Integer id;

	@Column(name="avatar")
	@SerializedName("avatar")
	@JsonProperty("avatar")
	private String avatar;

	@Column(name="created_at")
	@SerializedName("created_at")
	@JsonProperty("created_at")
	private Timestamp createdAt;

	@Column(name = "email")
	@SerializedName("email")
	@JsonProperty("email")
	private String email;

	@Column(name="full_name")
	@SerializedName("full_name")
	@JsonProperty("full_name")
	private String fullName;

//	@Column(name="metadata")
	@DbJsonB
	@SerializedName("metadata")
	@JsonProperty("metadata")
	private Object metadata;

	@Column(name="tagline")
	@SerializedName("tagline")
	@JsonProperty("tagline")
	private String tagline;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@Column(name="user_id")
	@SerializedName("user_id")
	@JsonProperty("user_id")
	private Integer userId;

	@Column(name="version")
	@SerializedName("version")
	@JsonProperty("version")
	private Integer version;

	public Operator() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Object getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public String getTagline() {
		return this.tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		if (!(o instanceof Operator)) return false;
		Operator operator = (Operator) o;
		return 	Objects.equals(getUserId(), operator.getUserId()) &&
				Objects.equals(getEmail(), operator.getEmail()) &&
				Objects.equals(getFullName(), operator.getFullName()) &&
				Objects.equals(getTagline(), operator.getTagline()) &&
				Objects.equals(getAvatar(), operator.getAvatar()) &&
				Objects.equals(getVersion(), operator.getVersion());
	}

	@Override
	public int hashCode() {
		return id;
	}

}