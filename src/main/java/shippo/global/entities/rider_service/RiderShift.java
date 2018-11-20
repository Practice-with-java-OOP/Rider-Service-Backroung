package shippo.global.entities.rider_service;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the rider_shifts database table.
 * 
 */
@Entity
@Table(name="rider_shifts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiderShift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@DbJsonB
	@Column(name = "barcodes")
	@SerializedName("barcodes")
	@JsonProperty("barcodes")
	private Object barcodes;

	@DbJsonB
	@Column(name = "tasks")
	@SerializedName("tasks")
	@JsonProperty("tasks")
	private Object tasks;

	@Column(name="created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdAt;

	@Column(name="created_by")
    @SerializedName("created_by")
    @JsonProperty("created_by")
	private Integer createdBy;

	@Column(name="rider_id")
    @SerializedName("rider_id")
    @JsonProperty("rider_id")
	private Integer riderId;

	@Column(name="shift_id")
    @SerializedName("shift_id")
    @JsonProperty("shift_id")
	private Integer shiftId;

	private String state;

	@Column(name="task_count")
    @SerializedName("task_count")
    @JsonProperty("task_count")
	private Integer taskCount;

	@Column(name="task_type")
    @SerializedName("task_type")
    @JsonProperty("task_type")
	private String taskType;

	@Column(name="updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
	private Timestamp updatedAt;

	@Column(name="working_date")
	@SerializedName("working_date")
	@JsonProperty("working_date")
	private Timestamp working_date;

	public Timestamp getWorking_date() {
		return working_date;
	}

	public void setWorking_date(Timestamp working_date) {
		this.working_date = working_date;
	}

	@DbJsonB
	@Column(name="metadata")
	@SerializedName("metadata")
	private List<Metadata> metadata;

	@Column(name="hub_id")
	@SerializedName("hub_id")
	@JsonProperty("hub_id")
	private Integer hubId;

	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Object getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(Object barcodes) {
		this.barcodes = barcodes;
	}

	public Object getTasks() {
		return tasks;
	}

	public void setTasks(Object tasks) {
		this.tasks = tasks;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getRiderId() {
		return riderId;
	}

	public void setRiderId(Integer riderId) {
		this.riderId = riderId;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getTaskType() {
		return taskType;
	}

    @Override
    public String toString() {
        return "RiderShift{" +
                "id=" + id +
                ", barcodes=" + barcodes +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", riderId=" + riderId +
                ", shiftId=" + shiftId +
                ", state='" + state + '\'' +
                ", taskCount=" + taskCount +
                ", taskType='" + taskType + '\'' +
                ", updatedAt=" + updatedAt +
                ", metadata=" + metadata +
                ", hubId=" + hubId +
                ", version=" + version +
                '}';
    }

    public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Metadata> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}

	public Integer getHubId() {
		return hubId;
	}

	public void setHubId(Integer hubId) {
		this.hubId = hubId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RiderShift that = (RiderShift) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(barcodes, that.barcodes) &&
				Objects.equals(tasks, that.tasks) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(createdBy, that.createdBy) &&
				Objects.equals(riderId, that.riderId) &&
				Objects.equals(shiftId, that.shiftId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(taskCount, that.taskCount) &&
				Objects.equals(taskType, that.taskType) &&
				Objects.equals(updatedAt, that.updatedAt) &&
				Objects.equals(working_date, that.working_date) &&
				Objects.equals(metadata, that.metadata) &&
				Objects.equals(hubId, that.hubId) &&
				Objects.equals(version, that.version);
	}

	@Override
	public int hashCode() {
		return id;
	}
}