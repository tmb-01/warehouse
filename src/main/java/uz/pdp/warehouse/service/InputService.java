package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.WareHouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    InputRepository inputRepository;
    WarehouseRepository warehouseRepository;
    SupplierRepository supplierRepository;
    CurrencyRepository currencyRepository;

    @Autowired
    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Input> getAll() {
        return inputRepository.findAll();
    }

    public Input getById(Long id) {
        Optional<Input> inputById = inputRepository.findById(id);
        if (inputById.isPresent()) {
            return inputRepository.findById(id).get();
        }
        return new Input();
    }

    public Result add(InputDto inputDto) {
        Optional<WareHouse> wareHouseById = warehouseRepository.findById(inputDto.getWareHouseId());
        Optional<Supplier> supplierById = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> currencyById = currencyRepository.findById(inputDto.getCurrencyId());

        if (wareHouseById.isPresent()) {
            if (supplierById.isPresent()) {
                if (currencyById.isPresent()) {
                    Input input = new Input();

                    WareHouse wareHouse = wareHouseById.get();
                    Supplier supplier = supplierById.get();
                    Currency currency = currencyById.get();

                    input.setCode(inputDto.getCode());
                    input.setDate(inputDto.getDate());
                    input.setFactureNumber(inputDto.getFactureNumber());
                    input.setWareHouse(wareHouse);
                    input.setSupplier(supplier);
                    input.setCurrency(currency);

                    inputRepository.save(input);
                    return new Result("Input saved", true);
                }
                return new Result("currency with this id not exist", false);
            }
            return new Result("supplier with this id not exist", false);
        }
        return new Result("warehouse with this id not exist", false);
    }

    public Result delete(Long id) {
        boolean existsById = inputRepository.existsById(id);
        if (existsById) {
            inputRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not input exist with this id", false);
    }

    public Result update(Long id, InputDto inputDto) {
        inputDto.setId(id);
        Optional<WareHouse> wareHouseById = warehouseRepository.findById(inputDto.getWareHouseId());
        Optional<Supplier> supplierById = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> currencyById = currencyRepository.findById(inputDto.getCurrencyId());

        if (wareHouseById.isPresent()) {
            if (supplierById.isPresent()) {
                if (currencyById.isPresent()) {
                    Optional<Input> inputById = inputRepository.findById(inputDto.getId());
                    if (inputById.isPresent()) {
                        Input input = inputById.get();

                        WareHouse wareHouse = wareHouseById.get();
                        Supplier supplier = supplierById.get();
                        Currency currency = currencyById.get();

                        input.setId(inputDto.getId());
                        input.setCode(inputDto.getCode());
                        input.setDate(inputDto.getDate());
                        input.setFactureNumber(inputDto.getFactureNumber());
                        input.setWareHouse(wareHouse);
                        input.setSupplier(supplier);
                        input.setCurrency(currency);

                        inputRepository.save(input);
                        return new Result("Input saved", true);
                    }
                    return new Result("input not exist with this id",false);
                }
                return new Result("currency with this id not exist", false);
            }
            return new Result("supplier with this id not exist", false);
        }
        return new Result("warehouse with this id not exist", false);
    }

}
