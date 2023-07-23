package com.mystudy.myjpamvn;

import com.mystudy.myjpamvn.entity.Members;
import com.mystudy.myjpamvn.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
public class MemberTeamTests {

    //    // 엔티티 매니저 팩토리 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("myjpa");
//
    // 엔티티 매니저 생성
    EntityManager em = emf.createEntityManager();

//    // 트랜잭션 획득
    EntityTransaction tx = em.getTransaction();

    @Test
    void save() {
        // 팀1 저장
        tx.begin();
        Team team1 = new Team("team1", "team1");
        em.persist(team1);
        
        // 회원1 저장
        Members member1 = new Members("member1", "members1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        em.persist(member1);
        
        // 회원2 저장
        Members member2 = new Members("member2", "members2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        em.persist(member2);

        Members member = em.find(Members.class, "member1");
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());

        tx.commit();
    }

}
