package com.springboot.jpa.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.springboot.jpa.data.dto.repository.ProductRepository;
import com.springboot.jpa.entity.Product;
import com.springboot.jpa.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        //쿼리 메서드에 Sort 객체 전달
        productRepository.findByName("펜", getSort());

        //페이징 쿼리 메서드 호출
       // Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,2));

        //Page 객체의 데이터 출력
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,2));
        System.out.println(productPage.getContent());
    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.asc("price"),
                Sort.Order.desc("stock")
        );
    }

    /*JPAQuery를 활용한 QueryDSL 테스트 코드*/
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct) //쿼리
                .where(qProduct.name.eq("펜")) //쿼리
                .orderBy(qProduct.price.asc()) //쿼리
                .fetch(); //조회 결과를 리스트로 반환

        for(Product product : productList) {
            System.out.println("----------------------");
            System.out.println("Product Name :" + product);
            System.out.println("----------------------");
        }
    }

}
