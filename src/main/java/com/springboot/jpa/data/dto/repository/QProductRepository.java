package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

}
