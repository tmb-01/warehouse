package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    InputProductRepository inputProductRepository;
    InputRepository inputRepository;
    ProductRepository productRepository;

    @Autowired
    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public List<InputProduct> getAll() {
        return inputProductRepository.findAll();
    }

    public InputProduct getById(Long id) {
        Optional<InputProduct> inputById = inputProductRepository.findById(id);
        if (inputById.isPresent()) {
            return inputProductRepository.findById(id).get();
        }
        return new InputProduct();
    }

    public Result add(InputProductDto inputProductDto) {
        Optional<Input> inputById = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> productById = productRepository.findById(inputProductDto.getProductId());

        if (inputById.isPresent()) {
            if (productById.isPresent()) {
                InputProduct inputProduct = new InputProduct();

                Input input = inputById.get();
                Product product = productById.get();

                inputProduct.setPrice(inputProductDto.getPrice());
                inputProduct.setAmount(inputProductDto.getAmount());
                inputProduct.setExpireDate(inputProductDto.getExpireDate());
                inputProduct.setInput(input);
                inputProduct.setProduct(product);

                inputProductRepository.save(inputProduct);
                return new Result("Input saved", true);
            }
            return new Result("currency with this id not exist", false);
        }
        return new Result("supplier with this id not exist", false);
    }

    public Result delete(Long id) {
        boolean existsById = inputProductRepository.existsById(id);
        if (existsById) {
            inputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not input product exist with this id", false);
    }

    public Result update(Long id, InputProductDto inputProductDto) {
        inputProductDto.setId(id);
        Optional<Input> inputById = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> productById = productRepository.findById(inputProductDto.getProductId());

        if (inputById.isPresent()) {
            if (productById.isPresent()) {
                Optional<InputProduct> inputProductById = inputProductRepository.findById(inputProductDto.getId());
                if (inputProductById.isPresent()) {
                    InputProduct inputProduct = inputProductById.get();

                    Input input = inputById.get();
                    Product product = productById.get();

                    inputProduct.setPrice(inputProductDto.getPrice());
                    inputProduct.setAmount(inputProductDto.getAmount());
                    inputProduct.setExpireDate(inputProductDto.getExpireDate());
                    inputProduct.setInput(input);
                    inputProduct.setProduct(product);

                    inputProductRepository.save(inputProduct);
                    return new Result("Input saved", true);
                }
                return new Result("not input product exist with this id", false);
            }
            return new Result("currency with this id not exist", false);
        }
        return new Result("supplier with this id not exist", false);
    }

}
