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

            Map<String, Boolean> l = new HashMap<>();
            l.put("8", false);
            l.put("9", false);
            l.put("10", false);

            Map<String, String> plan = new HashMap<>();
            plan.put("8", "18:00-18:45");
            plan.put("8", "19:00-19:45");
            plan.put("8", "20:30-21:30");
            plan.put("8", "06:30-07:00");

            plan.put("10", "18:55-19:05");
            plan.put("10", "20:05-22:05");

            plan.put("9", "17:02-18:10");
            plan.put("9", "22:15-23:10");
            plan.put("9", "06:50-7:40");

            for ( String id: plan.keySet())
            {
                if (withinPeriod(now, plan.get(id)))
                    l.put(id, true);
            }

            for (String id: l.keySet())
            {
                if ( l.get(id))
                    lightController.lightOn(id);
                else
                    lightController.lightOff(id);
            }
        }

    }

    private boolean withinPeriod(LocalTime now, String between) {
        int start_hour = Integer.parseInt(between.substring(0, 2));
        int start_min = Integer.parseInt(between.substring(3, 5));
        int end_hour = Integer.parseInt(between.substring(6, 8));
        int end_min = Integer.parseInt(between.substring(9, 11));

        return now.isAfter(LocalTime.of(start_hour, start_min)) && now.isBefore(LocalTime.of(end_hour, end_min));
    }
}
