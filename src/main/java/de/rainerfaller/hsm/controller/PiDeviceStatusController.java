package de.rainerfaller.hsm.controller;

import de.rainerfaller.hsm.lightcontrol.pi.PiResponse;
import de.rainerfaller.hsm.service.MpiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
