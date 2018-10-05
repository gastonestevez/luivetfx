/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.mainmenu;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import luivet.DAO.ClienteDAO;
import luivet.DAO.MascotaDAO;
import luivet.handler.Handler;

/**
 * FXML Controller class
 *
 * @author gastonestevez
 */
public class MainMenuDocumentController implements Initializable {
    private Handler handler;
    @FXML
    private JFXTextField searchClientField;
    @FXML
    private TableView clientTable;
    @FXML
    private TableView petTable;
    @FXML
    private ImageView petPicture;
    @FXML
    private Button modClientButton; 
    @FXML
    private Button modPetButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadClientTable();
        loadPetTable();
        System.out.println("cargo ui mainmenu");
        modClientButton.setDisable(true);
        modPetButton.setDisable(true);
        
        clientTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)-> {
        if(newSelection != null){
            ClienteDAO clienteSeleccionado = (ClienteDAO)clientTable.getSelectionModel().getSelectedItem();
            modClientButton.setDisable(false);
            buscarMascotas(clienteSeleccionado);
            //buscarTurnos(clienteSeleccionado);
        }else{
            modClientButton.setDisable(true);
        }});
        
        petTable.getSelectionModel().selectedItemProperty().addListener((obs2,oldSelection2,newSelection2)-> {
        if(newSelection2 != null){
            MascotaDAO mascotaSeleccionada = (MascotaDAO)petTable.getSelectionModel().getSelectedItem();
            //buscarVisitas(clienteSeleccionado);
            modPetButton.setDisable(false);
            //petPicture.setImage(new Image(mascotaSeleccionada.getFoto()));
        }else{
            modPetButton.setDisable(true);
        }});
    
    }
    
    private void loadClientsOnAction(ActionEvent event){
        System.out.println("Click!");
    }
    
    
    //CLIENT 
    @FXML
    private void searchClient(ActionEvent event){
        Set<ClienteDAO> conjuntoDeClientes = handler.getInstance().buscarCliente(searchClientField.getText());
        ObservableList<ClienteDAO> conjunto = FXCollections.observableArrayList(conjuntoDeClientes);
        clientTable.setItems(conjunto);
        petTable.setItems(null);
        
    }
    @FXML
    private void addClient(ActionEvent event){
       handler.getInstance().intercambiarInterfaz("editcliente/clienteDocument","Agregar cliente", false, event);
    }
    @FXML
    private void modClient(ActionEvent event){
       ClienteDAO selectedClient = (ClienteDAO) clientTable.getSelectionModel().getSelectedItem();
       handler.setModCliente(selectedClient); //TODO <<-- ERROR
       handler.getInstance().intercambiarInterfaz("editcliente/clienteDocument","Modificar cliente", false, event);
       
    }
    @FXML
    private void delClient(ActionEvent event){
        
    }
    
    //PET AREA
    @FXML
    private void modPet(ActionEvent event){
        
    }
    @FXML
    private void addPet(ActionEvent event){
        handler.getInstance().intercambiarInterfaz("editpet/petDocument","Agregar mascota", false, event);
    }
    @FXML
    private void delPet(ActionEvent event){
        
    }
    @FXML
    private void addWaitingList(ActionEvent event){
        
    }
    
    //TURN AREA
    @FXML
    private void addTurn(ActionEvent event){
        handler.getInstance().intercambiarInterfaz("editturno/TurnoDocument","Agregar turno", false, event);
    }
    @FXML
    private void modTurn(ActionEvent event){
        
    }
    @FXML
    private void delTurn(ActionEvent event){
        
    }
    
    //VISIT AREA
    @FXML
    private void addVisit(ActionEvent event){
        handler.getInstance().intercambiarInterfaz("editvisita/visitaDocument","Agregar visita", false, event);
    }
    @FXML
    private void modVisit(ActionEvent event){
        
    }
    @FXML
    private void delVisit(ActionEvent event){
        
    }
    
    //TURN TAB
    @FXML
    private void loadDateTurn(ActionEvent event){
        
    }
        
    //PRODUCT TAB
    @FXML
    private void loadSearchProduct(ActionEvent event){
        
    }
    @FXML
    private void loadAddProduct(ActionEvent event){
        handler.getInstance().intercambiarInterfaz("editproducto/productoDocument","Agregar producto", false, event);
    }
    @FXML
    private void loadDelProduct(ActionEvent event){
        
    }
    
    //WAITING TAB
    @FXML
    private void loadWaitVisit(ActionEvent event){
        
    }
    @FXML
    private void loadFinishWait(ActionEvent event){
        
    }

    private void loadClientTable() {
        TableColumn nombreCol = new TableColumn("Nombre");
        TableColumn direccionCol = new TableColumn("Direccion");
        TableColumn celularCol = new TableColumn("Celular");
        
        nombreCol.setCellValueFactory(
                new PropertyValueFactory<ClienteDAO,String>("nombre")
        );
        direccionCol.setCellValueFactory(
                new PropertyValueFactory<ClienteDAO,String>("direccion")
        );
        celularCol.setCellValueFactory(
                new PropertyValueFactory<ClienteDAO,String>("celular")
        );
        clientTable.getColumns().addAll(nombreCol,direccionCol,celularCol);
    }

    private void buscarMascotas(ClienteDAO clienteSeleccionado) {
        Set<MascotaDAO> mascotasEncontradas = handler.getInstance().buscarMascota(clienteSeleccionado.getId());
        ObservableList<MascotaDAO> conjunto = FXCollections.observableArrayList(mascotasEncontradas);
        petTable.setItems(conjunto);
    
    }

    private void buscarTurnos(ClienteDAO clienteSeleccionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buscarVisitas(ClienteDAO clienteSeleccionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadPetTable() {
        TableColumn nombreCol = new TableColumn("Nombre");
        TableColumn razaCol = new TableColumn("Raza");
        TableColumn especieCol =new TableColumn("Especie");
        TableColumn edadCol = new TableColumn("Edad");
        nombreCol.setCellValueFactory(new PropertyValueFactory<MascotaDAO,String>("nombre"));
        razaCol.setCellValueFactory(new PropertyValueFactory<MascotaDAO,String>("raza"));
        especieCol.setCellValueFactory(new PropertyValueFactory<MascotaDAO,String>("especie"));
        edadCol.setCellValueFactory(new PropertyValueFactory<MascotaDAO,String>("edad"));
        petTable.getColumns().addAll(nombreCol,razaCol,especieCol,edadCol);
    }
}
