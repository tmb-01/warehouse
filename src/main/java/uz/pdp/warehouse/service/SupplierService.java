package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier getById(Long id) {
        boolean existsById = supplierRepository.existsById(id);
        if (existsById) {
            return supplierRepository.findById(id).get();
        }
        return new Supplier();
    }

    public Result add(Supplier supplier) {
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("supplier with this phone number is exist", false);
        }
        supplierRepository.save(supplier);
        return new Result("supplier saved", true);
    }

    public Result delete(Long id) {
        boolean existsById = supplierRepository.existsById(id);
        if (existsById) {
            supplierRepository.deleteById(id);
            return new Result("supplier deleted", true);
        }
        return new Result("not supplier exist with this id", false);
    }

    public Result update(Supplier supplier) {
        Optional<Supplier> byId = supplierRepository.findById(supplier.getId());
        if (byId.isPresent()) {
            Supplier supplier1 = byId.get();
            supplier1.setName(supplier.getName());
            supplier1.setActive(supplier.isActive());
            supplierRepository.save(supplier1);
            return new Result("updated", true);
        }
        return new Result("not supplier exist with this id", false);
    }
}