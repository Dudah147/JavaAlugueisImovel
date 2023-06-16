/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaController;

import entity.Imovel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Tipoimovel;
import entity.Locacao;
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
public class ImovelJpaController implements Serializable {

    public ImovelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imovel imovel) {
        if (imovel.getLocacaoCollection() == null) {
            imovel.setLocacaoCollection(new ArrayList<Locacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoimovel idTipoImovel = imovel.getIdTipoImovel();
            if (idTipoImovel != null) {
                idTipoImovel = em.getReference(idTipoImovel.getClass(), idTipoImovel.getIdTipoImovel());
                imovel.setIdTipoImovel(idTipoImovel);
            }
            Collection<Locacao> attachedLocacaoCollection = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionLocacaoToAttach : imovel.getLocacaoCollection()) {
                locacaoCollectionLocacaoToAttach = em.getReference(locacaoCollectionLocacaoToAttach.getClass(), locacaoCollectionLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollection.add(locacaoCollectionLocacaoToAttach);
            }
            imovel.setLocacaoCollection(attachedLocacaoCollection);
            em.persist(imovel);
            if (idTipoImovel != null) {
                idTipoImovel.getImovelCollection().add(imovel);
                idTipoImovel = em.merge(idTipoImovel);
            }
            for (Locacao locacaoCollectionLocacao : imovel.getLocacaoCollection()) {
                Imovel oldIdImovelOfLocacaoCollectionLocacao = locacaoCollectionLocacao.getIdImovel();
                locacaoCollectionLocacao.setIdImovel(imovel);
                locacaoCollectionLocacao = em.merge(locacaoCollectionLocacao);
                if (oldIdImovelOfLocacaoCollectionLocacao != null) {
                    oldIdImovelOfLocacaoCollectionLocacao.getLocacaoCollection().remove(locacaoCollectionLocacao);
                    oldIdImovelOfLocacaoCollectionLocacao = em.merge(oldIdImovelOfLocacaoCollectionLocacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imovel imovel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imovel persistentImovel = em.find(Imovel.class, imovel.getIdImovel());
            Tipoimovel idTipoImovelOld = persistentImovel.getIdTipoImovel();
            Tipoimovel idTipoImovelNew = imovel.getIdTipoImovel();
            Collection<Locacao> locacaoCollectionOld = persistentImovel.getLocacaoCollection();
            Collection<Locacao> locacaoCollectionNew = imovel.getLocacaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Locacao locacaoCollectionOldLocacao : locacaoCollectionOld) {
                if (!locacaoCollectionNew.contains(locacaoCollectionOldLocacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Locacao " + locacaoCollectionOldLocacao + " since its idImovel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoImovelNew != null) {
                idTipoImovelNew = em.getReference(idTipoImovelNew.getClass(), idTipoImovelNew.getIdTipoImovel());
                imovel.setIdTipoImovel(idTipoImovelNew);
            }
            Collection<Locacao> attachedLocacaoCollectionNew = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionNewLocacaoToAttach : locacaoCollectionNew) {
                locacaoCollectionNewLocacaoToAttach = em.getReference(locacaoCollectionNewLocacaoToAttach.getClass(), locacaoCollectionNewLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollectionNew.add(locacaoCollectionNewLocacaoToAttach);
            }
            locacaoCollectionNew = attachedLocacaoCollectionNew;
            imovel.setLocacaoCollection(locacaoCollectionNew);
            imovel = em.merge(imovel);
            if (idTipoImovelOld != null && !idTipoImovelOld.equals(idTipoImovelNew)) {
                idTipoImovelOld.getImovelCollection().remove(imovel);
                idTipoImovelOld = em.merge(idTipoImovelOld);
            }
            if (idTipoImovelNew != null && !idTipoImovelNew.equals(idTipoImovelOld)) {
                idTipoImovelNew.getImovelCollection().add(imovel);
                idTipoImovelNew = em.merge(idTipoImovelNew);
            }
            for (Locacao locacaoCollectionNewLocacao : locacaoCollectionNew) {
                if (!locacaoCollectionOld.contains(locacaoCollectionNewLocacao)) {
                    Imovel oldIdImovelOfLocacaoCollectionNewLocacao = locacaoCollectionNewLocacao.getIdImovel();
                    locacaoCollectionNewLocacao.setIdImovel(imovel);
                    locacaoCollectionNewLocacao = em.merge(locacaoCollectionNewLocacao);
                    if (oldIdImovelOfLocacaoCollectionNewLocacao != null && !oldIdImovelOfLocacaoCollectionNewLocacao.equals(imovel)) {
                        oldIdImovelOfLocacaoCollectionNewLocacao.getLocacaoCollection().remove(locacaoCollectionNewLocacao);
                        oldIdImovelOfLocacaoCollectionNewLocacao = em.merge(oldIdImovelOfLocacaoCollectionNewLocacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imovel.getIdImovel();
                if (findImovel(id) == null) {
                    throw new NonexistentEntityException("The imovel with id " + id + " no longer exists.");
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
            Imovel imovel;
            try {
                imovel = em.getReference(Imovel.class, id);
                imovel.getIdImovel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imovel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Locacao> locacaoCollectionOrphanCheck = imovel.getLocacaoCollection();
            for (Locacao locacaoCollectionOrphanCheckLocacao : locacaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Imovel (" + imovel + ") cannot be destroyed since the Locacao " + locacaoCollectionOrphanCheckLocacao + " in its locacaoCollection field has a non-nullable idImovel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipoimovel idTipoImovel = imovel.getIdTipoImovel();
            if (idTipoImovel != null) {
                idTipoImovel.getImovelCollection().remove(imovel);
                idTipoImovel = em.merge(idTipoImovel);
            }
            em.remove(imovel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imovel> findImovelEntities() {
        return findImovelEntities(true, -1, -1);
    }

    public List<Imovel> findImovelEntities(int maxResults, int firstResult) {
        return findImovelEntities(false, maxResults, firstResult);
    }

    private List<Imovel> findImovelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imovel.class));
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

    public Imovel findImovel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imovel.class, id);
        } finally {
            em.close();
        }
    }

    public int getImovelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imovel> rt = cq.from(Imovel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
