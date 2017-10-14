package de.rainerfaller.hsm.dto;

/**
 * Created by rfaller on 27.04.2017.
 */
public class Client {
    private String client;

    public Client(String client) {
        this.client = client;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
