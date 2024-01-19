package com.brianeno.sqs.repository;

import com.brianeno.sqs.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, String> {
}
