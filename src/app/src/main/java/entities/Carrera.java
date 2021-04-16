package entities;

public class Carrera{
    private int idCarrera;
    private String nCarrera;

    public Carrera(int idCarrera,String nCarrera){
        this.idCarrera = idCarrera;
        this.nCarrera = nCarrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getnCarrera() {
        return nCarrera;
    }

    public void setnCarrera(String nCarrera) {
        this.nCarrera = nCarrera;
    }
}