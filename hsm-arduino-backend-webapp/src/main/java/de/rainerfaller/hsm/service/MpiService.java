package de.rainerfaller.hsm.service;

import de.rainerfaller.hsm.dto.Client;
import de.rainerfaller.hsm.dto.MeasurementPoint;
import de.rainerfaller.hsm.dto.WaterLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Calendar.SECOND;

/**
 * Created by rfaller on 30.03.2017.
 */
@Component
@ComponentScan
public class MpiService {
    public MpiService() {

    }

    // TODO das geht irgendwie nicht...
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * Parses and stores the values send from the arduino, mainly temperature values
     *
     * @param rawValues mainly temperature values
     */
    public void processRawTemperatureData(List<String> rawValues) {

        System.out.println("hi there " + new Date() + "P" + jdbcPassword);

        if (true)
            return;
        try {
            final String url = "jdbc:postgresql://192.168.99.100:5432/postgres";
            Connection conn = DriverManager.getConnection(url, "postgres", jdbcPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select version()");
            rs.next();
            System.out.println("rs+" + rs.getString(1));

            System.out.println("hi there2 " + new Date());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hi there3 " + new Date());


        Date now = new Date();
        Client client = null;
        WaterLevel waterLevel = null;
        List<MeasurementPoint> measurementPoints = new ArrayList<>();

        for (String c : rawValues.stream()
                .filter(c -> c.startsWith("http_hsm"))
                .map(c -> c.substring(9))
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
        waterLevel.setClient(client);
        for (MeasurementPoint mp : measurementPoints) {
            mp.setClient(client);
        }

        //store(measurementPoints);
        //store(waterLevel);
    }
}
