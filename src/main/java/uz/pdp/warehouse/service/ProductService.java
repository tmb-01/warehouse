package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    AttachmentRepository attachmentRepository;
    MeasurementRepository measurementRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> get() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isPresent()) {
            return productById.get();
        }
        return new Product();
    }

    public Result add(ProductDto productDto) {
        Long categoryId = productDto.getCategoryId();
        Long attachmentId = productDto.getAttachmentId();
        Long measurementId = productDto.getMeasurementId();

        Optional<Category> categoryById = categoryRepository.findById(categoryId);
        Optional<Attachment> attachmentById = attachmentRepository.findById(attachmentId);
        Optional<Measurement> measurementById = measurementRepository.findById(measurementId);

        if (categoryById.isPresent()) {
            if (attachmentById.isPresent()) {
                if (measurementById.isPresent()) {
                    Product product = new Product();

                    Category category = categoryById.get();
                    Attachment attachment = attachmentById.get();
                    Measurement measurement = measurementById.get();

                    product.setName(productDto.getName());
                    product.setCode(productDto.getCode());
                    product.setCategory(category);
                    product.setAttachment(attachment);
                    product.setMeasurement(measurement);

                    productRepository.save(product);
                    return new Result("saved", true);
                }
                return new Result("not measurement exist with this id", false);
            }
            return new Result("not attachment exist with this id", false);
        }
        return new Result("not category exist with this id", false);
    }

    public Result delete(Long id) {
        boolean existsById = productRepository.existsById(id);
        if (existsById) {
            productRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("No data exist with this id", false);
    }

    public Result update(Long id, ProductDto productDto) {
        productDto.setId(id);

        Long attachmentId = productDto.getAttachmentId();
        Long categoryId = productDto.getCategoryId();
        Long measurementId = productDto.getMeasurementId();

        Optional<Category> categoryById = categoryRepository.findById(categoryId);
        Optional<Attachment> attachmentById = attachmentRepository.findById(attachmentId);
        Optional<Measurement> measurementById = measurementRepository.findById(measurementId);

        if (categoryById.isPresent()) {
            if (attachmentById.isPresent()) {
                if (measurementById.isPresent()) {
                    Product product = productRepository.findById(productDto.getId()).get();

                    Category category = categoryById.get();
                    Attachment attachment = attachmentById.get();
                    Measurement measurement = measurementById.get();

                    product.setName(productDto.getName());
                    product.setId(productDto.getId());
                    product.setCode(productDto.getCode());
                    product.setCategory(category);
                    product.setAttachment(attachment);
                    product.setMeasurement(measurement);

                    productRepository.save(product);
                    return new Result("saved", true);
                }
                return new Result("not measurement exist with this id", false);
            }
            return new Result("not attachment exist with this id", false);
        }
        return new Result("not category exist with this id", false);
    }
}
