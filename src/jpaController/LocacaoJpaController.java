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
import entity.Locacao;
import entity.Locatario;
import entity.Pagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class LocacaoJpaController implements Serializable {

    public LocacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locacao locacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imovel idImovel = locacao.getIdImovel();
            if (idImovel != null) {
                idImovel = em.getReference(idImovel.getClass(), idImovel.getIdImovel());
                locacao.setIdImovel(idImovel);
            }
            Locatario idLocatario = locacao.getIdLocatario();
            if (idLocatario != null) {
                idLocatario = em.getReference(idLocatario.getClass(), idLocatario.getIdLocatario());
                locacao.setIdLocatario(idLocatario);
            }
            Pagamento idPagamento = locacao.getIdPagamento();
            if (idPagamento != null) {
                idPagamento = em.getReference(idPagamento.getClass(), idPagamento.getIdPagamento());
                locacao.setIdPagamento(idPagamento);
            }
            em.persist(locacao);
            if (idImovel != null) {
                idImovel = em.merge(idImovel);
            }
            if (idPagamento != null) {
                idPagamento.getLocacaoCollection().add(locacao);
                idPagamento = em.merge(idPagamento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locacao locacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locacao persistentLocacao = em.find(Locacao.class, locacao.getIdLocacao());
            Imovel idImovelOld = persistentLocacao.getIdImovel();
            Imovel idImovelNew = locacao.getIdImovel();
            Locatario idLocatarioOld = persistentLocacao.getIdLocatario();
            Locatario idLocatarioNew = locacao.getIdLocatario();
            Pagamento idPagamentoOld = persistentLocacao.getIdPagamento();
            Pagamento idPagamentoNew = locacao.getIdPagamento();
            if (idImovelNew != null) {
                idImovelNew = em.getReference(idImovelNew.getClass(), idImovelNew.getIdImovel());
                locacao.setIdImovel(idImovelNew);
            }
            if (idLocatarioNew != null) {
                idLocatarioNew = em.getReference(idLocatarioNew.getClass(), idLocatarioNew.getIdLocatario());
                locacao.setIdLocatario(idLocatarioNew);
            }
            if (idPagamentoNew != null) {
                idPagamentoNew = em.getReference(idPagamentoNew.getClass(), idPagamentoNew.getIdPagamento());
                locacao.setIdPagamento(idPagamentoNew);
            }
            locacao = em.merge(locacao);
            if (idImovelOld != null && !idImovelOld.equals(idImovelNew)) {
                idImovelOld = em.merge(idImovelOld);
            }
            if (idImovelNew != null && !idImovelNew.equals(idImovelOld)) {
                idImovelNew = em.merge(idImovelNew);
            }


            if (idPagamentoOld != null && !idPagamentoOld.equals(idPagamentoNew)) {
                idPagamentoOld.getLocacaoCollection().remove(locacao);
                idPagamentoOld = em.merge(idPagamentoOld);
            }
            if (idPagamentoNew != null && !idPagamentoNew.equals(idPagamentoOld)) {
                idPagamentoNew.getLocacaoCollection().add(locacao);
                idPagamentoNew = em.merge(idPagamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = locacao.getIdLocacao();
                if (findLocacao(id) == null) {
                    throw new NonexistentEntityException("The locacao with id " + id + " no longer exists.");
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
            Locacao locacao;
            try {
                locacao = em.getReference(Locacao.class, id);
                locacao.getIdLocacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locacao with id " + id + " no longer exists.", enfe);
            }
            Imovel idImovel = locacao.getIdImovel();
            if (idImovel != null) {
                idImovel = em.merge(idImovel);
            }
            Locatario idLocatario = locacao.getIdLocatario();

            Pagamento idPagamento = locacao.getIdPagamento();
            if (idPagamento != null) {
                idPagamento.getLocacaoCollection().remove(locacao);
                idPagamento = em.merge(idPagamento);
            }
            em.remove(locacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locacao> findLocacaoEntities() {
        return findLocacaoEntities(true, -1, -1);
    }

    public List<Locacao> findLocacaoEntities(int maxResults, int firstResult) {
        return findLocacaoEntities(false, maxResults, firstResult);
    }

    private List<Locacao> findLocacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locacao.class));
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

    public Locacao findLocacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locacao> rt = cq.from(Locacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
