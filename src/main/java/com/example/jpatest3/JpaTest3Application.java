package com.example.jpatest3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class JpaTest3Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaTest3Application.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

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
        Member2 member = new Member2();
        member.setId(id);
        member.setUsername("지한1");
        member.setAge(2);

        //등록
        em.persist(member);

//        //수정
//        member.setAge(20);

        //한 건 조회
        Member2 findMember = em.find(Member2.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
//        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//        System.out.println("members.size=" + members.size());
//
//        //삭제
//        em.remove(member);

    }

}
