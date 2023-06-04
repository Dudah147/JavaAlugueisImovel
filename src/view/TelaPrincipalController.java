/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import DAO.ImovelDAO;
import entity.Imovel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author dudam
 */
public class TelaPrincipalController extends TrocaTelas implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button bttnTelaAlugarImovel;
    @FXML
    private Button bttnTelaImoveis;
    @FXML
    private Button bttnTelaLocatario;
    @FXML
    private Button bttnTelaRelatorio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
