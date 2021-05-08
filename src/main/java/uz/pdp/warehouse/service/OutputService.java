package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.WareHouse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    OutputRepository outputRepository;
    WarehouseRepository warehouseRepository;
    ClientRepository clientRepository;
    CurrencyRepository currencyRepository;

    @Autowired
    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository, ClientRepository clientRepository, CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Output> getAll() {
        return outputRepository.findAll();
    }

    public Output getById(Long id) {
        Optional<Output> outputById = outputRepository.findById(id);
        if (outputById.isPresent()) {
            return outputRepository.findById(id).get();
        }
        return new Output();
    }

    public Result add(OutputDto outputDto) {
        Optional<WareHouse> wareHouseById = warehouseRepository.findById(outputDto.getWareHouseId());
        Optional<Client> clientById = clientRepository.findById(outputDto.getClientId());
        Optional<Currency> currencyById = currencyRepository.findById(outputDto.getCurrencyId());

        if (wareHouseById.isPresent()) {
            if (clientById.isPresent()) {
                if (currencyById.isPresent()) {
                    Output output = new Output();

                    WareHouse wareHouse = wareHouseById.get();
                    Client client = clientById.get();
                    Currency currency = currencyById.get();

                    output.setCode(outputDto.getCode());
                    output.setDate(outputDto.getDate());
                    output.setFactureNumber(outputDto.getFactureNumber());
                    output.setWareHouse(wareHouse);
                    output.setClient(client);
                    output.setCurrency(currency);

                    outputRepository.save(output);
                    return new Result("Output saved", true);
                }
                return new Result("currency with this id not exist", false);
            }
            return new Result("client with this id not exist", false);
        }
        return new Result("warehouse with this id not exist", false);
    }

    public Result delete(Long id) {
        boolean existsById = outputRepository.existsById(id);
        if (existsById) {
            outputRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not output exist with this id", false);
    }

    public Result update(Long id, OutputDto outputDto) {
        outputDto.setId(id);
        Optional<WareHouse> wareHouseById = warehouseRepository.findById(outputDto.getWareHouseId());
        Optional<Client> clientById = clientRepository.findById(outputDto.getClientId());
        Optional<Currency> currencyById = currencyRepository.findById(outputDto.getCurrencyId());

        if (wareHouseById.isPresent()) {
            if (clientById.isPresent()) {
                if (currencyById.isPresent()) {
                    Optional<Output> outputById = outputRepository.findById(outputDto.getId());
                    if (outputById.isPresent()) {
                        Output output = outputById.get();

                        WareHouse wareHouse = wareHouseById.get();
                        Client client = clientById.get();
                        Currency currency = currencyById.get();

                        output.setId(outputDto.getId());
                        output.setCode(outputDto.getCode());
                        output.setDate(outputDto.getDate());
                        output.setFactureNumber(outputDto.getFactureNumber());
                        output.setWareHouse(wareHouse);
                        output.setClient(client);
                        output.setCurrency(currency);

                        outputRepository.save(output);
                        return new Result("Output saved", true);
                    }
                    return new Result("output not exist with this id", false);
                }
                return new Result("currency with this id not exist", false);
            }
            return new Result("client with this id not exist", false);
        }
        return new Result("warehouse with this id not exist", false);
    }

}
