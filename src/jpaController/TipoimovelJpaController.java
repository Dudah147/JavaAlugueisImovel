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
import entity.Imovel;
import entity.Tipoimovel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class TipoimovelJpaController implements Serializable {

    public TipoimovelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoimovel tipoimovel) {
        if (tipoimovel.getImovelCollection() == null) {
            tipoimovel.setImovelCollection(new ArrayList<Imovel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Imovel> attachedImovelCollection = new ArrayList<Imovel>();
            for (Imovel imovelCollectionImovelToAttach : tipoimovel.getImovelCollection()) {
                imovelCollectionImovelToAttach = em.getReference(imovelCollectionImovelToAttach.getClass(), imovelCollectionImovelToAttach.getIdImovel());
                attachedImovelCollection.add(imovelCollectionImovelToAttach);
            }
            tipoimovel.setImovelCollection(attachedImovelCollection);
            em.persist(tipoimovel);
            for (Imovel imovelCollectionImovel : tipoimovel.getImovelCollection()) {
                Tipoimovel oldIdTipoImovelOfImovelCollectionImovel = imovelCollectionImovel.getIdTipoImovel();
                imovelCollectionImovel.setIdTipoImovel(tipoimovel);
                imovelCollectionImovel = em.merge(imovelCollectionImovel);
                if (oldIdTipoImovelOfImovelCollectionImovel != null) {
                    oldIdTipoImovelOfImovelCollectionImovel.getImovelCollection().remove(imovelCollectionImovel);
                    oldIdTipoImovelOfImovelCollectionImovel = em.merge(oldIdTipoImovelOfImovelCollectionImovel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoimovel tipoimovel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoimovel persistentTipoimovel = em.find(Tipoimovel.class, tipoimovel.getIdTipoImovel());
            Collection<Imovel> imovelCollectionOld = persistentTipoimovel.getImovelCollection();
            Collection<Imovel> imovelCollectionNew = tipoimovel.getImovelCollection();
            List<String> illegalOrphanMessages = null;
            for (Imovel imovelCollectionOldImovel : imovelCollectionOld) {
                if (!imovelCollectionNew.contains(imovelCollectionOldImovel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Imovel " + imovelCollectionOldImovel + " since its idTipoImovel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Imovel> attachedImovelCollectionNew = new ArrayList<Imovel>();
            for (Imovel imovelCollectionNewImovelToAttach : imovelCollectionNew) {
                imovelCollectionNewImovelToAttach = em.getReference(imovelCollectionNewImovelToAttach.getClass(), imovelCollectionNewImovelToAttach.getIdImovel());
                attachedImovelCollectionNew.add(imovelCollectionNewImovelToAttach);
            }
            imovelCollectionNew = attachedImovelCollectionNew;
            tipoimovel.setImovelCollection(imovelCollectionNew);
            tipoimovel = em.merge(tipoimovel);
            for (Imovel imovelCollectionNewImovel : imovelCollectionNew) {
                if (!imovelCollectionOld.contains(imovelCollectionNewImovel)) {
                    Tipoimovel oldIdTipoImovelOfImovelCollectionNewImovel = imovelCollectionNewImovel.getIdTipoImovel();
                    imovelCollectionNewImovel.setIdTipoImovel(tipoimovel);
                    imovelCollectionNewImovel = em.merge(imovelCollectionNewImovel);
                    if (oldIdTipoImovelOfImovelCollectionNewImovel != null && !oldIdTipoImovelOfImovelCollectionNewImovel.equals(tipoimovel)) {
                        oldIdTipoImovelOfImovelCollectionNewImovel.getImovelCollection().remove(imovelCollectionNewImovel);
                        oldIdTipoImovelOfImovelCollectionNewImovel = em.merge(oldIdTipoImovelOfImovelCollectionNewImovel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoimovel.getIdTipoImovel();
                if (findTipoimovel(id) == null) {
                    throw new NonexistentEntityException("The tipoimovel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoimovel tipoimovel;
            try {
                tipoimovel = em.getReference(Tipoimovel.class, id);
                tipoimovel.getIdTipoImovel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoimovel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Imovel> imovelCollectionOrphanCheck = tipoimovel.getImovelCollection();
            for (Imovel imovelCollectionOrphanCheckImovel : imovelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoimovel (" + tipoimovel + ") cannot be destroyed since the Imovel " + imovelCollectionOrphanCheckImovel + " in its imovelCollection field has a non-nullable idTipoImovel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoimovel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoimovel> findTipoimovelEntities() {
        return findTipoimovelEntities(true, -1, -1);
    }

    public List<Tipoimovel> findTipoimovelEntities(int maxResults, int firstResult) {
        return findTipoimovelEntities(false, maxResults, firstResult);
    }

    private List<Tipoimovel> findTipoimovelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoimovel.class));
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

    public Tipoimovel findTipoimovel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoimovel.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoimovelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoimovel> rt = cq.from(Tipoimovel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
