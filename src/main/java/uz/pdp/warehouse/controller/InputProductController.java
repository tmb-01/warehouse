package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputProductService;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input-product")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public List<InputProduct> getAll() {
        return inputProductService.getAll();
    }

    @GetMapping("{id}")
    public InputProduct getById(@PathVariable Long id) {
        return inputProductService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody InputProductDto input) {
        return inputProductService.add(input);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody InputProductDto input) {
        return inputProductService.update(id, input);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return inputProductService.delete(id);
    }
}
