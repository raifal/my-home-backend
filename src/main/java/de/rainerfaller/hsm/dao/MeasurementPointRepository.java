package de.rainerfaller.hsm.dao;

import de.rainerfaller.hsm.dto.MeasurementPoint;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementPointRepository extends CrudRepository<MeasurementPoint, Long> {
}
