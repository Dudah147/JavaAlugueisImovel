/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import DAO.ImovelDAO;
import DAO.TipoimovelDAO;
import entity.Imovel;
import entity.Tipoimovel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author dudam
 */
public class ImovelController extends TrocaTelas implements Initializable {
    private boolean flag_new_timovel;
    @FXML
    private Button bttnTelaAlugarImovel;
    @FXML
    private Button bttnTelaImoveis;
    @FXML
    private Button bttnTelaLocatario;
    @FXML
    private Button bttnTelaRelatorio;
    @FXML
    private TableView<Imovel> tableImovel;
    @FXML
    private TableColumn<Imovel, Integer> idImovel;
    @FXML
    private TableColumn<Imovel, String> endereco;
    @FXML
    private TableColumn<Imovel, Double> metragem;
    @FXML
    private TableColumn<Imovel, Double> valor;
    @FXML
    private Button bttnAdicionarImovel;
    @FXML
    private AnchorPane viewImoveis;
    @FXML
    private AnchorPane viewCadastroImovel;
    @FXML
    private TextField inpEndereco;
    @FXML
    private TextField inpMetragem;
    @FXML
    private TextField inpQuartos;
    @FXML
    private TextField inpBanheiros;
    @FXML
    private TextField inpValor;
    @FXML
    private TextArea inpDescricao;
    @FXML
    private ComboBox<Tipoimovel> inpTipoImovel;
    @FXML
    private Button btnAddTipoImovel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Itens tabela viewImovel
        ImovelDAO imovel = new ImovelDAO();

        List<Imovel> imoveis = imovel.getAll();

        ObservableList<Imovel> listaObs = FXCollections.observableArrayList(imoveis);

        this.idImovel.setCellValueFactory(new PropertyValueFactory<>("idImovel"));
        this.endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        this.metragem.setCellValueFactory(new PropertyValueFactory<>("metragem"));
        this.valor.setCellValueFactory(new PropertyValueFactory<>("valorLocacao"));

        this.tableImovel.setItems(listaObs);
        
        //Combo box para tipo imovel de viewCadastroImovel
        TipoimovelDAO tipoImovel = new TipoimovelDAO();
        List<Tipoimovel> tImoveis = tipoImovel.getAll();
        ObservableList<Tipoimovel> comboBoxList = FXCollections.observableArrayList(tImoveis);
        this.inpTipoImovel.setItems(comboBoxList);
    }    

    @FXML
    private void viewCadastrarImovel(ActionEvent event) {
        this.viewImoveis.setVisible(false);
        
        this.viewCadastroImovel.setVisible(true);
    }

    @FXML
    private void addTipoImovel(ActionEvent event) {
        this.viewCadastroImovel.getChildren().remove(this.inpTipoImovel);
        
        TextField inpNewTipoImovel = new TextField();
        this.flag_new_timovel = true;
        
        
    }
}
