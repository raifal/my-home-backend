package de.rainerfaller.hsm.dao;

import de.rainerfaller.hsm.lightcontrol.pi.DoorStatus;
import de.rainerfaller.hsm.lightcontrol.pi.LightStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryInMemoryRepository {

    private Map<String, LightStatus> lightStatus = new HashMap<>();
    private Map<String, DoorStatus> doorStatus = new HashMap<>();
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public InventoryInMemoryRepository() {

    }

    public Map<String, LightStatus> getLightStatus() {
        return lightStatus;
    }

    public Map<String, DoorStatus> getDoorStatus() {
        return doorStatus;
    }

    public void changeInventory(Map<String, LightStatus> lightStatus, Map<String, DoorStatus> doorStatus) {
        this.lightStatus = lightStatus;
        this.doorStatus = doorStatus;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
