package com.example.jpatest3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class JpaTest3Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaTest3Application.class, args);


        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        // persistence.xml의 설정 정보를 읽어서 JPA를 동작시키기 위한 기반 객체를 만들고 
        // JPA 구현체에 따라서는 데이터베이스 커넥션 풀도 생성하므로 생성 비용이 크다
        
        
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        // 매니저를 통해서 db에 등록/수정/삭제/조회 할수 있다.
        // 엔티티 매니저를 가상의 데이터베이스로 생각할수 있다
        // 엔티티 매니저는 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용하면 안된다.


        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료

    }
    public static void logic(EntityManager em) {

        String id = "id5";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한1");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);

    }

}
