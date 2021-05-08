package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.WareHouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody WareHouse wareHouse) {
        return warehouseService.add(wareHouse);
    }

    @GetMapping
    public List<WareHouse> getAll() {
        return warehouseService.get();
    }

    @GetMapping("{id}")
    public WareHouse getById(@PathVariable Long id) {
        return warehouseService.getById(id);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return warehouseService.delete(id);
    }

    @PutMapping("{id}")
    public Result edit(@PathVariable Long id, @RequestBody WareHouse wareHouse) {
        return warehouseService.edit(id, wareHouse);
    }


}
