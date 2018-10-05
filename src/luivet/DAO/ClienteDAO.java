/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import luivet.connection.Conexion;
import luivet.handler.Handler;

/**
 *
 * @author 1
 */
public class ClienteDAO {
    
    private String nombre;
    private String direccion;
    private String id;
    private String mail;
    private String celular;
    private String telefono;
    private String codigoPostal;
    private String fechaDeInscripcion;
    private Conexion conexion;
    
    public ClienteDAO(String nombre, String fecha){
        this.nombre = nombre;
        this.fechaDeInscripcion = fecha;
        this.direccion = "";
        this.mail = "";
        this.celular = "";
        this.telefono = "";
        this.codigoPostal = "";
        this.conexion = Conexion.getInstance();
    }
    
    public static Set<ClienteDAO> select(String query){
        Set<ClienteDAO> clientesEncontrados = new HashSet<ClienteDAO>();
        query = "'%"+query+"%'";
        query = "SELECT * "
                + "FROM evet_cliente "
                + "WHERE nombre_texto LIKE "+ query + " OR "
                + "celular_texto LIKE " + query + " OR "
                + "direccion_texto LIKE " + query + " OR "
                + "tel_texto LIKE " + query;
        ResultSet resultados = Conexion.getInstance().query(query);
        
            try {
                while(resultados.next()){
                    System.out.println(resultados.getString("nombre_texto"));
                    ClienteDAO cliente = new ClienteDAO(resultados.getString("nombre_texto"),resultados.getString("inscripcion_date"));
                    cliente.celular = resultados.getString("celular_texto");
                    cliente.direccion = resultados.getString("direccion_texto");
                    cliente.mail = resultados.getString("mail_texto");
                    cliente.telefono = resultados.getString("tel_texto");
                    cliente.codigoPostal = resultados.getString("codigopostal_texto");
                    cliente.id = resultados.getString("id");
                    clientesEncontrados.add(cliente);
                }
                    } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return clientesEncontrados;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public String getCelular(){
        return this.celular;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public String getCodigoPostal(){
        return this.codigoPostal;
    }
    public String getMail(){
        return this.mail;
    }
    public String getFechaDeInscripcion(){
        return this.fechaDeInscripcion;
    }
    public String getId(){
        return this.id;
    }
}
