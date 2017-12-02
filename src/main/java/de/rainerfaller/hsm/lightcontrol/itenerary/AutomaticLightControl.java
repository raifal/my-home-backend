package de.rainerfaller.hsm.lightcontrol.itenerary;

import de.rainerfaller.hsm.controller.ui.HomeAwayController;
import de.rainerfaller.hsm.controller.ui.LightController;
import de.rainerfaller.hsm.dao.HomeAwayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AutomaticLightControl {
    private static final Logger logger = LoggerFactory.getLogger(AutomaticLightControl.class);

    @Autowired
    private HomeAwayController homeAwayController;

    @Autowired
    private LightController lightController;

    @Scheduled(cron = "*/30 * * * * *")
    public void trigger()
    {
        if ( homeAwayController.homestatus().getHome() )
        {
            // at home, turn off all lights
            for ( String id: lightController.lights().keySet())
            {
                lightController.lightOff(id);
            }
        }
        else
        {
            // not at home, so enable automatic light control
            lightController.lightOn("8");
        }

    }
}
