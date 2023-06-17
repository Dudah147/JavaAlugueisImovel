/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.Tipoimovel;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.TipoImovelJpaController;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;
import jpaController.exceptions.PreexistingEntityException;

/**
 *
 * @author dudam
 */
public class TipoimovelDAO {
    private TipoImovelJpaController objetoJPA;
    private EntityManagerFactory emf;

    public TipoimovelDAO() {
        emf = Persistence.createEntityManagerFactory("JavaAlugueisImovelPU");
        objetoJPA = new TipoImovelJpaController(emf);
    }
    
    public void add(Tipoimovel objeto) throws Exception{

        objetoJPA.create(objeto);
    }

    public void edit(Tipoimovel objeto) throws Exception{
        try {
            objetoJPA.edit(objeto);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Tipoimovel " + objeto + " não existe.", ex);
        }
    }

    public void delete(Integer id) throws Exception{
        try {
            objetoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Tipoimovel " + id + " não existe.", ex);
        }
    }

    public List<Tipoimovel> getAll(){
        return objetoJPA.findTipoimovelEntities();
                //findTipoimovelEntities();
    }
    
    public Tipoimovel getTipoimovel(Integer id){
        return (Tipoimovel) objetoJPA.findTipoimovel(id);
    }
    
    /**
     * @return the objetoJPA
     */
    public TipoImovelJpaController getObjetoJPA() {
        return objetoJPA;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
