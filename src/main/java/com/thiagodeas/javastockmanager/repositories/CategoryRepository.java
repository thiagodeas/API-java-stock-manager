package com.thiagodeas.javastockmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodeas.javastockmanager.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    List<Category> findByNameContaining(String name);
}
