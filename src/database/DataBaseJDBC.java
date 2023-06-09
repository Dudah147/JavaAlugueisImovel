/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logico.File;
/**
 *
 * @author dudam
 */
public class DataBaseJDBC {
     private String driver;
    private String banco;
    private String user;
    private String senha;
    private String url;
    private Connection con;
    private boolean flagDB = false;
    
    public DataBaseJDBC(String driver, String banco, String user,
            String senha, String url) throws ClassNotFoundException {
        this.driver = driver;
        this.banco = banco;
        this.user = user;
        this.senha = senha;
        this.url = url;
        Class.forName(driver); // carregando o driver do banco
    }
    
    public DataBaseJDBC() throws ClassNotFoundException {
        this("com.mysql.cj.jdbc.Driver",
                "LPOO", "root", "",
                "jdbc:mysql://localhost/");
    }
    
    public void createDB() throws SQLException {
        this.setCon();
        Statement sessao = this.con.createStatement();
        String sql = "CREATE DATABASE " + this.banco;
        try {
            sessao.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException("erro na criação do banco\n" + sql);
        }
        
        //realiza a conexão ao database criado
        this.setConDatabase();
        
    }
    
    public void setConDatabase(){
        try {
            this.con = DriverManager.getConnection(this.url + this.banco, this.user,
                    this.senha);
            this.flagDB = true;
        } catch (SQLException ex) {
            System.out.println("Falha ao se conectar ao database");
        }
    }
    
    public void createTable(ArrayList<String> listSql) throws SQLException{
        //flag verifica se a conexão está no database
        if(this.flagDB == false){
            this.setConDatabase();
        }
        
        for(String sql : listSql){
            System.out.println(sql);
            
            Statement sessao = this.con.createStatement();
            int rs;
            try {
                rs = sessao.executeUpdate(sql);
                System.out.println("Cadastrado! " + rs);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
    
    public Connection getCon(){
        return this.con;
    }
    
    public void setCon() throws SQLException {
        this.con = DriverManager.getConnection(this.url, this.user,
                this.senha);
    }
    
    public ResultSet consulta(String tabela, String where) throws SQLException{
        if(this.flagDB == false){
            this.setConDatabase();
        }
        
        String sql = "Select * from " + tabela + " where " + where;
                
        Statement sessao = this.con.createStatement();
        
        ResultSet dados = sessao.executeQuery(sql);
        
        return dados;
    }
}
