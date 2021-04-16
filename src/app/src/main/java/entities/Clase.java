package entities;

public class Clase{
    private Carrera carrera;
    private int idClase;
    private String nClase;

    public Clase(){}
    public Clase(int idClase,String nClase,Carrera carrera){
        this.idClase = idClase;
        this.nClase = nClase;
        this.carrera = carrera;
    }

    public Carrera getCarrera(){
        return this.carrera;
    }

    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getnClase() {
        return nClase;
    }

    public void setnClase(String nClase) {
        this.nClase = nClase;
    }
}