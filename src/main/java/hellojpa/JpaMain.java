package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.sql.ast.tree.expression.Literal;

import javax.lang.model.SourceVersion;
import java.sql.SQLOutput;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);


            Member member2 = new Member();
            member1.setUsername("member2");
            em.persist(member2);


            em.flush();
            em.clear();
//
//            Member findmember = em.find(Member.class, member.getId());
//            System.out.println("findmember.id = " + findmember.getId());
//            System.out.println("findmember.username = " + findmember.getUsername());

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member2.getId());

            System.out.println(("m1 == m2: " + (m1.getClass() == m2.getClass())));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
