package com.example.jpatest3;

import com.example.jpatest3.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
@Log4j2
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

//            logic1(em);            // 다대일 연관 관계 (단방향)
            //biDirection(em);         // 일대다 컬렉션 조회


            //biDirectionSave(em);      // 일대다 양방향 연관관계 저장
            biDirectionNonSave(em);     // 일대다 양방향 연관관계 주의점



            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료

    }

    public static void biDirectionNonSave(EntityManager em){
        // 일대다 양방향 연관관계 주의점
        // 주인이 아닌 Team.members에만 값을 저장 했을 경우 TEAM_ID 가 null 이 된다

        // 회원1 저장
        Member2 member1 = new Member2("member1", "회원1");
        em.persist(member1);

        // 회원2 저장
        Member2 member2 = new Member2("member2", "회원2");
        em.persist(member2);

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);
    }


    public static void biDirectionSave(EntityManager em){
        // 일대다 양방향 연관관계 저장

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member2 member1 = new Member2("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member2 member2 = new Member2("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public static void biDirection(EntityManager em){
        // 일대다 컬렉션 조회

        Team team = em.find(Team.class, "team1");
        List<Member2> members = team.getMembers();

        for (Member2 member : members) {
            log.warn(member);
        }
    }

    public static void logic1(EntityManager em) {
        // 다대일 연관 관계 (단방향)

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member2 member1 = new Member2("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member2 member2 = new Member2("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        // 객체 그래프 탐색
        Member2 member = em.find(Member2.class, "member1");
        Team team = member.getTeam();
        log.info("팀이름 =" + team.getName());

        // JPQL 조인 검색
        String jpql = "select m from Member2 m join m.team t where t.name=:teamName";

        List<Member2> resultList = em.createQuery(jpql,Member2.class).setParameter("teamName", "팀1").getResultList();

        for (Member2 member21 : resultList) {
            log.warn(member21.getUsername());
        }




//        List<Member2> members = em.createQuery("SELECT M FROM Member2 M JOIN Team T ON M.team.name = T.name",  Member2.class).getResultList();
//
//        for (Member2 member : members) {
//            log.info(member);
//        }

        log.info("----logic1----end");




    }


    public static void logic(EntityManager em) {
//
//        String id = "id5";
//        Member2 member = new Member2();
//        //member.setId(id);
//        member.setUsername("지한1");
//        member.setAge(2);
//
//        //등록
//        em.persist(member);
//
//        //수정
//        member.setAge(20);
//
//        //한 건 조회
//        Member2 findMember2 = em.find(Member2.class, 1L);
//        System.out.println("findMember2=" + findMember2.getUsername() + ", age=" + findMember2.getAge());
//
//        //목록 조회
//        List<Member2> members = em.createQuery("select m from Member2 m", Member2.class).getResultList();
//        System.out.println("members.size=" + members.size());

        //삭제
       // em.remove(member);

    }

}
