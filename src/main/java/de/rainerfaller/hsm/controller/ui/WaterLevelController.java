package de.rainerfaller.hsm.controller.ui;

import de.rainerfaller.hsm.dao.MeasurementPointRepository;
import de.rainerfaller.hsm.dao.SensorRepository;
import de.rainerfaller.hsm.dao.WaterLevelRepository;
import de.rainerfaller.hsm.dto.MeasurementPoint;
import de.rainerfaller.hsm.dto.WaterLevel;
import de.rainerfaller.hsm.service.PiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class WaterLevelController {

    @Autowired
    private WaterLevelRepository waterLevelRepository;

    public WaterLevelController() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/waterlevel")
    public @ResponseBody
    Iterable<WaterLevel> waterlevel(HttpServletRequest request) {
        return waterLevelRepository.findAll();

    }


}
