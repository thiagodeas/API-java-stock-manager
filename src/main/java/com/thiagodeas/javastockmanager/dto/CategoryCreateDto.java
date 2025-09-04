package com.thiagodeas.javastockmanager.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class CategoryCreateDto {
    @Column(unique = true)
    @NotBlank
    private String name;

    public CategoryCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
