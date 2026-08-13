package Entidades;

public abstract class UsuarioDeSistema {

    protected int idUsuario;
    protected String nombre;
    protected String apellidos;
    protected String correoElectronico;
    protected String contrasena;

    public UsuarioDeSistema(String nombre, String apellidos, String correoElectronico, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;

    }

    public UsuarioDeSistema(int idUsuario, String nombre, String apellidos, String correoElectronico, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;

    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}