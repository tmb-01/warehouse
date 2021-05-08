package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/output-product")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public List<OutputProduct> getAll() {
        return outputProductService.getAll();
    }

    @GetMapping("{id}")
    public OutputProduct getById(@PathVariable Long id) {
        return outputProductService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody OutputProductDto output) {
        return outputProductService.add(output);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody OutputProductDto output) {
        return outputProductService.update(id, output);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return outputProductService.delete(id);
    }
}
