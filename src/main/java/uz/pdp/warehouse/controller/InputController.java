package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @GetMapping
    public List<Input> getAll() {
        return inputService.getAll();
    }

    @GetMapping("{id}")
    public Input getById(@PathVariable Long id) {
        return inputService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody InputDto input) {
        return inputService.add(input);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody InputDto inputDto) {
        return inputService.update(id, inputDto);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return inputService.delete(id);
    }
}
