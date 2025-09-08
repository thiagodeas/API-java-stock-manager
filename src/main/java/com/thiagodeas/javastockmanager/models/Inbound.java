package com.thiagodeas.javastockmanager.models;

import java.time.LocalDateTime;
import java.util.Objects;

import com.thiagodeas.javastockmanager.models.enums.InboundReason;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Inbound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private InboundReason reason;

    @ManyToOne
    @NotNull
    private Product product;

    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDateTime date;

    public Inbound() {}

    public Inbound(InboundReason reason, Product product, Integer quantity) {
        this.reason = reason;
        this.product = product;
        this.quantity = quantity;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

     public InboundReason getReason() {
        return this.reason;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inbound inbound = (Inbound) o;

        return Objects.equals(id, inbound.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Inbound{" +
        "id=" + id +
        ", product='" + product +
        ", reason=" + reason +
        ", quantity=" + quantity +
        ", date =" + date +
        '}';
    }
}
