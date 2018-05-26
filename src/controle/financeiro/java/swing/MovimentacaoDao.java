package controle.financeiro.java.swing;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MovimentacaoDao {

    public void persist(Movimentacao movimentacao) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-financeiro-java-swingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(movimentacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Movimentacao> findAll() {
        List<Movimentacao> movimentacoes;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-financeiro-java-swingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            movimentacoes = em.createNamedQuery("Movimentacao.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            movimentacoes = null;
        } finally {
            em.close();
        }
        
        return movimentacoes;
    }

    public void remove(long index) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-financeiro-java-swingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Movimentacao movimentacao = em.find(Movimentacao.class, index);
            em.remove(movimentacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
