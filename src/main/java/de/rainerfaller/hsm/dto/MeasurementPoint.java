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

    @OneToOne(targetEntity = Sensor.class)
    private Sensor sensor;

    private float temperatur;

    @OneToOne(targetEntity = Client.class)
    @JsonIgnore
    private Client client;

    public MeasurementPoint() {
    }

    public MeasurementPoint(Date created, Sensor sensor, float temperatur) {
        this.created = created;
        this.sensor = sensor;
        this.temperatur = temperatur;
    }

    public Date getCreated() {
        return created;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public float getTemperatur() {
        return temperatur;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
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
                ", sensor='" + sensor + '\'' +
                ", temperatur=" + temperatur +
                ", client=" + client +
                '}';
    }
}
