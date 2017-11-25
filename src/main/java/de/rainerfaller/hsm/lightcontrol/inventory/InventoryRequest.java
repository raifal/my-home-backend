package de.rainerfaller.hsm.lightcontrol.inventory;

public class InventoryRequest {

    private String inventory;

    public InventoryRequest() {
    }

    public InventoryRequest(String name) {
        this.inventory = name;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}