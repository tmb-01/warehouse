package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    OutputProductRepository outputProductRepository;
    OutputRepository outputRepository;
    ProductRepository productRepository;

    @Autowired
    public OutputProductService(OutputProductRepository outputProductRepository, OutputRepository outputRepository, ProductRepository productRepository) {
        this.outputProductRepository = outputProductRepository;
        this.outputRepository = outputRepository;
        this.productRepository = productRepository;
    }

    public List<OutputProduct> getAll() {
        return outputProductRepository.findAll();
    }

    public OutputProduct getById(Long id) {
        Optional<OutputProduct> outputById = outputProductRepository.findById(id);
        if (outputById.isPresent()) {
            return outputProductRepository.findById(id).get();
        }
        return new OutputProduct();
    }

    public Result add(OutputProductDto outputProductDto) {
        Optional<Output> outputById = outputRepository.findById(outputProductDto.getOutputId());
        Optional<Product> productById = productRepository.findById(outputProductDto.getProductId());

        if (outputById.isPresent()) {
            if (productById.isPresent()) {
                OutputProduct outputProduct = new OutputProduct();

                Output output = outputById.get();
                Product product = productById.get();

                outputProduct.setPrice(outputProductDto.getPrice());
                outputProduct.setAmount(outputProductDto.getAmount());
                outputProduct.setOutput(output);
                outputProduct.setProduct(product);

                outputProductRepository.save(outputProduct);
                return new Result("Output saved", true);
            }
            return new Result("product with this id not exist", false);
        }
        return new Result("output with this id not exist", false);
    }

    public Result delete(Long id) {
        boolean existsById = outputProductRepository.existsById(id);
        if (existsById) {
            outputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("not output product exist with this id", false);
    }

    public Result update(Long id, OutputProductDto outputProductDto) {
        outputProductDto.setId(id);
        Optional<Output> outputById = outputRepository.findById(outputProductDto.getOutputId());
        Optional<Product> productById = productRepository.findById(outputProductDto.getProductId());

        if (outputById.isPresent()) {
            if (productById.isPresent()) {
                Optional<OutputProduct> outputProductById = outputProductRepository.findById(outputProductDto.getId());
                if (outputProductById.isPresent()) {
                    OutputProduct outputProduct = outputProductById.get();

                    Output output = outputById.get();
                    Product product = productById.get();

                    outputProduct.setPrice(outputProductDto.getPrice());
                    outputProduct.setAmount(outputProductDto.getAmount());
                    outputProduct.setOutput(output);
                    outputProduct.setProduct(product);

                    outputProductRepository.save(outputProduct);
                    return new Result("Output saved", true);
                }
                return new Result("not output product exist with this id", false);
            }
            return new Result("product with this id not exist", false);
        }
        return new Result("output with this id not exist", false);
    }

}
