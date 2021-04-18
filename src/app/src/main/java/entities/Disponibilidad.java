package entities;

import java.util.Calendar;

class Disponibilidad {
    private int id;
    private char dia;
    private Calendar hora;
    public Disponibilidad(int id,char dia, Calendar hora){
        this.id = id;
        this.dia = dia;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getDia() {
        return dia;
    }

    public void setDia(char dia) {
        this.dia = dia;
    }

    public Calendar getHora() {
        return hora;
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }
}