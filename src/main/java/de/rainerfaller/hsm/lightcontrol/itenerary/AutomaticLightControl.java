package de.rainerfaller.hsm.lightcontrol.itenerary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AutomaticLightControl {
    private static final Logger logger = LoggerFactory.getLogger(AutomaticLightControl.class);

    @Scheduled(cron = "*/25 * * * * *")
    public void trigger()
    {

    }
}
