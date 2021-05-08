package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Supplier;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
