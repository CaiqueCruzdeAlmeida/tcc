package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * 
 * Classe Conexao usando o padrão Singleton
 * 
 * @author Leonardo Antonio Araujo
 */

public class Conexao {
    
    private static Connection conexao;

//    static PreparedStatement prepareStatement(String query) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    private Conexao() throws Exception{
        
        try{
            
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/tc";
            String usuario = "root";
            String senha = "";
            
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.setAutoCommit(true);
            
        }catch (Exception e){
            throw e;
        }
        
    }
    
    public static Connection getConexao() throws Exception {
        if (conexao == null || conexao.isClosed()) {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://127.0.0.1:3306/tc";
                String usuario = "root";
                String senha = "";
                
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usuario, senha);
                conexao.setAutoCommit(true);
            } catch (Exception e) {
                throw e;
            }
        }
        return conexao;
    }
    public static void closeConexao() throws Exception {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(Conexao.getConexao());
    }
}
