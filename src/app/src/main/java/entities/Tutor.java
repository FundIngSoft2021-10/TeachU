package entities;

import java.util.ArrayList;

public class Tutor extends Usuario{
    private ArrayList<Disponibilidad> horarios;
    private String cedula;
    private String tipoDoc;
    private String description;

    public Tutor() {
        this.horarios = new ArrayList<Disponibilidad>();
    }

    public Tutor(String cedula, String tipoDoc, String description, ArrayList<Disponibilidad> horarios){
        this.cedula = cedula;
        this.tipoDoc = tipoDoc;
        this.description = description;
        this.horarios = horarios;
    }

    public ArrayList<Disponibilidad> getHorarios(){
        return horarios;
    }

    public void setHorarios(ArrayList<Disponibilidad> horarios){
        this.horarios = horarios;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}