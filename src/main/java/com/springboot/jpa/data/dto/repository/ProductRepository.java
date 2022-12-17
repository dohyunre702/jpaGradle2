package com.springboot.jpa.data.dto.repository;

import com.springboot.jpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepositorySupport")
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    /* 정렬 */
    //Asc:오름차순, Desc:내림차순
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    //여러 정렬 기준 사용:and 붙이지 않음 - Price 기준 오름차순 정렬, 재고량 기준 내림차순 정렬
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    //매개변수 활용한 쿼리 정렬. Sort 객체 활용
    List<Product> findByName(String name, Sort sort);

    /* 페이징 */
    Page<Product> findByName(String name, Pageable pageable);

    /*어노테이션 사용 메서드*/
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
    List<Product> findByName(@Param("name") String name);

    //특정 칼럼만 추추 가능
    @Query("SELECT p.name, p.price, p.stock FROM Product AS p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
