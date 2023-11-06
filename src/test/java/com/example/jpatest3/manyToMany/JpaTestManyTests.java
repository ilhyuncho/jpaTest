package com.example.jpatest3.manyToMany;

import com.example.jpatest3.entity.Item;
import com.example.jpatest3.entity.Member;
import com.example.jpatest3.entity.Order;
import com.example.jpatest3.entity.OrderItem;
import com.example.jpatest3.entity.manyToMany.Member4;
import com.example.jpatest3.entity.manyToMany.Product4;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
public class JpaTestManyTests {

    @Test
    void test1(){
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            Product4 product4 = new Product4();
            product4.setId("productA");
            product4.setName("상품A");

            em.persist(product4);

            Member4 member4 = new Member4();
            member4.setId("member1");
            member4.setUsername("회원1");
            //member4.getProducts().add(product4);
            // 양방향 연관관계
            member4.addProduct(product4);

            // 역방향으로 객체 그래프를 탐색 가능
            product4.getMembers().forEach(System.out::println);

            em.persist(member4);

            // 양방향 역방향 탐색
            Product4 product= em.find(Product4.class, "productA");
            product.getMembers().forEach(System.out::println);

            // find
//            Member4 member = em.find(Member4.class, "member1");
//
//            member.getProducts().forEach( a-> {
//
//                System.out.println("product.name = " +a.getName());
//            });






            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
