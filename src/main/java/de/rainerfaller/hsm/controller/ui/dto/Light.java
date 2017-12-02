package de.rainerfaller.hsm.controller.ui.dto;

import de.rainerfaller.hsm.lightcontrol.pi.LightStatus;

public class Light {
    private String id;
    private String name;
    private LightStatus status;

    public Light(String id, String name, LightStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LightStatus getStatus() {
        return status;
    }

    public void setStatus(LightStatus status) {
        this.status = status;
    }
}
