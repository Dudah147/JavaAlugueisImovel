/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logico;

import DAO.ImovelDAO;
import DAO.TipoimovelDAO;
import database.DataBaseJDBC;
import entity.Imovel;
import entity.Tipoimovel;
import java.sql.SQLException;

/**
 *
 * @author dudam
 */
public class Main{
    
    public static void main(String[] args) {
//        CRIAÇÃO DE DATABASE E TABELAS
//        try {
//            DataBaseJDBC banco = new DataBaseJDBC();
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
        
        
        Tipoimovel timovel1 = new Tipoimovel(1,"Comercial");
        
        

        try {
            TipoimovelDAO tImovel = new TipoimovelDAO();
            tImovel.add(timovel1);
        } catch (Exception ex) {
            System.out.println("Erro ao inserir tipo imovel: " + ex.getMessage());
        }
        

//        Imovel imovel1 = new Imovel(2, "Rua Olegário Mariano", 240, 2, 1, "Semi mobiliado com garagem para carro", 1000.5, "imovel.png");
//        imovel1.setIdTipoImovel(timovel1);
//        System.out.println(imovel1.getIdTipoImovel());
//        
//        try {
//            ImovelDAO tImovel = new ImovelDAO();
//            tImovel.add(imovel1);
//        } catch (Exception ex) {
//            System.out.println("Erro ao inserir tipo imovel: " + ex.getMessage());
//        }
    }
}
