package de.rainerfaller.hsm.controller.ui;

import de.rainerfaller.hsm.dao.SensorRepository;
import de.rainerfaller.hsm.dto.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hsm/sensor")
@Service
public class TemperatureSensorController {
    @Autowired
    private SensorRepository sensorRepository;

    public TemperatureSensorController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Sensor> readSensors() {
        return sensorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sensorAddress}")
    public Sensor readSensor(@PathVariable String sensorAddress) {
        List<Sensor> found = sensorRepository.findByAddress(sensorAddress);
        if (found == null || found.size() != 1) {
            throw new IllegalArgumentException("sensor address not in repository");
        }
        return found.get(0);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Sensor update(@RequestBody Sensor input) {
        // make sure it exists
        Sensor s = readSensor(input.getAddress());
        input.setSensorId(s.getSensorId());
        sensorRepository.save(input);
        return readSensor(input.getAddress());
    }
}
