package com.example.jpatest3.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@ToString
public class Item {
    
    @Id
    @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;
    
    private String name;        // 이름
    private int price;          // 가격
    private int stockQuantity;  // 재고 수량

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
