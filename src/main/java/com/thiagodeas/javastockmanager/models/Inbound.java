package com.thiagodeas.javastockmanager.models;

import java.time.LocalDateTime;

import com.thiagodeas.javastockmanager.models.enums.InboundReason;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Inbound {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private final InboundReason reason;

    @ManyToOne
    @NotNull
    private final Product product;

    @NotBlank
    private final String quantity;

    private final LocalDateTime date;

    public Inbound(InboundReason reason, Product product, String quantity) {
        this.reason = reason;
        this.product = product;
        this.quantity = quantity;
        this.date = LocalDateTime.now();
    }

     public InboundReason getReason() {
        return this.reason;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public LocalDateTime getDate() {
        return this.date;
    }
}
