package com.thiagodeas.javastockmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodeas.javastockmanager.models.Category;
import com.thiagodeas.javastockmanager.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategory(Category category);
}
