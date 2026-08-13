package Entidades;

public class UsuarioVentas extends UsuarioDeSistema {
    public UsuarioVentas(String nombre, String apellidos, String correoElectronico, String contrasena) {
        super(nombre, apellidos, correoElectronico, contrasena);
    }

    public UsuarioVentas(int idUsuario, String nombre, String apellidos, String correoElectronico, String contrasena) {
        super(idUsuario, nombre, apellidos, correoElectronico, contrasena);
    }

}