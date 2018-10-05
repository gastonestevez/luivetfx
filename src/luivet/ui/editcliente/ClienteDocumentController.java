/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.editcliente;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXTextField dateField;
    @FXML
    private JFXTextField telField;
    @FXML
    private JFXTextField celularField;
    @FXML
    private JFXTextField domicilioField;
    @FXML
    private JFXTextField postalField;
    @FXML
    private JFXTextField mailField;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Handler.getInstance().getModCliente()!=null){
            clienteMod = Handler.getInstance().getModCliente();
            loadComponentesParaModificar();
        }
    }

    private void loadComponentesParaModificar() {
        nombreField.setText(clienteMod.getNombre());
        dateField.setText(clienteMod.getFechaDeInscripcion());
        telField.setText(clienteMod.getTelefono());
        celularField.setText(clienteMod.getCelular());
        domicilioField.setText(clienteMod.getDireccion());
        postalField.setText(clienteMod.getCodigoPostal());
        mailField.setText(clienteMod.getMail());
    }
    
    @FXML
    private void okButtonHandle(ActionEvent event){
    
    }
    
    @FXML
    private void cancelButtonHandle(ActionEvent event){
        
    }
    
}
