package de.rainerfaller.hsm.dto;

import java.util.Date;

/**
 * Created by rfaller on 27.04.2017.
 */
public class MeasurementPoint {
    private Date timestamp;
    private String sensorId;
    private float temperatur;
    private Client client;

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
}
