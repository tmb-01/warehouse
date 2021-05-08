package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.WareHouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    //    add
    public Result add(WareHouse wareHouse) {

        warehouseRepository.save(wareHouse);
        return new Result("Saqlandi", true);
    }

    //    get all
    public List<WareHouse> get() {
        return warehouseRepository.findAll();
    }

    //    get by id
    public WareHouse getById(Long id) {
        Optional<WareHouse> measurementById = warehouseRepository.findById(id);
        if (measurementById.isPresent()) {
            return measurementById.get();
        }
        return new WareHouse();
    }

    //    edit
    public Result edit(Long id, WareHouse wareHouse) {
        Optional<WareHouse> findById = warehouseRepository.findById(id);
        if (findById.isPresent()) {
            WareHouse wareHouse1 = findById.get();
            wareHouse1.setActive(wareHouse.isActive());
            wareHouse1.setName(wareHouse.getName());
            warehouseRepository.save(wareHouse1);
            return new Result("edited", true);
        }
        return new Result("bunday ombor mavjud emas", false);
    }

    //    delete
    public Result delete(Long id) {
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not exist", false);
    }

}
