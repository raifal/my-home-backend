package de.rainerfaller.hsm.lightcontrol.itenerary;

import de.rainerfaller.hsm.controller.ui.HomeAwayController;
import de.rainerfaller.hsm.controller.ui.LightController;
import de.rainerfaller.hsm.controller.ui.dto.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
public class AutomaticLightControl {
    private static final Logger logger = LoggerFactory.getLogger(AutomaticLightControl.class);

    @Autowired
    private HomeAwayController homeAwayController;

    @Autowired
    private LightController lightController;

    @Scheduled(cron = "*/30 * * * * *")
    public void trigger() {
        if (homeAwayController.homestatus().getHome()) {
            // at home, turn off all lights
            for (Light light : lightController.lights()) {
                lightController.lightOff(light.getId());
            }
        } else {
            // not at home, so enable automatic light control
            LocalTime now = LocalTime.now();
            logger.info("now is: " + now);

            if (inBetween(now, "21:40-21:42")) {
                lightController.lightOn("8");
            } else if (inBetween(now, "21:43-21:45")) {
                lightController.lightOn("8");
            } else {
                lightController.lightOff("8");
            }
        }

    }

    private boolean inBetween(LocalTime now, String between) {
        int start_hour = Integer.parseInt(between.substring(0, 2));
        int start_min = Integer.parseInt(between.substring(3, 5));
        int end_hour = Integer.parseInt(between.substring(6, 8));
        int end_min = Integer.parseInt(between.substring(9, 11));

        return now.isAfter(LocalTime.of(start_hour, start_min)) && now.isBefore(LocalTime.of(end_hour, end_min));
    }
}
