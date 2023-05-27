/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logico;

import dao.TipoImovelDAO;
import database.DB;
import entity.TipoImovel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicos.File;

/**
 *
 * @author a2319217
 */
public class MainTeste {
    public static void main(String[] args) {
        //CRIAÇÃO DE DATABASE E TABELAS
//        try {
//            DB banco = new DB();
//                
//            banco.createDB();
//            
//            File arq = new File("src\\database\\Tabelas.sql");
//            
//            banco.createTable(arq.readFile());
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Falha ao se conectar ao driver " + ex.getMessage());
//        } catch (SQLException ex) {
//            System.out.println("Falha na criação do DB " + ex.getMessage());
//        }

        TipoImovel imovel1 = new TipoImovel(1, "Residencial");
//        System.out.println(imovel1.getDescricao());
       
        try {
            TipoImovelDAO tImovel = new TipoImovelDAO();
            tImovel.add(imovel1);
        } catch (Exception ex) {
            System.out.println("Erro ao inserir tipo imovel: " + ex.getMessage());
        }
    }
}
