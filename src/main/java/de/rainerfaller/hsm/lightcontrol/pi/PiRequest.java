package de.rainerfaller.hsm.lightcontrol.pi;

import java.util.HashMap;
import java.util.Map;

public class PiRequest {
    private Map<String, LightStatus> lightSwitch = new HashMap<>();

    public PiRequest() {
    }

    public Map<String, LightStatus> getLightSwitch() {
        return lightSwitch;
    }

    public void setLightSwitch(Map<String, LightStatus> lightSwitch) {
        this.lightSwitch = lightSwitch;
    }
}
