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
public class ClienteDAO implements Comparable<ClienteDAO>{

    public static void removerCliente(ClienteDAO cliente) {
        Conexion.getInstance().delete(new Integer(cliente.getId()), "evet_cliente");
    }

    private String nombre;
    private String direccion;
    private String id;
    private String mail;
    private String celular;
    private String telefono;
    private String codigoPostal;
    private String fechaDeInscripcion;
    private Conexion conexion;
    
    public ClienteDAO(){
        this.id = "";
        this.nombre = "";
        this.fechaDeInscripcion = "";
        this.direccion = "";
        this.mail = "";
        this.celular = "";
        this.telefono = "";
        this.codigoPostal = "";
        this.conexion = Conexion.getInstance();
    }
    
    public static void insertarCliente(ClienteDAO clienteMod, boolean clienteNuevo){
        if(clienteNuevo){
            String nuevoId = Integer.toString(crearNuevoId());
            clienteMod.setId(nuevoId);
            String sentencia = "INSERT INTO evet_cliente (id,nombre_texto,inscripcion_date,celular_texto,codigopostal_texto,direccion_texto,tel_texto,mail_texto) "+
                " VALUES ('"+clienteMod.getId()+"','"+
                        clienteMod.getNombre()+"','"+
                        clienteMod.getFechaDeInscripcion()+"','"+
                        clienteMod.getCelular()+"','"+
                        clienteMod.getCodigoPostal()+"','"+
                        clienteMod.getDireccion()+"','"+
                        clienteMod.getTelefono()+"','"+
                        clienteMod.getMail()+"');";
            System.out.println(sentencia);
            Conexion.getInstance().insertData(sentencia);
        }else{
            
            String sentencia = "UPDATE evet_cliente "
                    + "SET nombre_texto = '" + clienteMod.getNombre() +
                    "', inscripcion_date = '" + clienteMod.getFechaDeInscripcion() +
                    "', celular_texto = '" + clienteMod.getCelular() +
                    "', codigopostal_texto = '" + clienteMod.getCodigoPostal() +
                    "', direccion_texto = '" + clienteMod.getDireccion() +
                    "', tel_texto = '" + clienteMod.getTelefono() + 
                    "' WHERE id = '" + clienteMod.getId() + "';" ;
            System.out.println(sentencia);
            Conexion.getInstance().modificarFila(sentencia);
        }
    }
    private static int crearNuevoId() {
        int numeroDeId = 0;
        try {
            String query = "SELECT * FROM evet_cliente\n" +
                    "WHERE ID = (\n" +
                    " SELECT MAX(ID) FROM evet_cliente)";
            ResultSet resultado = Conexion.getInstance().query(query);
            while(resultado.next()){
                numeroDeId = new Integer(resultado.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroDeId+1;
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
                    ClienteDAO cliente = new ClienteDAO();
                    cliente.setNombre(resultados.getString("nombre_texto"));
                    cliente.setFechaDeInscripcion(resultados.getString("inscripcion_date"));
                    cliente.setCelular(resultados.getString("celular_texto"));
                    cliente.setDireccion(resultados.getString("direccion_texto"));
                    cliente.setMail(resultados.getString("mail_texto"));
                    cliente.setTelefono(resultados.getString("tel_texto"));
                    cliente.setCodigoPostal(resultados.getString("codigopostal_texto"));
                    cliente.setId(resultados.getString("id"));
                    System.out.println(cliente);
                    clientesEncontrados.add(cliente);
                }
                    } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return clientesEncontrados;
    }

    @Override
    public String toString() {
        return "ClienteDAO{" + "nombre=" + getNombre() + ", direccion=" + direccion + ", id=" + id + ", mail=" + mail + ", celular=" + celular + ", telefono=" + telefono + ", codigoPostal=" + codigoPostal + ", fechaDeInscripcion=" + fechaDeInscripcion + '}';
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

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @param fechaDeInscripcion the fechaDeInscripcion to set
     */
    public void setFechaDeInscripcion(String fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    @Override
    public int compareTo(ClienteDAO o) {
        return this.nombre.compareTo(o.nombre);
    }
}
