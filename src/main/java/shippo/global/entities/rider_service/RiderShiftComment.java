package shippo.global.entities.rider_service;

import com.avaje.ebean.annotation.DbJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rider_shift_comments")
@NamedQuery(name = "RiderShiftComment.findAll", query = "SELECT t from RiderShiftComment t")
public class RiderShiftComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "created_by")
    @SerializedName("created_by")
    @JsonProperty("created_by")
    private Integer createdBy;

    @Column(name = "username")
    @SerializedName("username")
    @JsonProperty("username")
    private String userName;

    @Column(name = "rider_shift_id")
    @SerializedName("rider_shift_id")
    @JsonProperty("rider_shift_id")
    private Integer riderShiftId;

    private String files;

    @Column(name = "context_type")
    @SerializedName("context_type")
    @JsonProperty("context_type")
    private String contextType;

    @DbJson
    private Object context;

    private String scope;

    private Integer version;

    @Column(name = "created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public RiderShiftComment() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRiderShiftId() {
        return riderShiftId;
    }

    public void setRiderShiftId(Integer riderShiftId) {
        this.riderShiftId = riderShiftId;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
