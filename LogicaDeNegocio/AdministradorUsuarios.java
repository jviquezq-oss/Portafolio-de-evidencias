package LogicaDeNegocio;

import DAO.UsuarioDAO;
import Entidades.UsuarioDeSistema;
import Excepciones.ExcepcionDeNegocio;

import java.util.ArrayList;

public class AdministradorUsuarios {

    private final UsuarioDAO usuarioDAO;

    public AdministradorUsuarios() {

        usuarioDAO = new UsuarioDAO();

    }

    public void registrarUsuario(UsuarioDeSistema usuario) throws ExcepcionDeNegocio {
        if (usuario == null) {
            throw new ExcepcionDeNegocio("El usuario no puede ser nulo.");
        }

        if (usuarioDAO.buscarPorCorreo(usuario.getCorreoElectronico()) != null) {
            throw new ExcepcionDeNegocio("Ya existe un usuario registrado con ese correo electrónico.");
        }

        if (!usuarioDAO.registrar(usuario)) {
            throw new ExcepcionDeNegocio("No fue posible registrar el usuario.");
        }

    }

    public ArrayList<UsuarioDeSistema> listarUsuarios() {

        return usuarioDAO.listar();

    }

    public UsuarioDeSistema buscarUsuario(int idUsuario) {

        return usuarioDAO.buscar(idUsuario);

    }

    public void modificarUsuario(UsuarioDeSistema usuario, String nombre, String apellidos, String correoElectronico, String contrasena) throws ExcepcionDeNegocio {
        if (usuario == null) {
            throw new ExcepcionDeNegocio("El usuario no existe.");
        }

        UsuarioDeSistema usuarioCorreo =
                usuarioDAO.buscarPorCorreo(correoElectronico);

        if (usuarioCorreo != null
                && usuarioCorreo.getIdUsuario() != usuario.getIdUsuario()) {

            throw new ExcepcionDeNegocio("Ya existe un usuario registrado con ese correo electrónico.");

        }

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContrasena(contrasena);

        if (!usuarioDAO.modificar(usuario)) {
            throw new ExcepcionDeNegocio("No fue posible modificar el usuario.");
        }

    }

    public void eliminarUsuario(UsuarioDeSistema usuario) throws ExcepcionDeNegocio {
        if (usuario == null) {
            throw new ExcepcionDeNegocio("El usuario no existe.");
        }

        if (!usuarioDAO.existe(usuario.getIdUsuario())) {
            throw new ExcepcionDeNegocio("El usuario no se encuentra registrado.");
        }

        if (!usuarioDAO.eliminar(usuario.getIdUsuario())) {
            throw new ExcepcionDeNegocio("No fue posible eliminar el usuario.");
        }

    }

    public UsuarioDeSistema autenticarUsuario(String correoElectronico, String contrasena) throws ExcepcionDeNegocio {

        UsuarioDeSistema usuario =
                usuarioDAO.buscarPorCorreo(correoElectronico);

        if (usuario == null) {
            throw new ExcepcionDeNegocio("Correo electrónico o contraseña incorrectos.");
        }

        if (!usuario.getContrasena().equals(contrasena)) {
            throw new ExcepcionDeNegocio("Correo electrónico o contraseña incorrectos.");
        }

        return usuario;

    }

}