package com.example.jpatest3;

import com.example.jpatest3.entity.Item;
import com.example.jpatest3.entity.Member;
import com.example.jpatest3.entity.Order;
import com.example.jpatest3.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
class JpaTest3ApplicationTests {

    @Test
    void contextLoads() {

        // 실습 테스트~~~~~~~~~~~~~~~~

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작


            Member member1 = new Member("member1");
            em.persist(member1);

            Item item1 = new Item("item1", 4, 100);
            Item item2 = new Item("item2", 10, 100);
            em.persist(item1);
            em.persist(item2);

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setItem(item1);
            em.persist(orderItem1);


            Order order1 = new Order();
            order1.setMember(member1);
            order1.addOrderItem(orderItem1);

            em.persist(order1);


            Order order = em.find(Order.class,order1.getId() );
            Member member = order.getMember();

            OrderItem orderItem2 = order.getOrderItemList().get(0);
            orderItem2.getItem();

            System.out.println("sdfsdf : " + orderItem2.getItem());



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
