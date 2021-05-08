package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
