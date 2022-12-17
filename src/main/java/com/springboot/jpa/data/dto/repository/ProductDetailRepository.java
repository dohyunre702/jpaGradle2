package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
