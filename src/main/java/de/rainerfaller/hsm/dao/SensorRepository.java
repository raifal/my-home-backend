package de.rainerfaller.hsm.dao;

import de.rainerfaller.hsm.dto.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, String> {
    List<Sensor> findByAddress(String address);
}
