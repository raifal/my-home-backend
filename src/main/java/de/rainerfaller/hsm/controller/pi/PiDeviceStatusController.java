package de.rainerfaller.hsm.controller.pi;

import de.rainerfaller.hsm.lightcontrol.pi.PiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/hsm/pidevicestatus")
public class PiDeviceStatusController {

    private static final Logger logger = LoggerFactory.getLogger(PiDeviceStatusController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody PiResponse content, UriComponentsBuilder ucBuilder) {

        logger.info("Recived pi response " + content);

        return new ResponseEntity<PiResponse>(HttpStatus.NO_CONTENT);
    }
}