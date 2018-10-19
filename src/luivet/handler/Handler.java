/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.handler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import luivet.DAO.ClienteDAO;
import luivet.DAO.MascotaDAO;

/**
 *
 * @author 1
 */
public class Handler{ 
    private static Handler handler;

    private ClienteDAO modCliente;
    private MascotaDAO modMascota;
    
    private Handler(){
        
    }

    public static Handler getInstance(){
        if(handler==null){
            handler = new Handler();
            return handler;
        }else{
            return handler;
        }
    }

    public FXMLLoader intercambiarInterfaz(String direccionFXML, String titulo, boolean cierraVentanaActual, ActionEvent event){
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/luivet/ui/"+ direccionFXML +".fxml"));
           
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
            stage.setOnCloseRequest((WindowEvent we) -> {
                handler.setModCliente(null);
            });
            
            if(cierraVentanaActual){
                //Cierra ventana actual..
                final Node source = (Node) event.getSource();
                final Stage stageActual = (Stage) source.getScene().getWindow();
                stageActual.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fxmlLoader;
    }

    public Set<ClienteDAO> buscarCliente(String text) {
        return ClienteDAO.select(text);
    }   
    public Set<MascotaDAO> buscarMascota(String ownerId){
        return MascotaDAO.getMascotasByOwnerId(ownerId);
    }
    
    public void setModCliente(ClienteDAO cliente){
        modCliente = cliente;
    }
    public ClienteDAO getModCliente(){
        return modCliente;
    }
    public void setModMascota(MascotaDAO mascota){
        modMascota = mascota;
    }
    public MascotaDAO getModMascota(){
        return modMascota;
    }
    
    public LocalDate pasarStrADate(String fechastr){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(!fechastr.isEmpty()){
            LocalDate date = LocalDate.parse(fechastr,formato);
            return date;
        }else{
            return null;
        }
    } 
    public void guardarCliente(ClienteDAO clienteMod, boolean clienteNuevo) {
        System.out.println("handler -> "+clienteMod.toString());
        ClienteDAO.insertarCliente(clienteMod,clienteNuevo);
    }

    public void enviarAlerta(String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION,msg, ButtonType.OK);
        alert.showAndWait();
    }

    public void removeCliente(ClienteDAO cliente) {
        ClienteDAO.removerCliente(cliente);
    }

}
