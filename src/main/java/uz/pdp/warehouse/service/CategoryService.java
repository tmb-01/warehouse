package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        boolean existsById = categoryRepository.existsById(id);
        if (existsById) {
            return categoryRepository.findById(id).get();
        }
        return new Category();
    }

    public Result add(CategoryDto categoryDto) {
        Category category = new Category();
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        boolean existsById = categoryRepository.existsById(categoryDto.getParentCategoryId());
        if (existsByName) {
            return new Result("category with this name is exist", false);
        } else if (existsById) {
            return new Result("category with this id is exist", false);
        }
        Optional<Category> byId = categoryRepository.findById(categoryDto.getParentCategoryId());
        category.setName(categoryDto.getName());
        category.setParentCategory(byId.get());
        categoryRepository.save(category);
        return new Result("client saved", true);
    }

    public Result delete(Long id) {
        boolean existsById = categoryRepository.existsById(id);
        if (existsById) {
            categoryRepository.deleteById(id);
            return new Result("client deleted", true);
        }
        return new Result("not client exist with this id", false);
    }

    public Result update(CategoryDto categoryDto) {
        Optional<Category> byId = categoryRepository.findById(categoryDto.getId());
        Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (byId.isPresent()) {
            if (parentCategory.isPresent() || categoryDto.getParentCategoryId() == null) {
                Category category = byId.get();
                category.setName(categoryDto.getName());
                category.setActive(categoryDto.isActive());
                category.setParentCategory(parentCategory.get());
                categoryRepository.save(category);
                return new Result("updated", true);
            }
            return new Result("parent id with " + categoryDto.getParentCategoryId() + " not exist", false);
        }
        return new Result("not client exist with this id", false);
    }
}