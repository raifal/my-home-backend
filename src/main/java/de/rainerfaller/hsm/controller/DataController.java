package de.rainerfaller.hsm.controller;

import de.rainerfaller.hsm.dao.MeasurementPointRepository;
import de.rainerfaller.hsm.dao.SensorRepository;
import de.rainerfaller.hsm.dao.WaterLevelRepository;
import de.rainerfaller.hsm.dto.MeasurementPoint;
import de.rainerfaller.hsm.dto.WaterLevel;
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
public class DataController {

    @Autowired
    private MeasurementPointRepository measurementPointRepository;

    @Autowired
    private WaterLevelRepository waterLevelRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public DataController() {
    }

    // http://localhost:8080/hsm/measurementpoints/from/2017-10-15/to/2017-10-15/
    @RequestMapping(method = RequestMethod.GET, path = "/hsm/measurementpoints/from/{from}/to/{to}")
    public @ResponseBody
    Iterable<MeasurementPoint> measurementpoints(
            @PathVariable("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @PathVariable("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        // set endtime. TODO, maybe better using Java 8 or split database table into date and time?
        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(toDate);
        gc.set(Calendar.HOUR_OF_DAY, 23);
        gc.set(Calendar.MINUTE, 59);
        gc.set(Calendar.SECOND, 59);
        gc.set(Calendar.MILLISECOND, 999);

        return measurementPointRepository.findByCreatedBetween(fromDate, gc.getTime());

    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/waterlevel")
    public @ResponseBody
    Iterable<WaterLevel> waterlevel(HttpServletRequest request) {
        return waterLevelRepository.findAll();

    }
}
