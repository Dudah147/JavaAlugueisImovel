/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dudam
 */
public class File {
     private String nomeArq;
    private BufferedReader arqLeitura;
    private BufferedWriter arqSaida;
    
    public File(String nomeArq) {
        this.nomeArq = nomeArq;
    }
    
    public  void openArqLeitura() throws FileNotFoundException{
        FileReader file = new FileReader(this.nomeArq);
        this.arqLeitura = new BufferedReader(file);
    }
    
    public ArrayList<String> readFile(){
        String sql = "";
        String line;
        ArrayList<String> listSql = new ArrayList();
        
        if(this.arqLeitura == null){
            try {
                this.openArqLeitura();
 
            } catch (FileNotFoundException ex) {
                System.out.println("Arquivo " + this.nomeArq + " não encontrado");
            }
        }

        
        try {
            while( (line = this.arqLeitura.readLine())!= null ){
                sql += line + "\n";
                
                if(sql.contains(";")){
                    listSql.add(sql);
                    sql = "";
                }
            }
            
            this.arqLeitura.close();
        } catch (IOException ex) {
            System.out.println("Erro ao ler arquivo");
        }
        
        return listSql;
    }
}
