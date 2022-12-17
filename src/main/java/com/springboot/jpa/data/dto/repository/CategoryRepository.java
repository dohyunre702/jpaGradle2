package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
