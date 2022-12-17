package com.springboot.jpa.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_number") //매핑할 외래키 설정 (name으로 원하는 칼럼명 지정)
    private Product product;

}
