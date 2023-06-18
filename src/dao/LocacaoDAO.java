/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.Locacao;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.LocacaoJpaController;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class LocacaoDAO {
    private LocacaoJpaController objetoJPA;
    private EntityManagerFactory emf;

    public LocacaoDAO() {
        emf = Persistence.createEntityManagerFactory("JavaAlugueisImovelPU");
        objetoJPA = new LocacaoJpaController(emf);
    }
    
    public void add(Locacao objeto) throws Exception{

        objetoJPA.create(objeto);
    }

    public void edit(Locacao objeto) throws Exception{
        try {
            objetoJPA.edit(objeto);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Locacao " + objeto + " não existe.", ex);
        }
    }

    public void delete(Integer id) throws Exception{
        try {
            objetoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Locacao " + id + " não existe.", ex);
        }
    }

    public List<Locacao> getAll(){
        return objetoJPA.findLocacaoEntities();
    }
    
    public Locacao getLocacao(Integer id){
        return objetoJPA.findLocacao(id);
    }
    
    /**
     * @return the objetoJPA
     */
    public LocacaoJpaController getObjetoJPA() {
        return objetoJPA;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
