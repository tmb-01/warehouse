package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.WareHouse;

public interface WarehouseRepository extends JpaRepository<WareHouse,Long> {
}
