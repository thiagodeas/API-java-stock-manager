package com.thiagodeas.javastockmanager.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodeas.javastockmanager.models.Inbound;
import com.thiagodeas.javastockmanager.models.enums.InboundReason;

public interface InboundRepository extends JpaRepository<Inbound, Long> {
    List<Inbound> findByDateRange(LocalDateTime start, LocalDateTime end);
    List<Inbound> findByReason(InboundReason reason);
    List<Inbound> findByDateRangeAndReason(LocalDateTime start, LocalDateTime end, InboundReason reason);
}
