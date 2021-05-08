package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Long> {
}
