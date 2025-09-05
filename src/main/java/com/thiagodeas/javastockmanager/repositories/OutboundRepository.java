package com.thiagodeas.javastockmanager.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodeas.javastockmanager.models.Outbound;
import com.thiagodeas.javastockmanager.models.enums.OutboundReason;

public interface OutboundRepository extends JpaRepository<Outbound, Long>{
    List<Outbound> findByDateRange(LocalDateTime start, LocalDateTime end);
    List<Outbound> findByReason(OutboundReason reason);
    List<Outbound> findByDateRangeAndReason(LocalDateTime start, LocalDateTime end, OutboundReason reason);
}
