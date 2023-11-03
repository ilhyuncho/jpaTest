package com.example.jpatest3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
    
    @Id
    @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;
    
    private String name;        // 이름
    private int price;          // 가격
    private int stockQuantity;  // 재고 수량

}
