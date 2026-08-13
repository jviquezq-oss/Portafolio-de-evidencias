package Entidades;

public class UsuarioAdministrador extends UsuarioDeSistema {

    public UsuarioAdministrador(String nombre, String apellidos, String correoElectronico, String contrasena) {
        super(nombre, apellidos, correoElectronico,contrasena);
    }

    public UsuarioAdministrador(int idUsuario, String nombre, String apellidos, String correoElectronico, String contrasena) {
        super(idUsuario, nombre, apellidos, correoElectronico, contrasena);
    }

}