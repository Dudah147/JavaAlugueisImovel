/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.LocatarioDAO;
import entity.Locatario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author dudam
 */
public class AlugarImovelController extends TrocaTelas implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button bttnTelaImoveis1;
    @FXML
    private Button bttnTelaLocatario1;
    @FXML
    private Button bttnTelaRelatorio1;
    @FXML
    private ImageView imgPrincipal;
    @FXML
    private TextField inpImovel;
    @FXML
    private TextField inpValor;
    @FXML
    private TextField inpDesconto;
    @FXML
    private ComboBox<Locatario> inpLocatario;
    @FXML
    private DatePicker inpDataInicio;
    @FXML
    private DatePicker inpDataTermino;
    @FXML
    private ListView<?> inpFormaPgt;
    @FXML
    private Button bttnAlugar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setDataComboBox();
    }
    
    public void iniciarCampos(String end, double valor){
        this.inpImovel.setText(end);
        this.inpValor.setText(String.valueOf(valor));
    }
    
    public void setDataListView(){
        
    }
    
    public void setDataComboBox() {
        LocatarioDAO locatarioDados = new LocatarioDAO();
                
        ObservableList<Locatario> comboBoxList = FXCollections.observableArrayList(locatarioDados.getAll());
        
        this.inpLocatario.setItems(comboBoxList);
    }

    @FXML
    private void alugarImovel(ActionEvent event) {
    }
}
