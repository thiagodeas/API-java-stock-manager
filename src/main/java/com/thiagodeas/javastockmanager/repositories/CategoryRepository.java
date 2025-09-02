package com.thiagodeas.javastockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodeas.javastockmanager.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
