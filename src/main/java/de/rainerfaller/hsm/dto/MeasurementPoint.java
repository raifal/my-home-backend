package de.rainerfaller.hsm.dto;

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

    private Date timestamp;
    private String sensorId;
    private float temperatur;

    @OneToOne(targetEntity = Client.class)
    private Client client;

    public MeasurementPoint() {
    }

    public MeasurementPoint(Date timestamp, String sensorId, float temperatur) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.temperatur = temperatur;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSensorId() {
        return sensorId;
    }

    public float getTemperatur() {
        return temperatur;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
                ", timestamp=" + timestamp +
                ", sensorId='" + sensorId + '\'' +
                ", temperatur=" + temperatur +
                ", client=" + client +
                '}';
    }
}
