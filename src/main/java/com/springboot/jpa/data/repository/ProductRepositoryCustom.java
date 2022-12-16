package com.springboot.jpa.data.repository;

import com.springboot.jpa.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByName(String name);

}
