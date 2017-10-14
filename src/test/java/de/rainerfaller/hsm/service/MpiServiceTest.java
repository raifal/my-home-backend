package de.rainerfaller.hsm.service;

import org.junit.Test;

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
public class MpiServiceTest {
    @Test
    public void testRaw() throws IOException, URISyntaxException {
        List<String> rawData = new ArrayList<>();


        try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource("/src/test/resources/MpiRawData.txt").toURI()))) {
            rawData = stream.collect(Collectors.toList());
        }

        new MpiService().processRawTemperatureData(rawData);
    }
}