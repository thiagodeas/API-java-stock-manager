package com.thiagodeas.javastockmanager.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateDto(
    @NotBlank
    String name,

    @NotNull
    Long categoryId,

    @NotNull
    BigDecimal price
) {}
