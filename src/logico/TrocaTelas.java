/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logico;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dudam
 */
public class TrocaTelas {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void telaAlugarImovel(ActionEvent event) { 
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/AlugarImovel.fxml"));
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(root);
            this.stage.setScene(this.scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void telaImoveis (ActionEvent event) { 
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/Imovel.fxml"));
            String css = this.getClass().getResource("/view/geral.css").toExternalForm();
            
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(css);
            this.stage.setScene(this.scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void telaLocatario(ActionEvent event) { 
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/AlugarImovel.fxml"));
            String css = this.getClass().getResource("/view/geral.css").toExternalForm();
            
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(css);
            this.stage.setScene(this.scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void telaRelatorio(ActionEvent event) { 
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/AlugarImovel.fxml"));
            String css = this.getClass().getResource("/view/geral.css").toExternalForm();
            
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(css);
            this.stage.setScene(this.scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
