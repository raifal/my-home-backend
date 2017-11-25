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
        doIt(true);
    }

    public void changeLightStatusAndSendInventory()
    {
        doIt(false);
    }

    private void doIt(boolean sendInventory) {
        // TODO remove, just for websocket testing
        Map<String, LightStatus> l = new HashMap<>();

        l.put("light1", LightStatus.OFF);
        l.put("light2", LightStatus.ON);

        PiRequest request = new PiRequest();

        if (sendInventory)
            request.setLightSwitch(l);


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
