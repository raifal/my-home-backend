package de.rainerfaller.hsm.dto;

import java.util.Date;

/**
 * Created by rfaller on 27.04.2017.
 */
public class WaterLevel {
    private int lowerVoltage;
    private int higherVoltage;
    private Date timestamp;
    private Client client;

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
}
