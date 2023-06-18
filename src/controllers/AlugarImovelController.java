/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.ImovelDAO;
import DAO.LocacaoDAO;
import DAO.LocatarioDAO;
import dao.PagamentoDAO;
import entity.Imovel;
import entity.Locacao;
import entity.Locatario;
import entity.Pagamento;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
    
    private Imovel imovelTabela;    
    
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
    private ListView<Pagamento> inpFormaPgt;
    @FXML
    private Button bttnAlugar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        this.setDataComboBox();
        this.setDataListView();
        
        this.inpDataInicio.getEditor().setDisable(true);
        this.inpDataTermino.getEditor().setDisable(true);
        this.inpDataInicio.getEditor().setOpacity(1);
        this.inpDataTermino.getEditor().setOpacity(1);
        
        this.inpDataTermino.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }
    
    public void iniciarCampos(Imovel imovel){
        this.imovelTabela = imovel;
        
        this.inpValor.setText(String.valueOf(this.imovelTabela.getValorLocacao()));
        this.inpImovel.setText(this.imovelTabela.getEndereco());
    }
    
    public void setDataListView(){
        PagamentoDAO pgtData = new PagamentoDAO();
        
        ObservableList<Pagamento> listViewObs = FXCollections.observableArrayList(pgtData.getAll());
        this.inpFormaPgt.setItems(listViewObs);
    }
    
    public void setDataComboBox() {
        LocatarioDAO locatarioDados = new LocatarioDAO();
                
        ObservableList<Locatario> comboBoxList = FXCollections.observableArrayList(locatarioDados.getAll());
        
        this.inpLocatario.setItems(comboBoxList);
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
    
    private void insertBanco(ActionEvent event) {
        Locacao objLocacao = new Locacao();
        objLocacao.setDataInicio(Date.from(this.inpDataInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        objLocacao.setDataTermino(Date.from(this.inpDataTermino.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        objLocacao.setEncerrado(false);
        objLocacao.setIdPagamento(this.inpFormaPgt.getSelectionModel().getSelectedItem());
        objLocacao.setIdImovel(this.imovelTabela);
        objLocacao.setIdLocatario(this.inpLocatario.getValue());
        
        objLocacao.setValorDesconto(Double.parseDouble(this.inpDesconto.getText()));

        try {
            ImovelDAO imovel = new ImovelDAO();
            this.imovelTabela.setAlocado(true);
            imovel.edit(this.imovelTabela);
            
            LocacaoDAO locacaoDados = new LocacaoDAO();
            
            locacaoDados.add(objLocacao);

            super.telaImoveis(event);

            this.alertInformation("Imovel alugado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao inserir locacao: " + ex.getMessage());
        }
    }

    @FXML
    private void alugarImovel(ActionEvent event) {
        if(this.inpDesconto.getText().isEmpty()) {
            this.inpDesconto.setText(String.valueOf(0));
        }
        if (this.inpFormaPgt.getSelectionModel().getSelectedItem() == null
                || this.inpDataInicio == null
                || this.inpDataTermino == null
                || this.inpLocatario.getSelectionModel().getSelectedItem() == null) {

            this.alertError("Preencha todos os campos!");

        } else {
            if(Double.parseDouble(this.inpDesconto.getText()) > Double.parseDouble(this.inpValor.getText())){
                this.alertError("Desconto não pode ser maior que o valor do aluguel");
            }
            else{
                try {
                    this.alertConfirmation("Deseja alugar este imovel?", event, "cadastrar");
                } catch (IOException ex) {
                    Logger.getLogger(AlugarImovelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
}
