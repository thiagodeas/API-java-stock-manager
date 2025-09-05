package com.thiagodeas.javastockmanager.dto;

import java.time.LocalDateTime;

import com.thiagodeas.javastockmanager.models.enums.InboundReason;

import jakarta.validation.constraints.NotNull;

public record InboundCreateDto(
    @NotNull
    InboundReason reason,

    @NotNull
    Long productId,

    @NotNull
    Integer quantity,

    @NotNull
    LocalDateTime date
) {}
