/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Locacao;
import entity.Locatario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class LocatarioJpaController implements Serializable {

    public LocatarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locatario locatario) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Locacao> attachedLocacaoCollection = new ArrayList<Locacao>();

            em.persist(locatario);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locatario locatario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario persistentLocatario = em.find(Locatario.class, locatario.getIdLocatario());
            Collection<Locacao> attachedLocacaoCollectionNew = new ArrayList<Locacao>();


            locatario = em.merge(locatario);


            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = locatario.getIdLocatario();
                if (findLocatario(id) == null) {
                    throw new NonexistentEntityException("The locatario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario locatario;
            try {
                locatario = em.getReference(Locatario.class, id);
                locatario.getIdLocatario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locatario with id " + id + " no longer exists.", enfe);
            }

            em.remove(locatario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locatario> findLocatarioEntities() {
        return findLocatarioEntities(true, -1, -1);
    }

    public List<Locatario> findLocatarioEntities(int maxResults, int firstResult) {
        return findLocatarioEntities(false, maxResults, firstResult);
    }

    private List<Locatario> findLocatarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locatario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Locatario findLocatario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locatario.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocatarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locatario> rt = cq.from(Locatario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
