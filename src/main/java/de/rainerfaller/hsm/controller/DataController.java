package de.rainerfaller.hsm.controller;

import de.rainerfaller.hsm.dao.MeasurementPointRepository;
import de.rainerfaller.hsm.dao.WaterLevelRepository;
import de.rainerfaller.hsm.dto.MeasurementPoint;
import de.rainerfaller.hsm.dto.WaterLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller

public class DataController {

    @Autowired
    private MeasurementPointRepository measurementPointRepository;

    @Autowired
    private WaterLevelRepository waterLevelRepository;

    public DataController() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/measurementpoints")
    public @ResponseBody
    Iterable<MeasurementPoint> measurementpoints(HttpServletRequest request) {
        return measurementPointRepository.findAll();

    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/waterlevel")
    public @ResponseBody
    Iterable<WaterLevel> waterlevel(HttpServletRequest request) {
        return waterLevelRepository.findAll();

    }
}
