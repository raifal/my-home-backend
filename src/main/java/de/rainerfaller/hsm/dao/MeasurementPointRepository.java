package de.rainerfaller.hsm.dao;

import de.rainerfaller.hsm.dto.MeasurementPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MeasurementPointRepository extends CrudRepository<MeasurementPoint, Long> {
    List<MeasurementPoint> findByCreatedBetween(Date start, Date end);
}
