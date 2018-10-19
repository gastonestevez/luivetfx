/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.connection;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1
 */
public class Conexion {
    private Connection conexion1;
    private static Conexion con;
    
    public static Conexion getInstance(){
        if(con == null){
            con = new Conexion();
            return con;
        }else{
            return con;
        }
    }
    private Conexion(){
        try {
            String url = "jdbc:sqlite:db.sqlite3";
            conexion1 = DriverManager.getConnection(url);
            System.out.println("-> Conectado :D ");
            Statement statement = conexion1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM evet_cliente WHERE nombre_texto LIKE '%as%' OR celular_texto LIKE '%as%' OR direccion_texto LIKE '%as%' OR tel_texto LIKE '%as%'");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                if(conexion1 != null){
                    conexion1.close();
                }
            }catch(SQLException e2){
                System.out.println(e2.getMessage());
            }
        }                
    }
    public void delete(int id, String tabla){
        try {
            String sql = "DELETE FROM " + tabla + " WHERE id = ?;";
            String url = "jdbc:sqlite:db.sqlite3";
            conexion1 = DriverManager.getConnection(url);
            PreparedStatement pstmt = conexion1.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ResultSet query(String consulta) {
        ResultSet resultado = null;
        try {
            String url = "jdbc:sqlite:db.sqlite3";
            conexion1 = DriverManager.getConnection(url);
            Statement statement = conexion1.createStatement();
            resultado = statement.executeQuery(consulta);
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return resultado;
    }
    
    public void insertData(String sentence){
        ResultSet resultado = null;
        try {
            String url = "jdbc:sqlite:db.sqlite3";
            conexion1 = DriverManager.getConnection(url);
            Statement statement = conexion1.createStatement();
            statement.executeUpdate(sentence);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    public void modificarFila(String sentencia) {
        try {
            String url = "jdbc:sqlite:db.sqlite3";
            conexion1 = DriverManager.getConnection(url);
            PreparedStatement statement = conexion1.prepareStatement(sentencia);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
