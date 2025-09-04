package com.thiagodeas.javastockmanager.models;

import java.time.LocalDateTime;
import java.util.Objects;

import com.thiagodeas.javastockmanager.models.enums.OutboundReason;

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
public class Outbound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OutboundReason reason;

    @ManyToOne
    @NotNull
    private Product product;

    @NotBlank
    private String quantity;

    private LocalDateTime date;

    public Outbound() {}
    
    public Outbound(OutboundReason reason, Product product, String quantity) {
        this.reason = reason;
        this.product = product;
        this.quantity = quantity;
        this.date = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }

    public OutboundReason getReason() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outbound outbound = (Outbound) o;

        return id == outbound.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Outbound{" +
        "id=" + id +
        ", reason='" + reason +
        ", product='" + product +
        ", quantity='" + quantity +
        ", date=" + date +
        '}';
    }
}
