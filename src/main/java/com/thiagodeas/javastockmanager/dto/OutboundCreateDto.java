package com.thiagodeas.javastockmanager.dto;

import com.thiagodeas.javastockmanager.models.enums.OutboundReason;

import jakarta.validation.constraints.NotNull;

public record OutboundCreateDto(
    @NotNull
    OutboundReason reason,

    @NotNull
    Long productId,

    @NotNull
    Integer quantity
) {}
