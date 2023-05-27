/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TipoImovel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.TipoImovelJpaController;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author a2319217
 */
public class TipoImovelDAO {
    private final TipoImovelJpaController objetoJPA;
    private final EntityManagerFactory emf;
    
    public TipoImovelDAO(){
        this.emf = Persistence.createEntityManagerFactory("ProjetoAluguelImovelPU");
        this.objetoJPA = new TipoImovelJpaController(emf);
    }
    
    public void add(TipoImovel objeto)throws Exception{
        this.objetoJPA.create(objeto);
    }
    
    public void edit(TipoImovel objeto) throws Exception{
        this.objetoJPA.edit(objeto);
    }    
    
    public void remove(int id) throws NonexistentEntityException, IllegalOrphanException{
        this.objetoJPA.destroy(id);
    }
    
    public List<TipoImovel> getAllTipoImovel(int id){
        return this.objetoJPA.findTipoImovelEntities();
    }
    
    public void persist(TipoImovel object){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
}
