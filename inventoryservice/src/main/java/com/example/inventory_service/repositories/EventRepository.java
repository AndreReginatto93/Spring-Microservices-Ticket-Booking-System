package com.example.inventory_service.repositories;

import com.example.inventory_service.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<EventEntity, Long> {
}
