/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.ImovelDAO;
import DAO.TipoimovelDAO;
import entity.Imovel;
import entity.Tipoimovel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TextField inpUpNewTipoImovel;
    private ObservableList<Imovel> listaObs;

    private ImovelController controller;

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
    private TextField inpFoto;
    @FXML
    private Button bttnTelaImoveis1;
    @FXML
    private Button bttnTelaLocatario1;
    @FXML
    private Button bttnTelaRelatorio1;
    @FXML
    private ImageView imgPrincipal;
    @FXML
    private Button bttnVoltar;
    @FXML
    private Button bttnEditar;
    @FXML
    private Button bttnExcluir;
    @FXML
    private TextField upEndereco;
    @FXML
    private TextField upMetragem;
    @FXML
    private TextField upQuartos;
    @FXML
    private TextField upBanheiros;
    @FXML
    private TextField upValor;
    @FXML
    private TextArea upDescricao;
    @FXML
    private ComboBox<Tipoimovel> upTipoImovel;
    @FXML
    private Button btnUpAddTipoImovel;
    @FXML
    private TextField upFoto;
    @FXML
    private AnchorPane viewUpdateImovel;
    @FXML
    private TextField upIdImovel;
    @FXML
    private Button bttnVisualizar;
    @FXML
    private Label viewMetragem;
    @FXML
    private Label viewQuartos;
    @FXML
    private Label viewBanheiros;
    @FXML
    private Label viewValor;
    @FXML
    private Label viewDescricao;
    @FXML
    private Label viewTipoImovel;
    @FXML
    private Label viewLocado;
    @FXML
    private Label viewEndereco;
    @FXML
    private ImageView viewFoto;
    @FXML
    private AnchorPane viewVisuarlizar;
    @FXML
    private Button bttnAdicionarImovel;
    @FXML
    private Button bttnCadastrarImovel;
    @FXML
    private ImageView bttnSearchFile;
    @FXML
    private Button bttnAtualizar;
    @FXML
    private Label labelFeedback1;
    @FXML
    private Label labelFeedback11;

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

    public void setController(ImovelController controller) {
        this.controller = controller;
    }

    public void setDataComboBox() {
        TipoimovelDAO tipoImovel = new TipoimovelDAO();
        this.tImoveis = tipoImovel.getAll();
        ObservableList<Tipoimovel> comboBoxList = FXCollections.observableArrayList(tImoveis);
        this.inpTipoImovel.setItems(comboBoxList);
        this.upTipoImovel.setItems(comboBoxList);
    }

    public void setDataTable() {

        ImovelDAO imovel = new ImovelDAO();

        this.listaObs = FXCollections.observableArrayList(imovel.getAll());

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

    public void callFeedback(String msg, ActionEvent event, String tipo) throws IOException {
        Alert alerta;
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);

        Optional<ButtonType> option = alerta.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            switch (tipo) {
                case "cadastrar":
                    this.insertBanco(event);
                    break;

                case "update":
                    this.updateBanco(event);
                    break;
                case "delete":
                    this.removeImovel(event);
                    break;
            }
        } else {
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setHeaderText(null);
            alerta.setContentText("Cancelado com sucesso!");
            alerta.showAndWait();
        }

    }

    public void insertBanco(ActionEvent event) {
        Alert alerta;
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
            this.listaObs.clear();
            this.listaObs.addAll(imovelDAO.getAll());

            super.telaImoveis(event);

            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setHeaderText(null);
            alerta.setContentText("Imóvel cadastrado com sucesso!");
            alerta.showAndWait();
        } catch (Exception ex) {
            System.out.println("Erro ao inserir imovel: " + ex.getMessage());
        }

    }

    public void updateBanco(ActionEvent event) {
        Alert alerta;
        Imovel objImovel = new Imovel();

        objImovel.setIdImovel(Integer.valueOf(this.upIdImovel.getText()));
        objImovel.setEndereco(this.upEndereco.getText());
        objImovel.setMetragem(Double.parseDouble(this.upMetragem.getText()));
        objImovel.setQuantQuartos(Integer.parseInt(this.upQuartos.getText()));
        objImovel.setQuantBanheiros(Integer.parseInt(this.upBanheiros.getText()));
        objImovel.setValorLocacao(Double.parseDouble(this.upValor.getText()));
        objImovel.setFotoImovel(this.upFoto.getText());
        objImovel.setDescricaoDependencias(this.upDescricao.getText());

        if (!this.flag_new_timovel) {
            objImovel.setIdTipoImovel(this.upTipoImovel.getValue());

        } else {
            Tipoimovel objTipoImovel = new Tipoimovel();

            objTipoImovel.setDescricao(this.inpUpNewTipoImovel.getText());

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
            imovelDAO.edit(objImovel);
            this.listaObs.clear();
            this.listaObs.addAll(imovelDAO.getAll());

            super.telaImoveis(event);

            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("");
            alerta.setHeaderText(null);
            alerta.setContentText("Imóvel atualizado com sucesso!");
            alerta.showAndWait();
        } catch (Exception ex) {
            System.out.println("Erro ao atualizar imovel: " + ex.getMessage());
        }
    }

    @FXML
    private void viewImovel() {
        this.viewImoveis.setVisible(true);

        this.viewCadastroImovel.setVisible(false);
        this.viewUpdateImovel.setVisible(false);
        this.viewVisuarlizar.setVisible(false);
    }

    @FXML
    private void viewCadastrarImovel(ActionEvent event) {
        this.viewImoveis.setVisible(false);

        this.viewCadastroImovel.setVisible(true);

        this.viewUpdateImovel.setVisible(false);

        this.viewVisuarlizar.setVisible(false);
    }

    @FXML
    private void addTipoImovel(ActionEvent event) {
        this.flag_new_timovel = true;
        this.viewCadastroImovel.getChildren().remove(this.inpTipoImovel);
        this.viewCadastroImovel.getChildren().remove(this.btnAddTipoImovel);

        this.inpNewTipoImovel = new TextField();
        inpNewTipoImovel.prefWidth(150);
        inpNewTipoImovel.setLayoutX(105);
        inpNewTipoImovel.setLayoutY(420);

        this.viewCadastroImovel.getChildren().add(inpNewTipoImovel);
    }

    @FXML
    private void cadastrarImovel(ActionEvent event) {
        Alert alerta;
        if (this.inpBanheiros.getText().isEmpty()
                || this.inpDescricao.getText().isEmpty()
                || this.inpEndereco.getText().isEmpty()
                || this.inpFoto.getText().isEmpty()
                || this.inpMetragem.getText().isEmpty()
                || this.inpQuartos.getText().isEmpty()
                || ((this.inpTipoImovel.getSelectionModel().getSelectedItem() == null) && (!this.flag_new_timovel))
                || ((this.inpNewTipoImovel != null) && (this.inpNewTipoImovel.getText().isEmpty()))
                || this.inpValor.getText().isEmpty()) {

            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("");
            alerta.setHeaderText(null);
            alerta.setContentText("Preencha todos os campos!");
            alerta.showAndWait();
        } else {

            try {
                this.callFeedback("Deseja cadastrar este imovel?", event, "cadastrar");
            } catch (IOException ex) {
                Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void editButtons(MouseEvent event) {
        int num = this.tableImovel.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        this.bttnEditar.setDisable(false);
        this.bttnExcluir.setDisable(false);
        this.bttnVisualizar.setDisable(false);
    }

    @FXML
    private void bttnRemoveImovel(ActionEvent event) {
        Imovel tableImovel = this.tableImovel.getSelectionModel().getSelectedItem();
        
        try {
            this.callFeedback("Deseja remover o imóvel de id " + tableImovel.getIdImovel()+ "?", event, "delete");
        } catch (IOException ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeImovel(ActionEvent event) {
        Alert alerta;
        Imovel tableImovel = this.tableImovel.getSelectionModel().getSelectedItem();

        ImovelDAO imovelDao = new ImovelDAO();
        try {
            imovelDao.delete(tableImovel.getIdImovel());
        } catch (Exception ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        super.telaImoveis(event);
        
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("");
        alerta.setHeaderText(null);
        alerta.setContentText("Imóvel removido com sucesso!");
        alerta.showAndWait();
    }

    @FXML
    private void addUpTipoImovel(ActionEvent event) {
        this.flag_new_timovel = true;
        this.viewCadastroImovel.getChildren().remove(this.upTipoImovel);
        this.viewCadastroImovel.getChildren().remove(this.btnUpAddTipoImovel);

        this.inpUpNewTipoImovel = new TextField();
        this.inpUpNewTipoImovel.prefWidth(150);
        this.inpUpNewTipoImovel.setLayoutX(105);
        this.inpUpNewTipoImovel.setLayoutY(420);

        this.viewCadastroImovel.getChildren().add(this.inpUpNewTipoImovel);
    }

    @FXML
    private void viewUpdateImovel(ActionEvent event) {
        this.viewCadastroImovel.setVisible(false);
        this.viewImoveis.setVisible(false);
        this.viewVisuarlizar.setVisible(false);
        this.viewUpdateImovel.setVisible(true);

        Imovel tableImovel = this.tableImovel.getSelectionModel().getSelectedItem();

        this.upIdImovel.setText(String.valueOf(tableImovel.getIdImovel()));
        this.upBanheiros.setText(String.valueOf(tableImovel.getQuantBanheiros()));
        this.upDescricao.setText(tableImovel.getDescricaoDependencias());
        this.upEndereco.setText(tableImovel.getEndereco());
        this.upQuartos.setText(String.valueOf(tableImovel.getQuantQuartos()));
        this.upMetragem.setText(String.valueOf(tableImovel.getMetragem()));
        this.upValor.setText(String.valueOf(tableImovel.getValorLocacao()));
        this.upFoto.setText(tableImovel.getFotoImovel());

        this.upTipoImovel.getSelectionModel().select(tableImovel.getIdTipoImovel());
    }

    @FXML
    private void editImovel(ActionEvent event) {

        try {
            callFeedback("Deseja atualizar o imovel com id " + this.upIdImovel.getText() + "?", event, "update");
        } catch (IOException ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewSelectImovel(ActionEvent event) {
        this.viewImoveis.setVisible(false);

        this.viewCadastroImovel.setVisible(false);

        this.viewUpdateImovel.setVisible(false);

        this.viewVisuarlizar.setVisible(true);

        Imovel tableImovel = this.tableImovel.getSelectionModel().getSelectedItem();

        this.viewBanheiros.setText(String.valueOf(tableImovel.getQuantBanheiros()));
        this.viewDescricao.setText(tableImovel.getDescricaoDependencias());
        this.viewEndereco.setText(tableImovel.getEndereco());
        this.viewQuartos.setText(String.valueOf(tableImovel.getQuantQuartos()));
        this.viewMetragem.setText(String.valueOf(tableImovel.getMetragem()));
        this.viewValor.setText(String.valueOf(tableImovel.getValorLocacao()));
        this.viewTipoImovel.setText(tableImovel.getIdTipoImovel().getDescricao());

        if (tableImovel.getAlocado().TRUE) {
            this.viewLocado.setText("Sim");
        } else {
            this.viewLocado.setText("Não");
        }

        try {
            Image img = new Image(tableImovel.getFotoImovel());
            this.viewFoto.setImage(img);
        } catch (IllegalArgumentException ex) {

            this.viewFoto.setImage(new Image("src\\image-not-found.png"));
        }

    }

    @FXML
    private void getPath(MouseEvent event) {
        FileChooser dir = new FileChooser();

        dir.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        Stage s = (Stage) this.viewCadastroImovel.getScene().getWindow();

        File file = dir.showOpenDialog(s);

        if (file != null) {
            this.inpFoto.setText(file.getPath());
        }
    }

    @FXML
    private void getPathUp(MouseEvent event) {
        FileChooser dir = new FileChooser();

        dir.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        Stage s = (Stage) this.viewUpdateImovel.getScene().getWindow();

        File file = dir.showOpenDialog(s);

        if (file != null) {
            this.upFoto.setText(file.getPath());
        }
    }
}
