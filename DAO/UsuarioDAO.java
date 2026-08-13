package DAO;

import Entidades.UsuarioAdministrador;
import Entidades.UsuarioDeSistema;
import Entidades.UsuarioVentas;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public boolean registrar(UsuarioDeSistema usuario) {
        try {

            String tipoUsuario;

            if (usuario instanceof UsuarioAdministrador) {tipoUsuario = "ADMINISTRADOR";
            } else {
                tipoUsuario = "VENTAS";
            }

            String query = String.format(
                    "INSERT INTO USUARIO(nombre, apellidos, correo, contrasena, tipo_usuario) " +
                            "VALUES('%s','%s','%s','%s','%s')",
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getCorreoElectronico(),
                    usuario.getContrasena(),
                    tipoUsuario);

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public UsuarioDeSistema buscar(int idUsuario) {

        try {

            String query = "SELECT * FROM USUARIO WHERE id_usuario=?";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, idUsuario);

            if (!resultado.next()) {
                return null;
            }
            if (resultado.getString("tipo_usuario").equals("ADMINISTRADOR")) {
                return new UsuarioAdministrador(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getString("apellidos"),
                        resultado.getString("correo"),
                        resultado.getString("contrasena"));

            }

            return new UsuarioVentas(
                    resultado.getInt("id_usuario"),
                    resultado.getString("nombre"),
                    resultado.getString("apellidos"),
                    resultado.getString("correo"),
                    resultado.getString("contrasena"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<UsuarioDeSistema> listar() {
        ArrayList<UsuarioDeSistema> lista =
                new ArrayList<>();
        try {

            String query = "SELECT * FROM USUARIO ORDER BY nombre";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query);
            while (resultado.next()) {
                UsuarioDeSistema usuario;
                if (resultado.getString("tipo_usuario").equals("ADMINISTRADOR")) {
                    usuario =
                            new UsuarioAdministrador(
                                    resultado.getInt("id_usuario"),
                                    resultado.getString("nombre"),
                                    resultado.getString("apellidos"),
                                    resultado.getString("correo"),
                                    resultado.getString("contrasena"));

                } else {

                    usuario =
                            new UsuarioVentas(
                                    resultado.getInt("id_usuario"),
                                    resultado.getString("nombre"),
                                    resultado.getString("apellidos"),
                                    resultado.getString("correo"),
                                    resultado.getString("contrasena"));

                }

                lista.add(usuario);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;

    }

    public boolean modificar(UsuarioDeSistema usuario) {

        try {

            String tipoUsuario;

            if (usuario instanceof UsuarioAdministrador) {
                tipoUsuario = "ADMINISTRADOR";
            } else {
                tipoUsuario = "VENTAS";
            }

            String query = String.format(
                    "UPDATE USUARIO SET " +
                            "nombre='%s', " +
                            "apellidos='%s', " +
                            "correo='%s', " +
                            "contrasena='%s', " +
                            "tipo_usuario='%s' " +
                            "WHERE id_usuario=%d",
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getCorreoElectronico(),
                    usuario.getContrasena(),
                    tipoUsuario,
                    usuario.getIdUsuario());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean eliminar(int idUsuario) {

        try {

            String query = String.format("DELETE FROM USUARIO WHERE id_usuario=%d", idUsuario);
            Connector.getConnection().ejecutarStatement(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean existe(int idUsuario) {

        try {

            String query = "SELECT * FROM USUARIO WHERE id_usuario=?";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, idUsuario);
            return resultado.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public UsuarioDeSistema buscarPorCorreo(String correoElectronico) {
        try {

            String query = "SELECT * FROM USUARIO WHERE correo=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, correoElectronico);
            if (!resultado.next()) {
                return null;
            }
            if (resultado.getString("tipo_usuario")
                    .equals("ADMINISTRADOR")) {

                return new UsuarioAdministrador(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getString("apellidos"),
                        resultado.getString("correo"),
                        resultado.getString("contrasena"));

            }
            return new UsuarioVentas(
                    resultado.getInt("id_usuario"),
                    resultado.getString("nombre"),
                    resultado.getString("apellidos"),
                    resultado.getString("correo"),
                    resultado.getString("contrasena"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}