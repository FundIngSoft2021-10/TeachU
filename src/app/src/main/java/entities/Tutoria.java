package entities;

import java.util.Date;

public class Tutoria {
    private String idTutoria;
    private Date fecha;

    private int duracion;
    private long precio;

    public Tutoria() {
    }

    public Tutoria(String idTutoria, Date fecha, int duracion, long precio) {
        this.idTutoria = idTutoria;
        this.fecha = fecha;
        this.duracion = duracion;
        this.precio = precio;
    }

    public String getIdTutoria() {
        return idTutoria;
    }

    public void setIdTutoria(String idTutoria) {
        this.idTutoria = idTutoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
}