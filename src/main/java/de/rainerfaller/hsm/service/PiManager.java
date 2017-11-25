package de.rainerfaller.hsm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.rainerfaller.hsm.lightcontrol.pi.PiRequest;
import de.rainerfaller.hsm.lightcontrol.pi.LightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PiManager {
    private SimpMessagingTemplate template;

    @Autowired
    public PiManager(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendInventory() {
        sendWithInventory(new HashMap<>());
    }

    public void changeLightStatusAndSendInventory(String lightId, boolean on)
    {

        Map<String, LightStatus> light = new HashMap<>();
        light.put(lightId, on ? LightStatus.ON : LightStatus.OFF);

        sendWithInventory(light);
    }

    private void sendWithInventory(Map<String, LightStatus> lights) {

        PiRequest request = new PiRequest();
        request.setLightSwitch(lights);

        // TODO add inventory

        ObjectMapper mapper = new ObjectMapper();
        String requestString = null;
        try {
            requestString = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.template.convertAndSend("/topic/lightcontrol", requestString);

    }
}
