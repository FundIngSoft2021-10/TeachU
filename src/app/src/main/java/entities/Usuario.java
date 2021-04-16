package entities;

public class Usuario {
    private String idUsuario;
    private String foto;
    private String nombre;
    private String apellido;
    private String correoInst;
    private int nUsuario;

    public Usuario() {}

    public Usuario(String idUsuario,String foto,String nombre,String apellido,String correoInst,int nUsuario){
        this.idUsuario = idUsuario;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoInst = correoInst;
        this.nUsuario = nUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreoInst() {
        return correoInst;
    }
    public void setCorreoInst(String correoInst) {
        this.correoInst = correoInst;
    }
    public int getnUsuario() {
        return nUsuario;
    }
    public void setnUsuario(int nUsuario) {
        this.nUsuario = nUsuario;
    }
}
