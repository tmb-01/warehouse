package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Long> {
}
