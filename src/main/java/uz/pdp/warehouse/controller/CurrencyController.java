package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAll();
    }

    @GetMapping("{id}")
    public Currency getById(@PathVariable Long id) {
        return currencyService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody Currency currency) {
        return currencyService.add(currency);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return currencyService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody Currency currency) {
        currency.setId(id);
        return currencyService.update(currency);
    }
}
