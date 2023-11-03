package com.example.jpatest3;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="MEMBER2", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
@ToString
public class Member2 {

    @Id
    @Column(name = "MEMBER_ID")
    private String  id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name="TEAM_ID") // joinColumn을 생략하면 외래 키를 찾을때 기본 전략을 사용 함 (필드명 + _ + 참조하는 테이블의 컬럼명)
    private Team team;

    private Integer age;

    @Enumerated(EnumType.STRING)
//    @Column(name="role_type")
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member2(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}