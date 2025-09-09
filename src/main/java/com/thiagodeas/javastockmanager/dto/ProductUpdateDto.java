package com.thiagodeas.javastockmanager.dto;

import java.math.BigDecimal;

public record ProductUpdateDto(
    String name,

    Long categoryId,

    BigDecimal price
) {}
