package com.springboot.jpa.data.repository;

import com.springboot.jpa.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Asc:오름차순, Desc:내림차순
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    //여러 정렬 기준 사용:and 붙이지 않음 - Price 기준 오름차순 정렬, 재고량 기준 내림차순 정렬
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    //매개변수 활용한 쿼리 정렬. Sort 객체 활용
    List<Product> findByName(String name, Sort sort);
}
