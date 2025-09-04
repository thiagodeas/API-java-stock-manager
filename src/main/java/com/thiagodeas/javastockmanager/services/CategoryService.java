package com.thiagodeas.javastockmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thiagodeas.javastockmanager.dto.CategoryCreateDto;
import com.thiagodeas.javastockmanager.dto.CategoryUpdateDto;
import com.thiagodeas.javastockmanager.exceptions.CategoryAlreadyExistsException;
import com.thiagodeas.javastockmanager.exceptions.CategoryNotFoundException;
import com.thiagodeas.javastockmanager.models.Category;
import com.thiagodeas.javastockmanager.repositories.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(int id) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException());

        return category;
    }

    public Category findCategoryByName(String name) {
        Category category = this.categoryRepository.findByName(name);

        if (category == null) {
            throw new CategoryNotFoundException();
        }
        
        return category;
    }

    public List<Category> findCategoryByNameContaining(String name) {
        List<Category> categories = this.categoryRepository.findByNameContaining(name);

        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("Nenhuma categoria encontrada.");
        }

        return categories;
    }

    public Category createCategory(CategoryCreateDto dto) {
        Category existingCategory = this.categoryRepository.findByName(dto.name());

        if (existingCategory != null) {
            throw new CategoryAlreadyExistsException();
        }

        Category newCategory = new Category(dto.name());
        return this.categoryRepository.save(newCategory);
    }

    public Category updateCategory(int id, CategoryUpdateDto dto) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException());

        if (dto.name() != null && !dto.name().isBlank()) {
            category.setName(dto.name());
        }

        return this.categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException());

        this.categoryRepository.delete(category);
    }
}
