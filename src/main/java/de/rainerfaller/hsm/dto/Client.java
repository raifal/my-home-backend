package de.rainerfaller.hsm.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rfaller on 27.04.2017.
 */
@Entity(name = "hsm_client")
public class Client {
    @Id
    private String client;

    public Client() {
    }

    public Client(String client) {
        this.client = client;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client='" + client + '\'' +
                '}';
    }
}
