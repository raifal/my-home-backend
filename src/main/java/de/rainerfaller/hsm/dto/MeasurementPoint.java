package de.rainerfaller.hsm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rfaller on 27.04.2017.
 */
@Entity(name = "hsm_measurement_point")
public class MeasurementPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    private String sensorId;
    private float temperatur;

    @OneToOne(targetEntity = Client.class)
    @JsonIgnore
    private Client client;

    public MeasurementPoint() {
    }

    public MeasurementPoint(Date created, String sensorId, float temperatur) {
        this.created = created;
        this.sensorId = sensorId;
        this.temperatur = temperatur;
    }

    public Date getCreated() {
        return created;
    }

    public String getSensorId() {
        return sensorId;
    }

    public float getTemperatur() {
        return temperatur;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setTemperatur(float temperatur) {
        this.temperatur = temperatur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MeasurementPoint{" +
                "id=" + id +
                ", created=" + created +
                ", sensorId='" + sensorId + '\'' +
                ", temperatur=" + temperatur +
                ", client=" + client +
                '}';
    }
}
