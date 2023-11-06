package com.example.jpatest3.entity.manyToMany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Product4 {

    @Id @Column(name="PRODUCT_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products")  // 역방향 추가
    private List<Member4> members = new ArrayList<>();
}
