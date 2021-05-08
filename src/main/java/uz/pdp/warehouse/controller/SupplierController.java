package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return supplierService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return supplierService.update(supplier);
    }
}
