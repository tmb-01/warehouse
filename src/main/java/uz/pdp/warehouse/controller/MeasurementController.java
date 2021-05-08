package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.add(measurement);
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.get();
    }

    @GetMapping("{id}")
    public Measurement getById(@PathVariable Long id) {
        return measurementService.getById(id);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return measurementService.delete(id);
    }

    @PutMapping("{id}")
    public Result edit(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.edit(id, measurement);
    }


}
