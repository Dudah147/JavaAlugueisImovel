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
import entity.TipoImovel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author a2319217
 */
public class TipoImovelJpaController implements Serializable {

    public TipoImovelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoImovel tipoImovel) {
        if (tipoImovel.getImovelCollection() == null) {
            tipoImovel.setImovelCollection(new ArrayList<Imovel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Imovel> attachedImovelCollection = new ArrayList<Imovel>();
            for (Imovel imovelCollectionImovelToAttach : tipoImovel.getImovelCollection()) {
                imovelCollectionImovelToAttach = em.getReference(imovelCollectionImovelToAttach.getClass(), imovelCollectionImovelToAttach.getIdImovel());
                attachedImovelCollection.add(imovelCollectionImovelToAttach);
            }
            tipoImovel.setImovelCollection(attachedImovelCollection);
            em.persist(tipoImovel);
            for (Imovel imovelCollectionImovel : tipoImovel.getImovelCollection()) {
                TipoImovel oldIdTipoImovelOfImovelCollectionImovel = imovelCollectionImovel.getIdTipoImovel();
                imovelCollectionImovel.setIdTipoImovel(tipoImovel);
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

    public void edit(TipoImovel tipoImovel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoImovel persistentTipoImovel = em.find(TipoImovel.class, tipoImovel.getIdTipoImovel());
            Collection<Imovel> imovelCollectionOld = persistentTipoImovel.getImovelCollection();
            Collection<Imovel> imovelCollectionNew = tipoImovel.getImovelCollection();
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
            tipoImovel.setImovelCollection(imovelCollectionNew);
            tipoImovel = em.merge(tipoImovel);
            for (Imovel imovelCollectionNewImovel : imovelCollectionNew) {
                if (!imovelCollectionOld.contains(imovelCollectionNewImovel)) {
                    TipoImovel oldIdTipoImovelOfImovelCollectionNewImovel = imovelCollectionNewImovel.getIdTipoImovel();
                    imovelCollectionNewImovel.setIdTipoImovel(tipoImovel);
                    imovelCollectionNewImovel = em.merge(imovelCollectionNewImovel);
                    if (oldIdTipoImovelOfImovelCollectionNewImovel != null && !oldIdTipoImovelOfImovelCollectionNewImovel.equals(tipoImovel)) {
                        oldIdTipoImovelOfImovelCollectionNewImovel.getImovelCollection().remove(imovelCollectionNewImovel);
                        oldIdTipoImovelOfImovelCollectionNewImovel = em.merge(oldIdTipoImovelOfImovelCollectionNewImovel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoImovel.getIdTipoImovel();
                if (findTipoImovel(id) == null) {
                    throw new NonexistentEntityException("The tipoImovel with id " + id + " no longer exists.");
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
            TipoImovel tipoImovel;
            try {
                tipoImovel = em.getReference(TipoImovel.class, id);
                tipoImovel.getIdTipoImovel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoImovel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Imovel> imovelCollectionOrphanCheck = tipoImovel.getImovelCollection();
            for (Imovel imovelCollectionOrphanCheckImovel : imovelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoImovel (" + tipoImovel + ") cannot be destroyed since the Imovel " + imovelCollectionOrphanCheckImovel + " in its imovelCollection field has a non-nullable idTipoImovel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoImovel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoImovel> findTipoImovelEntities() {
        return findTipoImovelEntities(true, -1, -1);
    }

    public List<TipoImovel> findTipoImovelEntities(int maxResults, int firstResult) {
        return findTipoImovelEntities(false, maxResults, firstResult);
    }

    private List<TipoImovel> findTipoImovelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoImovel.class));
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

    public TipoImovel findTipoImovel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoImovel.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoImovelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoImovel> rt = cq.from(TipoImovel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
