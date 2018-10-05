/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        // TODO algoritmo de login
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/luivet/ui/mainmenu/MainMenuDocument.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        //Cierra ventana actual..
        final Node source = (Node) event.getSource();
        final Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
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
