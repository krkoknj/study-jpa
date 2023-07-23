package com.mystudy.myjpamvn.start;

import com.mystudy.myjpamvn.entity.Members;
import com.mystudy.myjpamvn.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    static EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("myjpa");

    public static void main(String... args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        testSave(em);
//        updateRelation(em);
//        deleteRelation(em);
//        queryLogicJoin(em);
//        biDirection(em);
//    testSaveNonOwner(em);
        test(em);
        tx.commit();
        em.close();
        emf.close();
    }

    public static void test(EntityManager em) {
        // 팀1 저장
        Team team1 = new Team("team1", "te1");
        em.persist(team1);

        Members member1 = new Members("member1", "mem1");
        
        // 양방향 연관관계 설정
        member1.setTeam(team1);
        team1.getMembers().add(member1);
        em.persist(member1);

        Members member2 = new Members("member2", "mem2");

        // 양방향 연관관계 설정
        member2.setTeam(team1);
        team1.getMembers().add(member2);
        em.persist(member2);
    }

    private static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");

        List<Members> members = team.getMembers(); // (팀 -> 회원), 객체 그래프 탐색

        for (Members member : members) {
            System.out.println("member.getUsername() = " + member.getUsername());
        }
    }
    
    private static void deleteRelation(EntityManager em) {
        Members member1 = em.find(Members.class, "member1");
        member1.setTeam(null); // 연관관계 제거
    }

    private static void updateRelation(final EntityManager em) {
        // 새로운 팀2
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);
        
        // 회원1에 새로운 팀2 설정
        Members member = em.find(Members.class, "member1");
        member.setTeam(team2);
    }

    private static void testSave(final EntityManager em) {
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Members member1 = new Members("member1", "membersss1");
        member1.setTeam(team1);  // 연관관계 설정 member1 -> team1
        em.persist(member1);

        // 회원1 저장
        Members member2 = new Members("member2", "membersss2");
        member2.setTeam(team1);  // 연관관계 설정 member1 -> team1
        em.persist(member2);
    }

    private static void queryLogicJoin(final EntityManager em) {
        String jpql = "select m from Members m join m.team t where t.name=:teamName";

        List<Members> resultList = em.createQuery(jpql, Members.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        resultList.forEach(m -> System.out.println("[query] member.username=" + m.getUsername()));
    }
}
