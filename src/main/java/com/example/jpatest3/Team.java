package com.example.jpatest3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Team {

    @Id
    @Column(name="TEAM_ID")
    private String id;

    private String name;



}
