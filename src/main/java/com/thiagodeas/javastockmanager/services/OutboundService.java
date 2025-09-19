package com.thiagodeas.javastockmanager.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thiagodeas.javastockmanager.dto.OutboundCreateDto;
import com.thiagodeas.javastockmanager.exceptions.InvalidDateRangeException;
import com.thiagodeas.javastockmanager.exceptions.InvalidReasonException;
import com.thiagodeas.javastockmanager.exceptions.ProductNotFoundException;
import com.thiagodeas.javastockmanager.models.Outbound;
import com.thiagodeas.javastockmanager.models.Product;
import com.thiagodeas.javastockmanager.models.enums.OutboundReason;
import com.thiagodeas.javastockmanager.repositories.OutboundRepository;
import com.thiagodeas.javastockmanager.repositories.ProductRepository;

@Service
public class OutboundService {
    private OutboundRepository outboundRepository;
    private ProductRepository productRepository;

    public OutboundService() {}

    public OutboundService(OutboundRepository outboundRepository, ProductRepository productRepository) {
        this.outboundRepository = outboundRepository;
        this.productRepository = productRepository;
    }

    public List<Outbound> findAll() {
        return this.outboundRepository.findAll();
    }

    public List<Outbound> findByDateBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new InvalidDateRangeException("As datas de início e fim são obrigatórias.");
        }

        if (start.isAfter(end)) {
            throw new InvalidDateRangeException("A data de início deve ser anterior ou igual à data de fim.");
        }

        return this.outboundRepository.findByDateBetween(start, end);
    }

    public List<Outbound> findByReason(OutboundReason reason) {
        if (reason == null) {
            throw new InvalidReasonException("O campo motivo é obrigatório.");
        }

        return this.outboundRepository.findByReason(reason);
    }

    public List<Outbound> findByDateBetweenAndReason(LocalDateTime start, LocalDateTime end, OutboundReason reason) {
        if (start == null || end == null) {
            throw new InvalidDateRangeException("As datas de início e fim são obrigatórias.");
        }

        if (start.isAfter(end)) {
            throw new InvalidDateRangeException("A data de início deve ser anterior ou igual à data de fim.");
        }

        if (reason == null) {
            throw new InvalidReasonException("O campo motivo é obrigatório.");
        }

        return this.outboundRepository.findByDateBetweenAndReason(start, end, reason);
    }

    public Outbound create(OutboundCreateDto dto) {
        Product product = this.productRepository.findById(dto.productId())
        .orElseThrow(() -> new ProductNotFoundException());

        Outbound outbound = new Outbound(dto.reason(), product, dto.quantity());

        return this.outboundRepository.save(outbound);
    }
}
