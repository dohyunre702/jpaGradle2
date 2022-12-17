package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
