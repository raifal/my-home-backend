package de.rainerfaller.hsm.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rfaller on 27.04.2017.
 */
@Entity(name = "hsm_water_level")
public class WaterLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int lowerVoltage;
    private int higherVoltage;
    private Date timestamp;

    @OneToOne(targetEntity = Client.class)
    private Client client;

    public WaterLevel() {

    }

    public WaterLevel(Date timestamp, int lowerVoltage, int higherVoltage) {
        this.timestamp = timestamp;
        this.lowerVoltage = lowerVoltage;
        this.higherVoltage = higherVoltage;
    }

    public int getLowerVoltage() {
        return lowerVoltage;
    }

    public int getHigherVoltage() {
        return higherVoltage;
    }

    public void setLowerVoltage(int lowerVoltage) {
        this.lowerVoltage = lowerVoltage;
    }

    public void setHigherVoltage(int higherVoltage) {
        this.higherVoltage = higherVoltage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WaterLevel{" +
                "id=" + id +
                ", lowerVoltage=" + lowerVoltage +
                ", higherVoltage=" + higherVoltage +
                ", timestamp=" + timestamp +
                ", client=" + client +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

}
