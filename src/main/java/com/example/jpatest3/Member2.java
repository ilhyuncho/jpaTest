package com.example.jpatest3;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="MEMBER2", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member2 {

    @Id
    @Column(name = "MEMBER_ID")
    private String  id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name="TEAM_ID") // joinColumn을 생략하면 외래 키를 찾을때 기본 전략을 사용 함 (필드명 + _ + 참조하는 테이블의 컬럼명)
    private Team team;          // 주인 - 주인만 데이터베이스 연관관계와 매핑되고 외래 키를 관리 할수 있다,
                                // 주인이 아닌 반대편은 읽기만 가능하고 외래키를 변경하지는 못한다.
                                // 외래키를 가지고 있다
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
        // 연관 관계 편의 메서드

        // 기존 팀과 관계를 제거
        if(this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;

        // 양방향 관계를 모두 설정
        team.getMembers().add(this);
    }
}