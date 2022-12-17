package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.dto.repository.ProductRepository;
import com.springboot.jpa.data.dto.repository.ProviderRepository;
import com.springboot.jpa.entity.Product;
import com.springboot.jpa.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    //엔티티 연관관계 테스트
    @Test
    void relationshipTest() {
        //테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");

        Product product1 = new Product();
        product1.setName("가위");
        product1.setPrice(5000);
        product1.setStock(500);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(2000);
        product2.setStock(100);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(1000);
        product3.setStock(50);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //ProductRepository를 통해 연관관계가 매핑된 Product리스트를 가져와 출력
        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();
        
        for(Product product : products) {
            System.out.println(product);
        }
    }    
}
