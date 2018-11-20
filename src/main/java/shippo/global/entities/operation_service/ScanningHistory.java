package shippo.global.entities.operation_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="scanning_histories")
@NamedQuery(name="ScanningHistory.findAll", query="SELECT s FROM ScanningHistory s")
public class ScanningHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String action;

    @Column(name="action_by")
    @SerializedName("action_by")
    @JsonProperty("action_by")
    private Integer actionBy;

    @Column(name="action_time")
    @SerializedName("action_time")
    @JsonProperty("action_time")
    private Timestamp actionTime;

    private String barcode;

    @Column(name="hub_code")
    @SerializedName("hub_code")
    @JsonProperty("hub_code")
    private String hubCode;

    @Column(name="hub_id")
    @SerializedName("hub_id")
    @JsonProperty("hub_id")
    private Integer hubId;

    @Column(name="hub_status")
    @SerializedName("hub_status")
    @JsonProperty("hub_status")
    private String hubStatus;

    @Column(name="hub_name")
    @SerializedName("hub_name")
    @JsonProperty("hub_name")
    private String hubName;

    @Column(name="order_barcode")
    @SerializedName("order_barcode")
    @JsonProperty("order_barcode")
    private String orderBarcode;

    @Column(name="order_id")
    @SerializedName("order_id")
    @JsonProperty("order_id")
    private Integer orderId;

    public ScanningHistory() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getActionBy() {
        return this.actionBy;
    }

    public void setActionBy(Integer actionBy) {
        this.actionBy = actionBy;
    }

    public Timestamp getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getHubCode() {
        return this.hubCode;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode;
    }

    public Integer getHubId() {
        return this.hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public String getHubStatus() {
        return this.hubStatus;
    }

    public void setHubStatus(String hubStatus) {
        this.hubStatus = hubStatus;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getOrderBarcode() {
        return this.orderBarcode;
    }

    public void setOrderBarcode(String orderBarcode) {
        this.orderBarcode = orderBarcode;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
