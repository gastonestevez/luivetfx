/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.editcliente;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import luivet.DAO.ClienteDAO;
import luivet.handler.Handler;

/**
 *
 * @author 1
 */
public class ClienteDocumentController implements Initializable{

    private ClienteDAO clienteMod;
    @FXML
    private JFXTextField nombreField;
    @FXML
    private JFXDatePicker dateClientField;
    @FXML
    private JFXTextField telefonoField;
    @FXML
    private JFXTextField celularField;
    @FXML
    private JFXTextField domicilioField;
    @FXML
    private JFXTextField postalField;
    @FXML
    private JFXTextField mailField;
    @FXML
    private Label tituloLabel;
    private Handler handler;
    private ObservableList<ClienteDAO> dataCliente;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = Handler.getInstance();
        if(Handler.getInstance().getModCliente()!=null){
            tituloLabel.setText("Modificar cliente");
            clienteMod = Handler.getInstance().getModCliente();
            System.out.println("ClientMod init -> " + clienteMod.toString());
            loadComponentesParaModificar();
         }else{
            tituloLabel.setText("Agregar cliente");
        }
    }

    private void loadComponentesParaModificar() {
        nombreField.setText(clienteMod.getNombre());
        dateClientField.setValue(Handler.getInstance().pasarStrADate(clienteMod.getFechaDeInscripcion()));
        telefonoField.setText(clienteMod.getTelefono());
        celularField.setText(clienteMod.getCelular());
        domicilioField.setText(clienteMod.getDireccion());
        postalField.setText(clienteMod.getCodigoPostal());
        mailField.setText(clienteMod.getMail());
    }
    
    @FXML
    private void okButtonHandle(ActionEvent event){
        boolean esClienteNuevo = handler.getModCliente() == null;
        if(!nombreField.getText().isEmpty() && dateClientField.getValue() != null){
            if(esClienteNuevo){
              clienteMod = new ClienteDAO();
            }else{
                removerClienteSeleccionado();
            }
            clienteMod.setNombre(nombreField.getText());
            clienteMod.setFechaDeInscripcion(dateClientField.getValue().toString());
            clienteMod.setCelular(celularField.getText());
            clienteMod.setCodigoPostal(postalField.getText());
            clienteMod.setDireccion(domicilioField.getText());
            clienteMod.setMail(mailField.getText());
            clienteMod.setTelefono(telefonoField.getText());
            handler.guardarCliente(clienteMod,esClienteNuevo);
            // TODO !! << COR
            this.dataCliente.add(clienteMod);
            
            cerrarVentana(event);
        } else {
            handler.enviarAlerta("Nombre y Fecha de inscripcion obligatorios");
        }
    }
    private void cerrarVentana(ActionEvent event){
        handler.setModCliente(null);
        final Node source = (Node) event.getSource();
        final Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
    
    @FXML
    private void cancelButtonHandle(ActionEvent event){
        cerrarVentana(event);
    }

    public void setObservableClientData(ObservableList<ClienteDAO> listadoObservableDeClientes) {
        this.dataCliente = listadoObservableDeClientes;
    }

    private void removerClienteSeleccionado() {
        Iterator<ClienteDAO> it = dataCliente.iterator();
        while(it.hasNext()){
            ClienteDAO cliente = it.next();
            if (cliente.getId().equals(clienteMod.getId())){
                it.remove();
            }
        }
    }
    
}
