package com.thiagodeas.javastockmanager.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDto (
    @NotBlank String name
) {}
