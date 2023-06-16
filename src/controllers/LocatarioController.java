/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.LocatarioDAO;
import entity.Locatario;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import logico.TrocaTelas;

/**
 * FXML Controller class
 *
 * @author dudam
 */
public class LocatarioController extends TrocaTelas implements Initializable {

    private ObservableList<Locatario> listaObs;
    private LocatarioDAO locatarioDados;

    @FXML
    private Button bttnTelaImoveis1;
    @FXML
    private Button bttnTelaLocatario1;
    @FXML
    private Button bttnTelaRelatorio1;
    @FXML
    private ImageView imgPrincipal;
    @FXML
    private AnchorPane viewLocatarios;
    @FXML
    private TableView<Locatario> tableLocatario;
    @FXML
    private TableColumn<Locatario, Integer> idLocatario;
    @FXML
    private TableColumn<Locatario, String> nome;
    @FXML
    private TableColumn<Locatario, String> cpf;
    @FXML
    private TableColumn<Locatario, String> estadoCivil;
    @FXML
    private TableColumn<Locatario, Date> dataNasc;
    @FXML
    private Button bttnExcluir;
    @FXML
    private Button bttnEditar;
    @FXML
    private Button bttnAdicionarLocatario;
    @FXML
    private AnchorPane viewCadastrarLocatario;
    @FXML
    private Button bttnCadastrarLocatario;
    @FXML
    private Button bttnVoltar;
    @FXML
    private TextField inpCPF;
    @FXML
    private TextField inpNome;
    @FXML
    private ComboBox<String> inpEstadoCivil;
    @FXML
    private DatePicker inpDataNasc;
    @FXML
    private TextField inpBanco;
    @FXML
    private AnchorPane viewEditarLocatario;
    @FXML
    private Button bttnAtualizarLocatario;
    @FXML
    private TextField upIdLocatario;
    @FXML
    private TextField upCPF;
    @FXML
    private TextField upNome;
    @FXML
    private ComboBox<String> upEstadoCivil;
    @FXML
    private DatePicker upDataNasc;
    @FXML
    private TextField upBanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.locatarioDados = new LocatarioDAO();
        // Adiciona dados a tabela
        this.setDataTable();

        // Adiciona dados ao comboBox
        this.setDataComboBox();

        // Retira campo txt de datePicker
        this.inpDataNasc.getEditor().setDisable(true);
        this.inpDataNasc.getEditor().setOpacity(1);
    }

    public void setDataComboBox() {
        String[] estCivil = {"Solteiro (a)", "Casado (a)", "Viúvo (a)"};
        List<String> listaEstadoCivil = new ArrayList<String>();

        listaEstadoCivil.addAll(Arrays.asList(estCivil));

        ObservableList<String> comboBoxList = FXCollections.observableArrayList(listaEstadoCivil);
        this.inpEstadoCivil.setItems(comboBoxList);
        this.upEstadoCivil.setItems(comboBoxList);
    }

    public void setDataTable() {

        this.listaObs = FXCollections.observableArrayList(this.locatarioDados.getAll());

        this.idLocatario.setCellValueFactory(new PropertyValueFactory<>("idLocatario"));
        this.nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        this.estadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        this.dataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        this.dataNasc.setCellFactory(column -> {
            TableCell<Locatario, Date> cell = new TableCell<Locatario, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        this.tableLocatario.setItems(listaObs);
    }

    public void alertConfirmation(String msg, ActionEvent event, String tipo) throws IOException {
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
                    break;
            }
        } else {
            this.alertInformation("Cancelado com sucesso!");
        }

    }

    public void alertInformation(String msg) {
        Alert alerta;
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    public void alertError(String msg) {
        Alert alerta;
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    @FXML
    private void editButtons(MouseEvent event) {
        this.bttnEditar.setDisable(false);
        this.bttnExcluir.setDisable(false);
    }

    @FXML
    private void bttnRemoveLocatario(ActionEvent event) {
    }

    @FXML
    private void viewUpdateLocatario(ActionEvent event) {
        this.viewLocatarios.setVisible(false);
        this.viewEditarLocatario.setVisible(true);
        this.viewCadastrarLocatario.setVisible(false);

        Locatario tableLoc = this.tableLocatario.getSelectionModel().getSelectedItem();

        this.upIdLocatario.setText(String.valueOf(tableLoc.getIdLocatario()));
        this.upBanco.setText(tableLoc.getContaBancaria());
        this.upCPF.setText(tableLoc.getCpf());
        this.upNome.setText(tableLoc.getNome());
        
        this.upDataNasc.getEditor().setDisable(true);
        this.upDataNasc.getEditor().setOpacity(1);
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String data = String.valueOf(format.format(tableLoc.getDataNascimento()));
        
        this.upDataNasc.setValue(LocalDate.parse(data, formatter));
        
        this.upEstadoCivil.getSelectionModel().select(tableLoc.getEstadoCivil());
    }

    @FXML
    private void viewCadastrarLocatario(ActionEvent event) {
        this.viewLocatarios.setVisible(false);
        this.viewEditarLocatario.setVisible(false);
        this.viewCadastrarLocatario.setVisible(true);
    }

    @FXML
    private void cadastrarLocatario(ActionEvent event) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDate hoje = LocalDate.now(zone);
        if (this.inpDataNasc.getValue() != null) {
            if (this.inpDataNasc.getValue().isAfter(hoje) || this.inpDataNasc.getValue().isEqual(hoje)) {
                this.alertError("Informe uma data válida");
                return;
            }
        }

        if (this.inpCPF.getText().isEmpty()
                || this.inpBanco.getText().isEmpty()
                || this.inpDataNasc == null
                || this.inpNome.getText().isEmpty()
                || this.inpEstadoCivil.getSelectionModel().getSelectedItem() == null) {

            this.alertError("Preencha todos os campos!");

        } else {
            boolean flag_cpf = false;

            for (Locatario l : this.locatarioDados.getAll()) {
                if (l.getCpf().equals(this.inpCPF.getText())) {
                    flag_cpf = true;
                }
            }

            if (flag_cpf == true) {
                this.alertError("CPF já cadastrado no sistema");
            } else {
                try {
                    this.alertConfirmation("Deseja cadastrar um novo locatário?", event, "cadastrar");
                } catch (IOException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void insertBanco(ActionEvent event) {
        Locatario objLocatario = new Locatario();
        objLocatario.setCpf(this.inpCPF.getText());
        objLocatario.setNome(this.inpNome.getText());
        objLocatario.setContaBancaria(this.inpBanco.getText());
        objLocatario.setDataNascimento(Date.from(this.inpDataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        objLocatario.setEstadoCivil(this.inpEstadoCivil.getValue());

        try {
            this.locatarioDados.add(objLocatario);
            this.listaObs.clear();
            this.listaObs.addAll(this.locatarioDados.getAll());

            super.telaLocatario(event);

            this.alertInformation("Locatário inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao inserir locatário: " + ex.getMessage());
        }
    }

    @FXML
    private void updateLocatario(ActionEvent event) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDate hoje = LocalDate.now(zone);
        if (this.upDataNasc.getValue() != null) {
            if (this.upDataNasc.getValue().isAfter(hoje) || this.upDataNasc.getValue().isEqual(hoje)) {
                this.alertError("Informe uma data válida");
                return;
            }
        }

        if (this.upCPF.getText().isEmpty()
                || this.upBanco.getText().isEmpty()
                || this.upDataNasc == null
                || this.upNome.getText().isEmpty()
                || this.upEstadoCivil.getSelectionModel().getSelectedItem() == null) {

            this.alertError("Preencha todos os campos!");

        } else {
            boolean flag_cpf = false;

            for (Locatario l : this.locatarioDados.getAll()) {
                if (l.getCpf().equals(this.upCPF.getText()) && !Objects.equals(l.getIdLocatario(), Integer.valueOf(this.upIdLocatario.getText()))) {
                    flag_cpf = true;
                }
            }

            if (flag_cpf == true) {
                this.alertError("CPF já cadastrado no sistema");
            } else {
                try {
                    this.alertConfirmation("Deseja atualizar o locatário de id " + this.upIdLocatario.getText() +"?", event, "update");
                } catch (IOException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateBanco(ActionEvent event) {
        Locatario objLocatario = new Locatario();
        objLocatario.setIdLocatario(Integer.valueOf(this.upIdLocatario.getText()));
        objLocatario.setCpf(this.upCPF.getText());
        objLocatario.setNome(this.upNome.getText());
        objLocatario.setContaBancaria(this.upBanco.getText());
        objLocatario.setDataNascimento(Date.from(this.upDataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        objLocatario.setEstadoCivil(this.upEstadoCivil.getValue());
        System.out.println(objLocatario);

        try {
            this.locatarioDados.edit(objLocatario);
            this.listaObs.clear();
            this.listaObs.addAll(this.locatarioDados.getAll());
            
            super.telaLocatario(event);

            this.alertInformation("Locatário editado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao editar locatário: " + ex.getMessage());
        }
    }

    @FXML
    private void viewLocatario(ActionEvent event) {
        this.viewLocatarios.setVisible(true);
        this.viewEditarLocatario.setVisible(false);
        this.viewCadastrarLocatario.setVisible(false);
    }

}
