package de.rainerfaller.hsm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rfaller on 30.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class MpiServiceTest {


    @Autowired
    private MpiService mpiService;

    @Test
    public void testRaw() throws IOException, URISyntaxException {

        List<String> rawData = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource("/MpiRawData.txt").toURI()))) {
            rawData = stream.collect(Collectors.toList());
        }

        mpiService.processRawTemperatureData(rawData);
    }
}