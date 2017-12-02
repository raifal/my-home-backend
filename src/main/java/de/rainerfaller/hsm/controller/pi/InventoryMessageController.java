package de.rainerfaller.hsm.controller.pi;

import de.rainerfaller.hsm.lightcontrol.inventory.InventoryRequest;
import de.rainerfaller.hsm.lightcontrol.inventory.InventoryResponse;
import de.rainerfaller.hsm.service.PiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryMessageController {

    @Autowired
    private PiManager piManager;

    @MessageMapping("/requestForInventory")
    @SendTo("/topic/lightcontrol")
    public InventoryResponse lightcontrol(InventoryRequest message) throws Exception {

        piManager.requestInventory();

        return new InventoryResponse("Received request for inventory, " + message.getInventory() + "!");
    }

}