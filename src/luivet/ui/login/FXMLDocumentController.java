/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author gastonestevez
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uName = userName.getText();
        String uPwd = DigestUtils.shaHex(password.getText());
    }
    
    @FXML
    private void handleQuitButton(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
