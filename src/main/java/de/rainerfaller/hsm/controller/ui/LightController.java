package de.rainerfaller.hsm.controller.ui;

import de.rainerfaller.hsm.dao.InventoryInMemoryRepository;
import de.rainerfaller.hsm.lightcontrol.pi.LightStatus;
import de.rainerfaller.hsm.service.PiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class LightController {
    @Autowired
    private PiManager piManager;

    @Autowired
    private InventoryInMemoryRepository inventoryInMemoryRepository;

    public LightController() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/light/{id}/on")
    public @ResponseBody
    String lightOn(@PathVariable("id") String id) {
        piManager.changeLightStatusAndSendInventory(id, true);
        return "processed";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/light/{id}/off")
    public @ResponseBody
    String lightOff(@PathVariable("id") String id) {
        piManager.changeLightStatusAndSendInventory(id, false);
        return "processed";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/lights")
    public @ResponseBody
    Map<String, LightStatus> lights() throws InterruptedException {
        LocalDateTime lastUpdated = inventoryInMemoryRepository.getLastUpdated();

        piManager.requestInventory();

        // wait until asynch response from pi is available
        int timeoutMillis = 5 * 1000;
        final int waitMillis = 500;
        do {
            Thread.sleep(waitMillis);
            timeoutMillis -= waitMillis;

            if (inventoryInMemoryRepository.getLastUpdated().isAfter(lastUpdated)) {
                // update available
                return inventoryInMemoryRepository.getLightStatus();
            }
        }
        while (timeoutMillis > 0);
        throw new RuntimeException("Timout waiting for light inventory");
    }


}
