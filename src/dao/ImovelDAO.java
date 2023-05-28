/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.Imovel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.ImovelJpaController;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class ImovelDAO {
    private ImovelJpaController objetoJPA;
    private EntityManagerFactory emf;

    public ImovelDAO() {
        emf = Persistence.createEntityManagerFactory("ProjetoJavaAluguelImovelPU");
        objetoJPA = new ImovelJpaController(emf);
    }
    
    public void add(Imovel objeto) throws Exception{

        objetoJPA.create(objeto);
    }

    public void edit(Imovel objeto) throws Exception{
        try {
            objetoJPA.edit(objeto);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Imovel " + objeto + " não existe.", ex);
        }
    }

    public void delete(Integer id) throws Exception{
        try {
            objetoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Imovel " + id + " não existe.", ex);
        }
    }

    public List<Imovel> getAll(){
        return objetoJPA.findImovelEntities();
    }
    
    public Imovel getTipoimovel(Integer id){
        return objetoJPA.findImovel(id);
    }
    
    /**
     * @return the objetoJPA
     */
    public ImovelJpaController getObjetoJPA() {
        return objetoJPA;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
