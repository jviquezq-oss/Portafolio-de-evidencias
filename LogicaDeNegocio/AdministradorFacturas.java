package LogicaDeNegocio;

import DAO.FacturaDAO;
import DAO.ReservaDAO;
import Entidades.EstadoReserva;
import Entidades.Factura;
import Entidades.Reserva;
import Excepciones.ExcepcionDeNegocio;

import java.util.ArrayList;

public class AdministradorFacturas {

    private final FacturaDAO facturaDAO;
    private final ReservaDAO reservaDAO;

    public AdministradorFacturas() {

        facturaDAO = new FacturaDAO();
        reservaDAO = new ReservaDAO();

    }

    public void generarFactura(Reserva reserva) throws ExcepcionDeNegocio {

        if (reserva == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar una reserva.");
        }

        Reserva reservaPersistida =
                reservaDAO.buscar(
                        reserva.getNumeroReserva());

        if (reservaPersistida == null) {
            throw new ExcepcionDeNegocio("La reserva no existe.");
        }

        if (reservaPersistida.getEstado() != EstadoReserva.FINALIZADA) {
            throw new ExcepcionDeNegocio(
                    "Solo se pueden facturar reservas finalizadas."
            );
        }

        ArrayList<Factura> facturas =
                facturaDAO.listar();

        for (Factura factura : facturas) {

            if (factura.getReserva() != null &&
                    factura.getReserva().getNumeroReserva() ==
                            reservaPersistida.getNumeroReserva()) {

                throw new ExcepcionDeNegocio(
                        "La reserva ya tiene una factura registrada."
                );

            }

        }

        Factura factura =
                reservaPersistida.generarFactura();

        if (!facturaDAO.registrar(factura)) {
            throw new ExcepcionDeNegocio(
                    "No fue posible generar la factura."
            );
        }

    }

    public ArrayList<Factura> listarFacturas() {

        return facturaDAO.listar();

    }

    public Factura buscarFactura(int numeroFactura) {

        return facturaDAO.buscar(numeroFactura);

    }

    public void eliminarFactura(Factura factura) throws ExcepcionDeNegocio {

        if (factura == null) {
            throw new ExcepcionDeNegocio("La factura no existe.");
        }

        if (!facturaDAO.existe(factura.getNumeroFactura())) {
            throw new ExcepcionDeNegocio(
                    "La factura no se encuentra registrada."
            );
        }

        if (!facturaDAO.eliminar(factura.getNumeroFactura())) {
            throw new ExcepcionDeNegocio(
                    "No fue posible eliminar la factura."
            );
        }

    }

}