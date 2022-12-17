package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
