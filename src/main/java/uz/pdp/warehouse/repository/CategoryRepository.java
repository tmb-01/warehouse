package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Client;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
