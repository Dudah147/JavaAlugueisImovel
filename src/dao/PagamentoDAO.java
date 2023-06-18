/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Pagamento;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.PagamentoJpaController;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class PagamentoDAO {
    private PagamentoJpaController objetoJPA;
    private EntityManagerFactory emf;

    public PagamentoDAO() {
        emf = Persistence.createEntityManagerFactory("JavaAlugueisImovelPU");
        objetoJPA = new PagamentoJpaController(emf);
    }
    
    public void add(Pagamento objeto) throws Exception{

        objetoJPA.create(objeto);
    }

    public void edit(Pagamento objeto) throws Exception{
        try {
            objetoJPA.edit(objeto);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Tipoimovel " + objeto + " não existe.", ex);
        }
    }

    public void delete(Integer id) throws Exception{
        objetoJPA.destroy(id);
    }

    public List<Pagamento> getAll(){
        return objetoJPA.findPagamentoEntities();
                //findTipoimovelEntities();
    }
    
    public Pagamento getPagamento(Integer id){
        return (Pagamento) objetoJPA.findPagamento(id);
    }
    
    /**
     * @return the objetoJPA
     */
    public PagamentoJpaController getObjetoJPA() {
        return objetoJPA;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
