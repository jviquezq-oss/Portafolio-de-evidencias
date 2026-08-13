package LogicaDeNegocio;

import DAO.AutomovilDAO;
import DAO.ClienteDAO;
import DAO.ReservaDAO;
import DAO.UsuarioDAO;
import Entidades.*;
import Excepciones.ExcepcionDeNegocio;

import java.time.LocalDate;
import java.util.ArrayList;

public class AdministradorReservas {

    private final ReservaDAO reservaDAO;
    private final AutomovilDAO automovilDAO;
    private final ClienteDAO clienteDAO;
    private final UsuarioDAO usuarioDAO;

    public AdministradorReservas() {

        reservaDAO = new ReservaDAO();
        automovilDAO = new AutomovilDAO();
        clienteDAO = new ClienteDAO();
        usuarioDAO = new UsuarioDAO();

    }

    public void registrarReserva(Automovil automovil, Cliente cliente, UsuarioDeSistema vendedor, LocalDate fechaInicio, LocalDate fechaFinalizacion) throws ExcepcionDeNegocio {

        if (automovil == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vehículo.");
        }

        if (cliente == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un cliente.");
        }

        if (vendedor == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vendedor.");
        }

        if (fechaInicio == null || fechaFinalizacion == null) {
            throw new ExcepcionDeNegocio("Debe ingresar las fechas de la reserva.");
        }

        if (fechaFinalizacion.isBefore(fechaInicio)) {
            throw new ExcepcionDeNegocio("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }

        Automovil automovilPersistido =
                automovilDAO.buscar(
                        automovil.getNumeroVin());

        if (automovilPersistido == null) {
            throw new ExcepcionDeNegocio("El vehículo no existe.");
        }

        Cliente clientePersistido =
                clienteDAO.buscar(
                        cliente.getIdentificacion());

        if (clientePersistido == null) {
            throw new ExcepcionDeNegocio("El cliente no existe.");
        }

        UsuarioDeSistema vendedorPersistido =
                usuarioDAO.buscar(
                        vendedor.getIdUsuario());

        if (vendedorPersistido == null) {
            throw new ExcepcionDeNegocio("El usuario no existe.");
        }

        try {

            automovilPersistido.reservar();

            Reserva nuevaReserva =
                    new Reserva(
                            automovilPersistido,
                            clientePersistido,
                            vendedorPersistido,
                            fechaInicio,
                            fechaFinalizacion);

            if (!reservaDAO.registrar(nuevaReserva)) {
                throw new ExcepcionDeNegocio("No fue posible registrar la reserva.");
            }

            if (!automovilDAO.modificar(automovilPersistido)) {
                throw new ExcepcionDeNegocio("No fue posible actualizar el estado del vehículo.");
            }

        } catch (Exception e) {

            try {
                automovilPersistido.liberarVehiculo();
            } catch (Exception ignored) {
            }

            throw e;

        }

    }
    public ArrayList<Reserva> listarReservas() {

        return reservaDAO.listar();

    }

    public Reserva buscarReserva(int numeroReserva) {

        return reservaDAO.buscar(numeroReserva);

    }
    public void modificarReserva(Reserva reserva, Automovil nuevoAutomovil, LocalDate fechaInicio, LocalDate fechaFinal) throws ExcepcionDeNegocio {

        if (reserva == null) {
            throw new ExcepcionDeNegocio("La reserva no existe.");
        }

        if (!reservaDAO.existe(reserva.getNumeroReserva())) {
            throw new ExcepcionDeNegocio("La reserva no se encuentra registrada.");
        }

        if (reserva.getEstado() != EstadoReserva.ACTIVA) {
            throw new ExcepcionDeNegocio("Solo se pueden modificar reservas activas.");
        }

        if (nuevoAutomovil == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vehículo.");
        }

        Automovil automovilPersistido =
                automovilDAO.buscar(
                        nuevoAutomovil.getNumeroVin());

        if (automovilPersistido == null) {
            throw new ExcepcionDeNegocio("El vehículo no existe.");
        }

        if (fechaInicio == null || fechaFinal == null) {
            throw new ExcepcionDeNegocio("Debe ingresar las fechas de la reserva.");
        }

        if (fechaFinal.isBefore(fechaInicio)) {
            throw new ExcepcionDeNegocio("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }

        Automovil automovilAnterior = reserva.getAutomovil();

        if (!automovilAnterior.getNumeroVin().equals(automovilPersistido.getNumeroVin())) {

            automovilPersistido.reservar();

            automovilAnterior.liberarVehiculo();

            if (!automovilDAO.modificar(automovilAnterior)) {
                throw new ExcepcionDeNegocio("No fue posible actualizar el vehículo anterior.");
            }

            if (!automovilDAO.modificar(automovilPersistido)) {
                throw new ExcepcionDeNegocio("No fue posible actualizar el nuevo vehículo.");
            }

            reserva.setAutomovil(automovilPersistido);

        }

        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFinalizacion(fechaFinal);

        if (!reservaDAO.modificar(reserva)) {
            throw new ExcepcionDeNegocio("No fue posible modificar la reserva.");
        }

    }

    public void eliminarReserva(Reserva reserva) throws ExcepcionDeNegocio {

        if (reserva == null) {
            throw new ExcepcionDeNegocio("La reserva no existe.");
        }

        if (!reservaDAO.existe(reserva.getNumeroReserva())) {
            throw new ExcepcionDeNegocio("La reserva no se encuentra registrada.");
        }

        reserva.getAutomovil().liberarVehiculo();

        if (!automovilDAO.modificar(reserva.getAutomovil())) {
            throw new ExcepcionDeNegocio("No fue posible actualizar el estado del vehículo.");
        }

        if (!reservaDAO.eliminar(reserva.getNumeroReserva())) {
            throw new ExcepcionDeNegocio("No fue posible eliminar la reserva.");
        }

    }

    public void finalizarReserva(Reserva reserva) throws ExcepcionDeNegocio {

        if (reserva == null) {
            throw new ExcepcionDeNegocio("La reserva no existe.");
        }

        if (!reservaDAO.existe(reserva.getNumeroReserva())) {
            throw new ExcepcionDeNegocio("La reserva no se encuentra registrada.");
        }

        reserva.finalizarReserva();

        if (!reservaDAO.modificar(reserva)) {
            throw new ExcepcionDeNegocio("No fue posible actualizar la reserva.");
        }

        reserva.getAutomovil().liberarVehiculo();

        if (!automovilDAO.modificar(reserva.getAutomovil())) {
            throw new ExcepcionDeNegocio("No fue posible actualizar el estado del vehículo.");
        }

    }

}