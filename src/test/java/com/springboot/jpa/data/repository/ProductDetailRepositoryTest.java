package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.dto.repository.ProductDetailRepository;
import com.springboot.jpa.data.dto.repository.ProductRepository;
import com.springboot.jpa.entity.Product;
import com.springboot.jpa.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    //의존성 주입
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;


    @Test
    public void saveAndReadTest1() {
        //조회할 엔티티 객체 저장
        Product product = new Product();
        product.setName("Springboot JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링부트 , jpa");

        productDetailRepository.save(productDetail);

        //생성한 데이터 조회
        System.out.println("savedProduct : " + productDetailRepository.findById(
                productDetail.getId()).get());
    }
}
