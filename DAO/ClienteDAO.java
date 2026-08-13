package DAO;

import Entidades.Cliente;
import Entidades.Nacionalidad;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {

    public boolean registrar(Cliente cliente) {

        try {

            String query = String.format(
                    "INSERT INTO CLIENTE(identificacion, nombre, apellidos, correo, telefono, fecha_nacimiento, nacionalidad) " +
                            "VALUES('%s','%s %s','%s','%s','%s','%s','%s')",
                    cliente.getIdentificacion(),
                    cliente.getNombre(),
                    cliente.getPrimerApellido(),
                    cliente.getSegundoApellido(),
                    cliente.getCorreoElectronico(),
                    cliente.getNumeroTelefono(),
                    cliente.getFechaDeNacimiento(),
                    cliente.getNacionalidad().name());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public Cliente buscar(String identificacion) {

        try {

            String query = "SELECT * FROM CLIENTE WHERE identificacion=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, identificacion);

            if (!resultado.next()) {
                return null;
            }

            String[] apellidos = resultado.getString("apellidos").split(" ", 2);

            String primerApellido = apellidos[0];
            String segundoApellido = "";

            if (apellidos.length > 1) {
                segundoApellido = apellidos[1];
            }

            return new Cliente(
                    resultado.getString("identificacion"),
                    Nacionalidad.valueOf(resultado.getString("nacionalidad")),
                    resultado.getDate("fecha_nacimiento").toLocalDate(),
                    resultado.getString("nombre"),
                    primerApellido,
                    segundoApellido,
                    resultado.getString("correo"),
                    resultado.getString("telefono")
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> lista = new ArrayList<>();

        try {

            String query = "SELECT * FROM CLIENTE ORDER BY nombre";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query);

            while (resultado.next()) {

                String[] apellidos = resultado.getString("apellidos").split(" ", 2);

                String primerApellido = apellidos[0];
                String segundoApellido = "";

                if (apellidos.length > 1) {
                    segundoApellido = apellidos[1];
                }

                Cliente cliente =
                        new Cliente(
                                resultado.getString("identificacion"),
                                Nacionalidad.valueOf(resultado.getString("nacionalidad")),
                                resultado.getDate("fecha_nacimiento").toLocalDate(),
                                resultado.getString("nombre"),
                                primerApellido,
                                segundoApellido,
                                resultado.getString("correo"),
                                resultado.getString("telefono")
                        );

                lista.add(cliente);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Cliente cliente) {

        try {

            String query = String.format(
                    "UPDATE CLIENTE SET " +
                            "nombre='%s', " +
                            "apellidos='%s %s', " +
                            "correo='%s', " +
                            "telefono='%s', " +
                            "fecha_nacimiento='%s', " +
                            "nacionalidad='%s' " +
                            "WHERE identificacion='%s'",
                    cliente.getNombre(),
                    cliente.getPrimerApellido(),
                    cliente.getSegundoApellido(),
                    cliente.getCorreoElectronico(),
                    cliente.getNumeroTelefono(),
                    cliente.getFechaDeNacimiento(),
                    cliente.getNacionalidad().name(),
                    cliente.getIdentificacion());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(String identificacion) {

        try {

            String query = String.format(
                    "DELETE FROM CLIENTE WHERE identificacion='%s'",
                    identificacion);

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean existe(String identificacion) {

        try {

            String query =
                    "SELECT * FROM CLIENTE WHERE identificacion=?";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query, identificacion);

            return resultado.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }
    public Cliente buscarPorCorreo(String correoElectronico) {
        try {
            String query = "SELECT * FROM CLIENTE WHERE correo=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, correoElectronico);
            if (!resultado.next()) {
                return null;
            }
            return new Cliente(
                    resultado.getString("identificacion"),
                    Nacionalidad.valueOf(resultado.getString("nacionalidad")),
                    resultado.getDate("fecha_nacimiento").toLocalDate(),
                    resultado.getString("nombre"),
                    resultado.getString("primer_apellido"),
                    resultado.getString("segundo_apellido"),
                    resultado.getString("correo"),
                    resultado.getString("telefono")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}