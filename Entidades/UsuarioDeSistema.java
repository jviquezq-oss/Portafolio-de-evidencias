package Entidades;

public abstract class UsuarioDeSistema {
    protected String correoElctronico;
    protected String contrasena;
    protected String nombreCompleto;

    public UsuarioDeSistema(String correoElctronico, String contrasena, String nombreCompleto) {
        this.correoElctronico = correoElctronico;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElctronico() {
        return correoElctronico;
    }

    public void setCorreoElctronico(String correoElctronico) {
        this.correoElctronico = correoElctronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
