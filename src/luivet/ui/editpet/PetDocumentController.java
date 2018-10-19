/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.ui.editpet;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import luivet.DAO.ClienteDAO;
import luivet.DAO.MascotaDAO;
import luivet.handler.Handler;

/**
 * FXML Controller class
 *
 * @author 1
 */
public class PetDocumentController implements Initializable {
    // MARK: Properties
    private MascotaDAO mascotaMod;
    private Handler handler;
    private ObservableList<MascotaDAO> dataMascota;
    @FXML
    private Label tituloLabel;
    @FXML
    private JFXTextField nombreField;
    @FXML
    private JFXDatePicker nacimientoDate;
    @FXML
    private JFXTextField chipField;
    @FXML
    private JFXTextField especieField;
    @FXML
    private JFXTextField razaField;
    @FXML
    private JFXTextField colorField;
    @FXML
    private JFXTextField causaField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox sexoCombo;
    @FXML
    private JFXCheckBox esterilizadoCheck;
    @FXML
    private JFXDatePicker decesoDate;
    @FXML
    private ComboBox ambienteCombo;
    @FXML
    private ComboBox alimentacionCombo;
    @FXML
    private JFXTextField frecuenciaField;
    @FXML
    private JFXTextField observacionesField;
    @FXML
    private Button fotoButton;
    @FXML
    private ImageView fotoImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = Handler.getInstance();
        sexoCombo.setItems(FXCollections.observableArrayList("Macho","Hembra"));
        if(handler.getModMascota() != null ){
            tituloLabel.setText("Modificar mascota");
            mascotaMod = handler.getModMascota();
            loadComponentesParaModificar();
        }else{
            tituloLabel.setText("Agregar mascota");
        }
    }    
    
    //MARK: Private methods
    
    public void setObservablePetData(ObservableList<MascotaDAO> listadoObservableDeMascotas) {
        dataMascota = listadoObservableDeMascotas;
    }

    private void loadComponentesParaModificar() {
        nombreField.setText(mascotaMod.getNombre());
        nacimientoDate.setValue(handler.pasarStrADate(mascotaMod.getBirthdayDate()));
        chipField.setText(mascotaMod.getChip());
        especieField.setText(mascotaMod.getEspecie());
        razaField.setText(mascotaMod.getRaza());
        causaField.setText(mascotaMod.getDecesoCausa());
        colorField.setText(mascotaMod.getColor());
        sexoCombo.getSelectionModel().select(mascotaMod.getSexo());
        esterilizadoCheck.setSelected(mascotaMod.getEsterilizado().equals("1"));
        decesoDate.setValue(handler.pasarStrADate(mascotaMod.getDeceso()));
        ambienteCombo.getSelectionModel().select(mascotaMod.getAmbiente());
        alimentacionCombo.getSelectionModel().select(mascotaMod.getAlimentacion());
        frecuenciaField.setText(mascotaMod.getAlimentacionFrecuencia());
        observacionesField.setText(mascotaMod.getMedicadoObservacion());
    }
    
    private void cerrarVentana(ActionEvent event){
        handler.setModMascota(null);
        final Node source = (Node) event.getSource();
        final Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
    
    // MARK: Actions
    @FXML 
    private void seleccionarFoto(ActionEvent event){
        Node source = (Node) event.getSource();
        Window stage = source.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Cargar foto");
        File r = fc.showOpenDialog(stage);
        if (r!=null){
            
        }
        
    }
    
    @FXML
    private void confirmarAccion(ActionEvent event){
        boolean esMascotaNueva = handler.getModMascota() == null;
        if(!nombreField.getText().isEmpty()){
            if(esMascotaNueva){
                mascotaMod = new MascotaDAO();
            }else{
                removerMascotaSeleccionada();
            }
            mascotaMod.setNombre(nombreField.getText());
            mascotaMod.setBirthdayDate(nacimientoDate.getValue().toString());
            mascotaMod.setAlimentacion(alimentacionCombo.getSelectionModel().getSelectedItem().toString());
            mascotaMod.setAlimentacionFrecuencia(frecuenciaField.getText());
            mascotaMod.setAmbiente(ambienteCombo.getSelectionModel().getSelectedItem().toString());
            mascotaMod.setChip(chipField.getText());
            mascotaMod.setColor(colorField.getText());
            mascotaMod.setDeceso(decesoDate.getValue().toString());
            mascotaMod.setDecesoCausa(causaField.getText());
            mascotaMod.setEspecie(especieField.getText());
            mascotaMod.setEsterilizado(esterilizadoCheck.getText());
            mascotaMod.setMedicadoObservacion(observacionesField.getText());
            mascotaMod.setRaza(razaField.getText());
            dataMascota.add(mascotaMod);
            cerrarVentana(event);
        } else{
            handler.enviarAlerta("Nombre obligatorio");
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event){
        cerrarVentana(event);
    }
    
    private void removerMascotaSeleccionada(){
        Iterator<MascotaDAO> it = dataMascota.iterator();
        while(it.hasNext()){
            MascotaDAO m = it.next();
            if (m.getId().equals(mascotaMod.getId())){
                it.remove();
            }
        }
    }
}
