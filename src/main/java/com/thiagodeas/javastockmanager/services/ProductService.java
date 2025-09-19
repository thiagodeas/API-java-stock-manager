package com.thiagodeas.javastockmanager.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thiagodeas.javastockmanager.dto.ProductCreateDto;
import com.thiagodeas.javastockmanager.dto.ProductUpdateDto;
import com.thiagodeas.javastockmanager.exceptions.CategoryNotFoundException;
import com.thiagodeas.javastockmanager.exceptions.ProductAlreadyExistsException;
import com.thiagodeas.javastockmanager.exceptions.ProductNotFoundException;
import com.thiagodeas.javastockmanager.models.Category;
import com.thiagodeas.javastockmanager.models.Product;
import com.thiagodeas.javastockmanager.repositories.CategoryRepository;
import com.thiagodeas.javastockmanager.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService() {}

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findByName(String name) {
        Product product = this.productRepository.findByName(name);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return product;
    }

    public List<Product> findByNameContaining(String name) {
        List<Product> products = this.productRepository.findByNameContaining(name);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado.");
        }

        return products;
    }

    public List<Product> findByCategory(Category category) {
        List<Product> products = this.productRepository.findByCategory(category);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado.");
        }

        return products;
    }

    public Product create(ProductCreateDto dto) {
        String normalizedName = dto.name().trim();
        Product existingProduct = this.productRepository.findByName(normalizedName);
        Category existingCategory = this.categoryRepository.findById(dto.categoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Não existe nenhuma categoria com o ID recebido."));

        if (existingProduct != null) {
            throw new ProductAlreadyExistsException("Já existe um produto com o nome informado.");
        }

        Product newProduct = new Product(normalizedName, existingCategory, dto.price());
        return this.productRepository.save(newProduct);
    }

    public Product update(Long id, ProductUpdateDto dto) {
        String normalizedName = dto.name().trim();

        Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Não existe nenhum produto com o ID recebido."));

        Category category = this.categoryRepository.findById(dto.categoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Não existe nenhuma categoria com o ID recebido."));

        if (dto.name() != null && !dto.name().isBlank()) {
            Product existingProduct = this.productRepository.findByName(normalizedName);
            if (existingProduct != null && !existingProduct.getId().equals(id)) {
                throw new ProductAlreadyExistsException("Já existe um produto cadastrado com o nome informado.");
            }
            product.setName(normalizedName);
        }

        if (dto.price() != null && dto.price().compareTo(BigDecimal.ZERO) > 0) {
            product.setPrice(dto.price());
        }

        product.setCategory(category);
        return this.productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException());

        this.productRepository.delete(product);
    }

}
