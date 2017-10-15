package de.rainerfaller.hsm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "hsm_sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long sensorId;

    @JsonIgnore
    private String address;

    private String sensorName;

    private String sensorDescription;

    @Column(name = "sortorder")
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", address='" + address + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", sensorDescription='" + sensorDescription + '\'' +
                ", order=" + order +
                '}';
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Sensor() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

}
