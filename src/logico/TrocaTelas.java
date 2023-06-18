/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logico;

import controllers.ImovelController;
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
    public void telaImoveis (ActionEvent event) { 
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Imovel.fxml"));
            Parent root = loader.load();
            String css = this.getClass().getResource("/view/geral.css").toExternalForm();
            
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(css);
            this.stage.setScene(this.scene);
            
            ImovelController controller = loader.getController();
            controller.setController(controller);
            
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void telaLocatario(ActionEvent event) { 
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/Locatario.fxml"));
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
    public void telaRelatorio(ActionEvent event) { 
        try{
            Parent r = FXMLLoader.load(getClass().getResource("/view/Relatorio.fxml"));
            String css = this.getClass().getResource("/view/geral.css").toExternalForm();
            
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();  
            this.scene = new Scene(r);
            this.scene.getStylesheets().add(css);
            this.stage.setScene(this.scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    
}
