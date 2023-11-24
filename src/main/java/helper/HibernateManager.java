package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManager {
    private static final String PU_NAME = "TrabalhoFinalPoo";
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private HibernateManager() {
    }

    public static synchronized EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PU_NAME);
        }

        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public static synchronized void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public static synchronized void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
