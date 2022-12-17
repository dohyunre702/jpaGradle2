package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.dto.repository.ProductRepository;
import com.springboot.jpa.data.dto.repository.ProviderRepository;
import com.springboot.jpa.entity.Product;
import com.springboot.jpa.entity.Provider;
import org.assertj.core.util.Lists;
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

    @Test //영속성 전이 테스트
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 500, 1000);
        Product product2 = savedProduct("상품2", 100, 2000);
        Product product3 = savedProduct("상품3", 750, 5000);

        //연관관계 매핑
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product2, product2, product3));

        providerRepository.save(provider);

    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    //고아객체 제거 기능 테스트
    @Test
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 500, 1000);
        Product product2 = savedProduct("상품2", 100, 2000);
        Product product3 = savedProduct("상품3", 750, 5000);

        //연관관계 매핑
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product2, product2, product3));

        providerRepository.saveAndFlush(provider);

        //각 엔티티 출력
        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        //생성한 공급업체 엔티티 가져온 ㅎ후 첫 번째로 매핑된 상품 엔티티의 연관관계 제거
        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        //전체 코드 조회 수행0
        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }
}
