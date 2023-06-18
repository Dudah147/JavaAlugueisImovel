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
import entity.Pagamento;
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
public class PagamentoJpaController implements Serializable {

    public PagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagamento pagamento) {
        if (pagamento.getLocacaoCollection() == null) {
            pagamento.setLocacaoCollection(new ArrayList<Locacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Locacao> attachedLocacaoCollection = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionLocacaoToAttach : pagamento.getLocacaoCollection()) {
                locacaoCollectionLocacaoToAttach = em.getReference(locacaoCollectionLocacaoToAttach.getClass(), locacaoCollectionLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollection.add(locacaoCollectionLocacaoToAttach);
            }
            pagamento.setLocacaoCollection(attachedLocacaoCollection);
            em.persist(pagamento);
            for (Locacao locacaoCollectionLocacao : pagamento.getLocacaoCollection()) {
                Pagamento oldIdPagamentoOfLocacaoCollectionLocacao = locacaoCollectionLocacao.getIdPagamento();
                locacaoCollectionLocacao.setIdPagamento(pagamento);
                locacaoCollectionLocacao = em.merge(locacaoCollectionLocacao);
                if (oldIdPagamentoOfLocacaoCollectionLocacao != null) {
                    oldIdPagamentoOfLocacaoCollectionLocacao.getLocacaoCollection().remove(locacaoCollectionLocacao);
                    oldIdPagamentoOfLocacaoCollectionLocacao = em.merge(oldIdPagamentoOfLocacaoCollectionLocacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagamento pagamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagamento persistentPagamento = em.find(Pagamento.class, pagamento.getIdPagamento());
            Collection<Locacao> locacaoCollectionOld = persistentPagamento.getLocacaoCollection();
            Collection<Locacao> locacaoCollectionNew = pagamento.getLocacaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Locacao locacaoCollectionOldLocacao : locacaoCollectionOld) {
                if (!locacaoCollectionNew.contains(locacaoCollectionOldLocacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Locacao " + locacaoCollectionOldLocacao + " since its idPagamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Locacao> attachedLocacaoCollectionNew = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionNewLocacaoToAttach : locacaoCollectionNew) {
                locacaoCollectionNewLocacaoToAttach = em.getReference(locacaoCollectionNewLocacaoToAttach.getClass(), locacaoCollectionNewLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollectionNew.add(locacaoCollectionNewLocacaoToAttach);
            }
            locacaoCollectionNew = attachedLocacaoCollectionNew;
            pagamento.setLocacaoCollection(locacaoCollectionNew);
            pagamento = em.merge(pagamento);
            for (Locacao locacaoCollectionNewLocacao : locacaoCollectionNew) {
                if (!locacaoCollectionOld.contains(locacaoCollectionNewLocacao)) {
                    Pagamento oldIdPagamentoOfLocacaoCollectionNewLocacao = locacaoCollectionNewLocacao.getIdPagamento();
                    locacaoCollectionNewLocacao.setIdPagamento(pagamento);
                    locacaoCollectionNewLocacao = em.merge(locacaoCollectionNewLocacao);
                    if (oldIdPagamentoOfLocacaoCollectionNewLocacao != null && !oldIdPagamentoOfLocacaoCollectionNewLocacao.equals(pagamento)) {
                        oldIdPagamentoOfLocacaoCollectionNewLocacao.getLocacaoCollection().remove(locacaoCollectionNewLocacao);
                        oldIdPagamentoOfLocacaoCollectionNewLocacao = em.merge(oldIdPagamentoOfLocacaoCollectionNewLocacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagamento.getIdPagamento();
                if (findPagamento(id) == null) {
                    throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.");
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
            Pagamento pagamento;
            try {
                pagamento = em.getReference(Pagamento.class, id);
                pagamento.getIdPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Locacao> locacaoCollectionOrphanCheck = pagamento.getLocacaoCollection();
            for (Locacao locacaoCollectionOrphanCheckLocacao : locacaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pagamento (" + pagamento + ") cannot be destroyed since the Locacao " + locacaoCollectionOrphanCheckLocacao + " in its locacaoCollection field has a non-nullable idPagamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagamento> findPagamentoEntities() {
        return findPagamentoEntities(true, -1, -1);
    }

    public List<Pagamento> findPagamentoEntities(int maxResults, int firstResult) {
        return findPagamentoEntities(false, maxResults, firstResult);
    }

    private List<Pagamento> findPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagamento.class));
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

    public Pagamento findPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagamento> rt = cq.from(Pagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
