package helper;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Vilao;
import Classes.Heroi;

// Facade
public class HibernateController {

    public static void registrarVilao(Vilao novoVilao) {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(novoVilao);
            em.getTransaction().commit();
        } finally {
            HibernateManager.closeEntityManager();
        }
    }

    public static void registrarHeroi(Heroi novoHeroi) {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(novoHeroi);
            em.getTransaction().commit();
        } finally {
            HibernateManager.closeEntityManager();
        }
    }

    public static Vilao procurarVilao(String nome) {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            Vilao vilao = em.find(Vilao.class, nome);
            return vilao;
        } catch (Exception e) {
            return null;
        } finally {
            HibernateManager.closeEntityManager();
        }
    }

    public static Heroi procurarHeroi(String nome) {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            Heroi heroi = em.find(Heroi.class, nome);
            return heroi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateManager.closeEntityManager();
        }
    }

    public static void limparTabelaVilao() {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Vilao");
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            HibernateManager.closeEntityManager();
        }
    }

    public static void limparTabelaHeroi() {
        EntityManager em = HibernateManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Heroi");
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            HibernateManager.closeEntityManager();
        }
    }
}
