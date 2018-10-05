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
                m.id = resultados.getString("id");
                m.nombre = resultados.getString("nombre_texto");
                m.ownerId =  resultados.getString("owner_id");
                m.foto = resultados.getString("foto");
                m.color = resultados.getString("color_texto");
                m.deceso = resultados.getString("deceso_date");
                m.raza = resultados.getString("raza_texto");
                m.sexo = resultados.getString("sexo_texto");
                m.alimentacion = resultados.getString("alimentacion");
                m.alimentacionFrecuencia = resultados.getString("alimentacion_frecuencia");
                m.ambiente = resultados.getString("ambiente");
                m.decesoCausa = resultados.getString("causa_deceso");
                m.medicadoObservacion = resultados.getString("medicado_observacion");
                m.chip = resultados.getString("chip");
                m.esterilizado = resultados.getString("esterilizado");
                m.birthdayDate = resultados.getString("birthday_date");
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
}
