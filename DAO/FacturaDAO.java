package DAO;

import Entidades.Factura;
import Entidades.Reserva;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FacturaDAO {

    public boolean registrar(Factura factura) {

        try {

            String query = String.format(
                    "INSERT INTO FACTURA(id_reserva, fecha_emision, subtotal, impuesto, total) " +
                            "VALUES(%d,'%s',%f,%f,%f)",
                    factura.getReserva().getNumeroReserva(),
                    factura.getFechaEmision(),
                    factura.getSubtotal(),
                    factura.getImpuesto(),
                    factura.getTotal());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public Factura buscar(int numeroFactura) {

        try {

            String query =
                    "SELECT * FROM FACTURA WHERE id_factura=?";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query, numeroFactura);

            if (!resultado.next()) {
                return null;
            }

            ReservaDAO reservaDAO =
                    new ReservaDAO();

            Reserva reserva =
                    reservaDAO.buscar(
                            resultado.getInt("id_reserva"));

            return new Factura(
                    resultado.getInt("id_factura"),
                    reserva,
                    resultado.getDate("fecha_emision").toLocalDate(),
                    resultado.getDouble("subtotal"),
                    resultado.getDouble("impuesto"),
                    resultado.getDouble("total")
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public ArrayList<Factura> listar() {

        ArrayList<Factura> lista =
                new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM FACTURA ORDER BY fecha_emision";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query);

            while (resultado.next()) {

                ReservaDAO reservaDAO =
                        new ReservaDAO();

                Reserva reserva =
                        reservaDAO.buscar(
                                resultado.getInt("id_reserva"));

                Factura factura =
                        new Factura(
                                resultado.getInt("id_factura"),
                                reserva,
                                resultado.getDate("fecha_emision").toLocalDate(),
                                resultado.getDouble("subtotal"),
                                resultado.getDouble("impuesto"),
                                resultado.getDouble("total")
                        );

                lista.add(factura);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Factura factura) {

        try {

            String query = String.format(
                    "UPDATE FACTURA SET " +
                            "id_reserva=%d, " +
                            "fecha_emision='%s', " +
                            "subtotal=%f, " +
                            "impuesto=%f, " +
                            "total=%f " +
                            "WHERE id_factura=%d",
                    factura.getReserva().getNumeroReserva(),
                    factura.getFechaEmision(),
                    factura.getSubtotal(),
                    factura.getImpuesto(),
                    factura.getTotal(),
                    factura.getNumeroFactura());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int numeroFactura) {

        try {

            String query = String.format("DELETE FROM FACTURA WHERE id_factura=%d", numeroFactura);
            Connector.getConnection().ejecutarStatement(query);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean existe(int numeroFactura) {

        try {

            String query = "SELECT * FROM FACTURA WHERE id_factura=?";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroFactura);
            return resultado.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

}