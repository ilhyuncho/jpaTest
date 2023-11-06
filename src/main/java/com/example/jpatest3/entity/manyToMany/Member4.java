package com.example.jpatest3.entity.manyToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Member4 {

    @Id
    @Column(name= "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT4"  // 신규 연결 테이블 지정
            , joinColumns = @JoinColumn(name = "MEMBER_ID")
            ,inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product4> products = new ArrayList<Product4>();

    public void addProduct(Product4 product){

        if(!products.contains(product)){
            products.add(product);
        }

        product.getMembers().add(this);
    }


}
