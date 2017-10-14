package de.rainerfaller.hsm.service;

import de.rainerfaller.hsm.dto.Client;
import de.rainerfaller.hsm.dto.MeasurementPoint;
import de.rainerfaller.hsm.dto.WaterLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Calendar.SECOND;

/**
 * Created by rfaller on 30.03.2017.
 */
@Service
public class MpiService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MpiService.class);

    /**
     * Parses and stores the values send from the arduino, mainly temperature values
     *
     * @param rawValues mainly temperature values
     */
    public void processRawTemperatureData(List<String> rawValues) {

        String dbVersion = jdbcTemplate.queryForObject("select version()", String.class);
        logger.info("DB Version: " + dbVersion);

        Date now = new Date();
        Client client = null;
        WaterLevel waterLevel = null;
        List<MeasurementPoint> measurementPoints = new ArrayList<>();

        for (String c : rawValues.stream()
                .filter(c -> c.startsWith("hsm-"))
                .map(c -> c.substring(4))
                .collect(Collectors.toList())) {

            String subject = c.substring(0, c.indexOf("->"));
            String propertiesString = c.substring(c.indexOf("->") + 2);

            Map<String, String> properties = new HashMap<>();
            for (String p : propertiesString.split(":")) {
                String key = p.substring(0, p.indexOf("="));
                String value = p.substring(p.indexOf("=") + 1);
                properties.put(key, value);
            }

            if ("c".equals(subject)) {
                client = new Client(properties.get("client"));
            }

            if ("w".equals(subject)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                waterLevel = new WaterLevel(
                        calendar.getTime(),
                        Integer.valueOf(properties.get("lower_voltage")),
                        Integer.valueOf(properties.get("upper_voltage")));
            }

            if (subject.startsWith("m")) {
                int offsetSeconds = Integer.parseInt(properties.get("ts"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(SECOND, offsetSeconds);
                measurementPoints.add(
                        new MeasurementPoint(
                                calendar.getTime(),
                                properties.get("addr"),
                                Integer.parseInt(properties.get("value")) / 100f));
            }

        }

        // add client to each record. Can't be done above as the order of data sets is unclear.
        // Now, we are sure to have all records
        if (waterLevel != null) {
            waterLevel.setClient(client);
        }

        for (MeasurementPoint mp : measurementPoints) {
            mp.setClient(client);
        }

        for (MeasurementPoint mp : measurementPoints) {
            System.out.println("mp-" + mp);
        }
        //store(measurementPoints);
        //store(waterLevel);
    }
}
