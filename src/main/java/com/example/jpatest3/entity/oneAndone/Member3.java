package com.example.jpatest3.entity.oneAndone;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member3 {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String username;

    @OneToOne
    @JoinColumn(name="LOKCER_ID")
    private Locker3 locker;

}
