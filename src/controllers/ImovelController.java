/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.ImovelDAO;
import DAO.TipoimovelDAO;
import entity.Imovel;
import entity.Tipoimovel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author dudam
 */
public class ImovelController extends TrocaTelas implements Initializable {

    private boolean flag_new_timovel;
    private List<Tipoimovel> tImoveis;
    private TextField inpNewTipoImovel;

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
    @FXML
    private Button bttnCadastrarImovel;
    @FXML
    private TextField inpFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Adiciona restrição de caracteres
        this.doubleField(this.inpMetragem);
        this.intField(this.inpBanheiros);
        this.intField(this.inpQuartos);
        this.doubleField(this.inpValor);

        //Itens tabela viewImovel
        this.setDataTable();

        //Combo box para tipo imovel de viewCadastroImovel
        this.setDataComboBox();
    }

    public void setDataComboBox() {
        TipoimovelDAO tipoImovel = new TipoimovelDAO();
        this.tImoveis = tipoImovel.getAll();
        ObservableList<Tipoimovel> comboBoxList = FXCollections.observableArrayList(tImoveis);
        this.inpTipoImovel.setItems(comboBoxList);
    }

    public void setDataTable() {

        ImovelDAO imovel = new ImovelDAO();

        List<Imovel> imoveis = imovel.getAll();

        ObservableList<Imovel> listaObs = FXCollections.observableArrayList(imoveis);

        this.idImovel.setCellValueFactory(new PropertyValueFactory<>("idImovel"));
        this.endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        this.metragem.setCellValueFactory(new PropertyValueFactory<>("metragem"));
        this.valor.setCellValueFactory(new PropertyValueFactory<>("valorLocacao"));

        this.tableImovel.setItems(listaObs);
    }

    public void doubleField(TextField t) {
        t.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("|[-\\+]?|[-\\+]?\\d+\\.?|[-\\+]?\\d+\\.?\\d+")) {
                    t.setText(oldValue);
                }
            }
        });
    }

    public void intField(TextField t) {
        t.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("[0-9]*")) {
                    t.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void viewCadastrarImovel(ActionEvent event) {
        this.viewImoveis.setVisible(false);

        this.viewCadastroImovel.setVisible(true);
    }

    @FXML
    private void addTipoImovel(ActionEvent event) {
        this.flag_new_timovel = true;
        this.viewCadastroImovel.getChildren().remove(this.inpTipoImovel);
        this.viewCadastroImovel.getChildren().remove(this.btnAddTipoImovel);

        this.inpNewTipoImovel = new TextField();
        inpNewTipoImovel.prefWidth(150);
        inpNewTipoImovel.setLayoutX(100);
        inpNewTipoImovel.setLayoutY(292);

        this.viewCadastroImovel.getChildren().add(inpNewTipoImovel);
    }

    @FXML
    private void cadastrarImovel(ActionEvent event) {
        Imovel objImovel = new Imovel();
        objImovel.setEndereco(this.inpEndereco.getText());
        objImovel.setMetragem(Double.parseDouble(this.inpMetragem.getText()));
        objImovel.setQuantQuartos(Integer.parseInt(this.inpQuartos.getText()));
        objImovel.setQuantBanheiros(Integer.parseInt(this.inpBanheiros.getText()));
        objImovel.setValorLocacao(Double.parseDouble(this.inpValor.getText()));
        objImovel.setFotoImovel(this.inpFoto.getText());
        objImovel.setDescricaoDependencias(this.inpDescricao.getText());
        if (!this.flag_new_timovel) {

            objImovel.setIdTipoImovel(this.inpTipoImovel.getValue());

        } else {
            Tipoimovel objTipoImovel = new Tipoimovel();

            objTipoImovel.setDescricao(this.inpNewTipoImovel.getText());

            try {
                TipoimovelDAO tImovelDAO = new TipoimovelDAO();
                tImovelDAO.add(objTipoImovel);
                System.out.println("Tipo imovel inserido com sucesso");

                objImovel.setIdTipoImovel(objTipoImovel);
            } catch (Exception ex) {
                System.out.println("Erro ao inserir tipo imovel: " + ex.getMessage());
            }
        }

        try {
            ImovelDAO imovelDAO = new ImovelDAO();
            imovelDAO.add(objImovel);
            System.out.println("Imovel inserido com sucesso");
        } catch (Exception ex) {
            System.out.println("Erro ao inserir imovel: " + ex.getMessage());
        }

        try {
            this.callFeedback("Deseja cadastrar este imovel?");
        } catch (IOException ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callFeedback(String msg) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlugarImovel.fxml"));
        
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        FeedbackController controller = loader.getController();
        
        controller.initText(msg);
    }
}
