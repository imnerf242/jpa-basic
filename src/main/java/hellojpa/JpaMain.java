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
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findmovie = em.find(Movie.class, movie.getId());
            System.out.println("findmovie = " + findmovie);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
