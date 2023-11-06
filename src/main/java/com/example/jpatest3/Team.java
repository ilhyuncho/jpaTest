package com.example.jpatest3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @Column(name="TEAM_ID") // 외래 키
    private String id;

    private String name;

    // 양방향 연관관계 매핑
    @OneToMany(mappedBy = "team")   // mappedBy는 양방향 매핑일때 사용
                                    // 두 객체 연관관계 중 하나를 정해서 테이블의 외래키를 관리하기 위하여
                                    // 주인은 mappedBy속성을 사용하지 않는다
                                    // 조회를 위한 JPQL이나 객체 그래프를 탐색할때 사용 됨
    private List<Member2> members = new ArrayList<Member2>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(Member2 member){
        this.members.add(member);

        if(member.getTeam() != this){
            member.setTeam(this);
        }
    }
}
