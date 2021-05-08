package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    //    add
    public Result add(Measurement measurement) {

        boolean existsByName = measurementRepository.existsByName(measurement.getName());

        if (existsByName) {
            return new Result("Bunday ulchov mavjud", false);
        }
        measurementRepository.save(measurement);
        return new Result("Saqlandi", true);
    }

    //    get all
    public List<Measurement> get() {
        return measurementRepository.findAll();
    }

    //    get by id
    public Measurement getById(Long id) {
        Optional<Measurement> measurementById = measurementRepository.findById(id);
        if (measurementById.isPresent()) {
            return measurementById.get();
        }
        return new Measurement();
    }

    //    edit
    public Result edit(Long id, Measurement measurement) {
        Optional<Measurement> findById = measurementRepository.findById(id);
        if (findById.isPresent()) {
            Measurement measurement1 = findById.get();
            measurement1.setActive(measurement.isActive());
            measurement1.setName(measurement.getName());
            measurementRepository.save(measurement1);
            return new Result("edited", true);
        }
        return new Result("bunday ulchov mavjud emas", false);
    }

    //    delete
    public Result delete(Long id) {
        if (measurementRepository.existsById(id)) {
            measurementRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not exist", false);
    }
}
