package luivet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author gastonestevez
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("ui/login/FXMLDocument.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("ui/mainmenu/MainMenuDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/luivet/ui/res/sinimagen.png"));
        stage.setTitle("Luivet - Gebo 2018");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
