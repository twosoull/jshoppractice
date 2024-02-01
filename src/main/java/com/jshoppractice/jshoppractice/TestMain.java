package com.jshoppractice.jshoppractice;

import com.jshoppractice.jshoppractice.domain.Address;
import com.jshoppractice.jshoppractice.domain.Member;
import com.jshoppractice.jshoppractice.domain.Order;
import com.jshoppractice.jshoppractice.domain.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestMain {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @GetMapping("/test")
    public String Test(){
        Address address = new Address("city","myunmok", "1233455");

        Member member = new Member();
        member.setName("user1");
        member.setAddress(address);

        em.persist(member);

        Order order = new Order();

        order.setMember(member);
        order.setStatus(Status.ORDER);
        em.persist(order);

        em.flush();
        em.clear();

        Member member1 = em.find(Member.class, member.getId());

        for (Order o : member1.getOrderList()) {
            System.out.println("o = " + o.getStatus());
        }


        return "";
    }

}
