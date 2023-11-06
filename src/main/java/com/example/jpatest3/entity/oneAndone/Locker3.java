package com.example.jpatest3.entity.oneAndone;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Locker3 {

    @Id @GeneratedValue
    @Column(name="LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker") // 일대일 양방향을 위해, 주인이 아니라고 설정
    private Member3 member;
}
