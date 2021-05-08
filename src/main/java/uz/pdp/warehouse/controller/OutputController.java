package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @GetMapping
    public List<Output> getAll() {
        return outputService.getAll();
    }

    @GetMapping("{id}")
    public Output getById(@PathVariable Long id) {
        return outputService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto) {
        return outputService.add(outputDto);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody OutputDto outputDto) {
        return outputService.update(id, outputDto);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return outputService.delete(id);
    }
}
