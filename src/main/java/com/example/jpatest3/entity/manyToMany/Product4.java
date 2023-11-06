package com.example.jpatest3.entity.manyToMany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Product4 {

    @Id @Column(name="PRODUCT_ID")
    private String id;

    private String name;
}
