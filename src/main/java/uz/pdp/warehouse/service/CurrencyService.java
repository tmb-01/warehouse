package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Currency getById(Long id) {
        boolean existsById = currencyRepository.existsById(id);
        if (existsById) {
            return currencyRepository.findById(id).get();
        }
        return new Currency();
    }

    public Result add(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName) {
            return new Result("currency with this id exist", false);
        }
        currencyRepository.save(currency);
        return new Result("currency saved", true);
    }

    public Result delete(Long id) {
        boolean existsById = currencyRepository.existsById(id);
        if (existsById) {
            currencyRepository.deleteById(id);
            return new Result("currency deleted", true);
        }
        return new Result("not currency exist with this id", false);
    }

    public Result update(Currency currency) {
        Optional<Currency> byId = currencyRepository.findById(currency.getId());
        if (byId.isPresent()) {
            Currency currency1 = byId.get();
            currency1.setName(currency.getName());
            currency1.setActive(currency.isActive());
            currencyRepository.save(currency1);
            return new Result("updated", true);
        }
        return new Result("not currency exist with this id", false);
    }
}