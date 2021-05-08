package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.get();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody ProductDto product) {
        return productService.add(product);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }
}
