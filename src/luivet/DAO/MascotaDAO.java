/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luivet.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import luivet.connection.Conexion;

/**
 *
 * @author 1
 */
public class MascotaDAO {
    private String id;
    private String nombre;
    private String ownerId;
    private String foto;
    private String color;
    private String deceso;
    private String decesoCausa;
    private String raza;
    private String sexo;
    private String alimentacion;
    private String alimentacionFrecuencia;
    private String ambiente;
    private String medicadoObservacion;
    private String chip;
    private String esterilizado;
    private String birthdayDate;
    private String especie;
    
    public MascotaDAO(){
        
    }
    
    public static Set<MascotaDAO> getMascotasByOwnerId(String id){
        Set<MascotaDAO> mascotasEncontradas = new HashSet<MascotaDAO>();
        String query = "SELECT * FROM evet_mascota WHERE owner_id = '"+id+"'";
        ResultSet resultados = Conexion.getInstance().query(query);
        
        try {    
            while(resultados.next()){
                MascotaDAO m = new MascotaDAO();
                m.setId(resultados.getString("id"));
                m.setNombre(resultados.getString("nombre_texto"));
                m.setOwnerId(resultados.getString("owner_id"));
                m.setFoto(resultados.getString("foto"));
                m.setColor(resultados.getString("color_texto"));
                m.setDeceso(resultados.getString("deceso_date"));
                m.setRaza(resultados.getString("raza_texto"));
                m.setSexo(resultados.getString("sexo_texto"));
                m.setAlimentacion(resultados.getString("alimentacion"));
                m.setAlimentacionFrecuencia(resultados.getString("alimentacion_frecuencia"));
                m.setAmbiente(resultados.getString("ambiente"));
                m.setDecesoCausa(resultados.getString("causa_deceso"));
                m.setMedicadoObservacion(resultados.getString("medicado_observacion"));
                m.setChip(resultados.getString("chip"));
                m.setEsterilizado(resultados.getString("esterilizado"));
                m.setBirthdayDate(resultados.getString("birthday_date"));
                mascotasEncontradas.add(m);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mascotasEncontradas;
    }
    
    
    public String getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getOwnerId(){
        return ownerId;
    }
    public String getFoto(){
        return foto;
    }
    public String getColor(){
        return color;
    }
    public String getDeceso(){
        return deceso;
    }
    public String getDecesoCausa(){
        return decesoCausa;
    }
    public String getRaza(){
        return raza;
    }
    public String getSexo(){
        return sexo;
    }
    public String getAlimentacion(){
        return alimentacion;
    }
    public String getAlimentacionFrecuencia(){
        return alimentacionFrecuencia;
    }
    public String getAmbiente(){
        return ambiente;
    }
    public String getMedicadoObservacion(){
        return medicadoObservacion;
    }
    public String getChip(){
        return chip;
    }
    public String getEsterilizado(){
        return esterilizado;
    }
    public String getBirthdayDate(){
        return birthdayDate;
    }
    public String getEspecie(){
        return especie;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param deceso the deceso to set
     */
    public void setDeceso(String deceso) {
        this.deceso = deceso;
    }

    /**
     * @param decesoCausa the decesoCausa to set
     */
    public void setDecesoCausa(String decesoCausa) {
        this.decesoCausa = decesoCausa;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @param alimentacion the alimentacion to set
     */
    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    /**
     * @param alimentacionFrecuencia the alimentacionFrecuencia to set
     */
    public void setAlimentacionFrecuencia(String alimentacionFrecuencia) {
        this.alimentacionFrecuencia = alimentacionFrecuencia;
    }

    /**
     * @param ambiente the ambiente to set
     */
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * @param medicadoObservacion the medicadoObservacion to set
     */
    public void setMedicadoObservacion(String medicadoObservacion) {
        this.medicadoObservacion = medicadoObservacion;
    }

    /**
     * @param chip the chip to set
     */
    public void setChip(String chip) {
        this.chip = chip;
    }

    /**
     * @param esterilizado the esterilizado to set
     */
    public void setEsterilizado(String esterilizado) {
        this.esterilizado = esterilizado;
    }

    /**
     * @param birthdayDate the birthdayDate to set
     */
    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
}
