package DAO;

import Entidades.Automovil;
import Entidades.Cliente;
import Entidades.EstadoReserva;
import Entidades.Reserva;
import Entidades.UsuarioDeSistema;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaDAO {

    public boolean registrar(Reserva reserva) {

        try {

            String query = String.format(
                    "INSERT INTO RESERVA(vin, identificacion_cliente, id_usuario, fecha_inicio, fecha_finalizacion, estado, precio_por_dia) " +
                            "VALUES('%s','%s',%d,'%s','%s','%s',%f)",
                    reserva.getAutomovil().getNumeroVin(),
                    reserva.getCliente().getIdentificacion(),
                    reserva.getVendedor().getIdUsuario(),
                    reserva.getFechaInicio(),
                    reserva.getFechaFinalizacion(),
                    reserva.getEstado().name(),
                    reserva.getPrecioPorDia());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public Reserva buscar(int numeroReserva) {

        try {

            String query =
                    "SELECT * FROM RESERVA WHERE id_reserva=?";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroReserva);
            if (!resultado.next()) {
                return null;
            }

            AutomovilDAO automovilDAO =
                    new AutomovilDAO();

            ClienteDAO clienteDAO =
                    new ClienteDAO();

            UsuarioDAO usuarioDAO =
                    new UsuarioDAO();

            Automovil automovil =
                    automovilDAO.buscar(
                            resultado.getString("vin"));

            Cliente cliente =
                    clienteDAO.buscar(
                            resultado.getString("identificacion_cliente"));

            UsuarioDeSistema vendedor =
                    usuarioDAO.buscar(
                            resultado.getInt("id_usuario"));

            return new Reserva(
                    resultado.getInt("id_reserva"),
                    automovil,
                    cliente,
                    vendedor,
                    resultado.getDate("fecha_inicio").toLocalDate(),
                    resultado.getDate("fecha_finalizacion").toLocalDate(),
                    EstadoReserva.valueOf(
                            resultado.getString("estado"))
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public ArrayList<Reserva> listar() {

        ArrayList<Reserva> lista =
                new ArrayList<>();

        try {

            String query = "SELECT * FROM RESERVA ORDER BY fecha_inicio";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query);

            while (resultado.next()) {

                AutomovilDAO automovilDAO =
                        new AutomovilDAO();

                ClienteDAO clienteDAO =
                        new ClienteDAO();

                UsuarioDAO usuarioDAO =
                        new UsuarioDAO();

                Automovil automovil =
                        automovilDAO.buscar(
                                resultado.getString("vin"));

                Cliente cliente =
                        clienteDAO.buscar(
                                resultado.getString("identificacion_cliente"));

                UsuarioDeSistema vendedor =
                        usuarioDAO.buscar(
                                resultado.getInt("id_usuario"));

                Reserva reserva =
                        new Reserva(
                                resultado.getInt("id_reserva"),
                                automovil,
                                cliente,
                                vendedor,
                                resultado.getDate("fecha_inicio").toLocalDate(),
                                resultado.getDate("fecha_finalizacion").toLocalDate(),
                                EstadoReserva.valueOf(
                                        resultado.getString("estado"))
                        );

                lista.add(reserva);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Reserva reserva) {

        try {

            String query = String.format(
                    "UPDATE RESERVA SET " +
                            "vin='%s', " +
                            "identificacion_cliente='%s', " +
                            "id_usuario=%d, " +
                            "fecha_inicio='%s', " +
                            "fecha_finalizacion='%s', " +
                            "estado='%s' " +
                            "WHERE id_reserva=%d",
                    reserva.getAutomovil().getNumeroVin(),
                    reserva.getCliente().getIdentificacion(),
                    reserva.getVendedor().getIdUsuario(),
                    reserva.getFechaInicio(),
                    reserva.getFechaFinalizacion(),
                    reserva.getEstado().name(),
                    reserva.getNumeroReserva());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int numeroReserva) {

        try {

            String query = String.format("DELETE FROM RESERVA WHERE id_reserva=%d", numeroReserva);

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean existe(int numeroReserva) {

        try {

            String query = "SELECT * FROM RESERVA WHERE id_reserva=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroReserva);

            return resultado.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

}