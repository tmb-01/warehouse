package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CategoryService;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody CategoryDto category) {
        return categoryService.add(category);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody CategoryDto category) {
        category.setId(id);
        return categoryService.update(category);
    }
}
