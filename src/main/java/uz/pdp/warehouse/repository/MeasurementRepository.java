package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    boolean existsByName(String name);
}
