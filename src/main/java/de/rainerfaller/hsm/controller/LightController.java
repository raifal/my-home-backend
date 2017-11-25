package de.rainerfaller.hsm.controller;

import de.rainerfaller.hsm.service.PiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LightController {
    @Autowired
    private PiManager piManager;

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
}
