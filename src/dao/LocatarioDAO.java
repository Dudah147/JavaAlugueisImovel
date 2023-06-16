/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.Locatario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.LocatarioJpaController;
import jpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author dudam
 */
public class LocatarioDAO {
    private LocatarioJpaController objetoJPA;
    private EntityManagerFactory emf;

    public LocatarioDAO() {
        emf = Persistence.createEntityManagerFactory("JavaAlugueisImovelPU");
        objetoJPA = new LocatarioJpaController(emf);
    }
    
    public void add(Locatario objeto) throws Exception{

        objetoJPA.create(objeto);
    }

    public void edit(Locatario objeto) throws Exception{
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

    public List<Locatario> getAll(){
        return objetoJPA.findLocatarioEntities();
    }
    
    public Locatario getLocatario (Integer id){
        return objetoJPA.findLocatario(id);
    }
    
    /**
     * @return the objetoJPA
     */
    public LocatarioJpaController getObjetoJPA() {
        return objetoJPA;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
