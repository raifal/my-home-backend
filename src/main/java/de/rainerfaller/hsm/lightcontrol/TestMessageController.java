package de.rainerfaller.hsm.lightcontrol;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TestMessageController {


    @MessageMapping("/testmessage")
    @SendTo("/topic/lightcontrol")
    public TestMessageResponse lightcontrol(TestMessageRequest message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new TestMessageResponse("Hello, " + message.getName() + "!");
    }

}