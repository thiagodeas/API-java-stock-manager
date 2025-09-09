package com.thiagodeas.javastockmanager.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thiagodeas.javastockmanager.dto.InboundCreateDto;
import com.thiagodeas.javastockmanager.exceptions.InvalidDateRangeException;
import com.thiagodeas.javastockmanager.exceptions.InvalidReasonException;
import com.thiagodeas.javastockmanager.exceptions.ProductNotFoundException;
import com.thiagodeas.javastockmanager.models.Inbound;
import com.thiagodeas.javastockmanager.models.Product;
import com.thiagodeas.javastockmanager.models.enums.InboundReason;
import com.thiagodeas.javastockmanager.repositories.InboundRepository;
import com.thiagodeas.javastockmanager.repositories.ProductRepository;

@Service
public class InboundService {
    private final InboundRepository inboundRepository;
    private final ProductRepository productRepository;

    public InboundService(InboundRepository inboundRepository, ProductRepository productRepository) {
        this.inboundRepository = inboundRepository;
        this.productRepository = productRepository;
    }

    public List<Inbound> findAll() {
        return this.inboundRepository.findAll();
    }

    public List<Inbound> findByDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new InvalidDateRangeException("As datas de início e fim são obrigatórias.");
        }
        if (start.isAfter(end)) {
            throw new InvalidDateRangeException("A data de início deve ser anterior ou igual à data de fim.");
        }

        return inboundRepository.findByDateRange(start, end);
    }

    public List<Inbound> findByReason(InboundReason reason) {
        if (reason == null) {
            throw new InvalidReasonException("O campo motivo é obrigatório.");
        }

        return this.inboundRepository.findByReason(reason);
    }

    public List<Inbound> findByDateRangeAndReason(LocalDateTime start, LocalDateTime end, InboundReason reason) {
        if (start == null || end == null) {
            throw new InvalidDateRangeException("As datas de início e fim são obrigatórias.");
        }

        if (start.isAfter(end)) {
            throw new InvalidDateRangeException("A data de início deve ser anterior ou igual à data de fim.");
        }

        if (reason == null) {
            throw new InvalidReasonException("O campo motivo é obrigatório.");        
        }

        return this.inboundRepository.findByDateRangeAndReason(start, end, reason);
    }

    public Inbound create(InboundCreateDto dto) {
        Product product = this.productRepository.findById(dto.productId())
        .orElseThrow(() -> new ProductNotFoundException());

        Inbound inbound = new Inbound(dto.reason(), product, dto.quantity());
        return this.inboundRepository.save(inbound);
      
    }
}
