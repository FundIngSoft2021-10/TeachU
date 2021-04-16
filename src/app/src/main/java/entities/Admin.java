package entities;

public class Admin extends Usuario{
    private String permisos;
    public Admin(){}
    public Admin(String permisos){
        this.permisos = permisos;
    }
    public String getPermisos() {
        return permisos;
    }
    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
}