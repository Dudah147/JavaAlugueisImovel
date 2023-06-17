/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entity.Relatorio;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author henri
 */
public class RelatorioController extends TrocaTelas implements Initializable {

    @FXML
    private Button bttnTelaImoveis;
    @FXML
    private Button bttnTelaLocatario;
    @FXML
    private Button bttnTelaRelatorio;
    @FXML
    private ImageView imgPrincipal;
    @FXML
    private TableView<Relatorio> TbLocacao;
    @FXML
    private TableColumn<Relatorio, Integer> IdImovel;
    @FXML
    private TableColumn<Relatorio, Integer> IdLocatario;
    @FXML
    private TableColumn<Relatorio, Date>EncerraContrato;
    @FXML
    private TableColumn<Relatorio, Double> Aluguel;
    private List<Relatorio> resultados ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setDataTable();
        this.atualizarTabela();
    }    
    
    
    public void setDataTable() {  
        this.IdLocatario.setCellValueFactory(new PropertyValueFactory<>("idLocatario"));
        this.IdImovel.setCellValueFactory(new PropertyValueFactory<>("idImovel"));
        this.EncerraContrato.setCellValueFactory(new PropertyValueFactory<>("dataTermino"));
        this.Aluguel.setCellValueFactory(new PropertyValueFactory<>("valorLocacao")); 
    }
    private void atualizarTabela(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaAlugueisImovelPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "SELECT i FROM Relatorio i";
        TypedQuery<Relatorio> query = entityManager.createQuery(jpql, Relatorio.class);

    // Executa a consulta e obtém os resultados
       this.resultados = query.getResultList();
       TbLocacao.getItems().setAll(this.resultados);
    }
}
