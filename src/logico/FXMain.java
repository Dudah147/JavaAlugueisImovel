/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package logico;

import database.DataBaseJDBC;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dudam
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        String css = this.getClass().getResource("/view/geral.css").toExternalForm();
           
        Scene scene = new Scene(root); 
        scene.getStylesheets().add(css);


        primaryStage.setTitle("Venda de Imoveis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            DataBaseJDBC banco = new DataBaseJDBC();
//
////            banco.createDB();
//
//            File arq = new File("src\\database\\Tabelas.sql");
//
//            banco.createTable(arq.readFile());
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Falha ao se conectar ao driver " + ex.getMessage());
//        } catch (SQLException ex) {
//            System.out.println("Falha na criação do DB " + ex.getMessage());
//        }
//        
        
        launch(args);
    }

}
