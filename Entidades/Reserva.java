package Entidades;

import Excepciones.ExcepcionDeNegocio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private int numeroReserva;
    private Automovil automovil;
    private Cliente cliente;
    private Factura factura;
    private UsuarioVentas vendedor;

    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;

    private List<ServiciosComplementarios> serviciosComplementarios;
    private List<Cliente> conductores;

    private EstadoReserva estado;

    private static int idReservas;

    public Reserva(Automovil automovil, Cliente cliente, UsuarioVentas vendedor, LocalDate fechaInicio, LocalDate fechaFinalizacion) {

        if (automovil == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vehiculo.");
        }

        if (cliente == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un cliente.");
        }

        if (vendedor == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vendedor.");
        }

        if (fechaFinalizacion.isBefore(fechaInicio)) {
            throw new ExcepcionDeNegocio("La fecha de finalizacion debe ser posterior a la fecha de inicio.");
        }

        idReservas++;

        this.numeroReserva = idReservas;
        this.automovil = automovil;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;

        this.serviciosComplementarios = new ArrayList<>();
        this.conductores = new ArrayList<>();

        this.conductores.add(cliente);

        this.estado = EstadoReserva.ACTIVA;
    }

    public long getCantidadDias() {
        return ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFinalizacion);
    }

    public double getPrecioNeto() {
        return getPrecioPorDia() * getCantidadDias();
    }

    public double getImpuesto() {
        return getPrecioNeto() * 0.13;
    }

    public double getPrecioTotal() {
        return getPrecioNeto() + getImpuesto();
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public UsuarioVentas getVendedor() {
        return vendedor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public List<ServiciosComplementarios> getServiciosComplementarios() {
        return new ArrayList<>(this.serviciosComplementarios);
    }

    public List<Cliente> getConductores() {
        return new ArrayList<>(this.conductores);
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void agregarServicioComplementario(ServiciosComplementarios nuevoServicio) {

        if (this.serviciosComplementarios.contains(nuevoServicio)) {
            throw new ExcepcionDeNegocio("El servicio ya se encuentra agregado a la reserva.");
        }

        this.serviciosComplementarios.add(nuevoServicio);
    }

    public void eliminarServicioComplementario(ServiciosComplementarios servicioEliminar) {

        if (this.serviciosComplementarios.isEmpty()) {
            throw new ExcepcionDeNegocio("No hay servicios complementarios seleccionados.");
        }

        this.serviciosComplementarios.remove(servicioEliminar);
    }

    public void agregarConductor(Cliente nuevoConductor) {

        if (this.conductores.contains(nuevoConductor)) {
            throw new ExcepcionDeNegocio("El conductor ya se encuentra registrado en la reserva.");
        }

        this.conductores.add(nuevoConductor);
    }

    public void eliminarConductor(Cliente conductorEliminar) {

        if (this.conductores.size() == 1) {
            throw new ExcepcionDeNegocio("No se puede eliminar el unico conductor de la reserva.");
        }

        this.conductores.remove(conductorEliminar);
    }

    public Factura generarFactura() {

        if (this.factura != null) {
            return this.factura;
        }

        this.factura = new Factura(
                this.cliente.getNombre(),
                this.cliente.getIdentificacion(),
                this.vendedor.getNombreCompleto(),
                this.automovil.getModelo(),
                this.automovil.getNumeroPlaca(),
                this.fechaInicio,
                this.fechaFinalizacion,
                new ArrayList<>(this.serviciosComplementarios),
                getPrecioNeto(),
                getPrecioTotal()
        );

        return this.factura;
    }

    public boolean verificarSobreExtension(LocalDate fechaDevolucion) {

        if (!fechaDevolucion.isAfter(this.fechaFinalizacion)) {
            return false;
        }

        this.fechaFinalizacion = fechaDevolucion;

        return true;
    }

    public void cancelarReserva() {

        if (this.estado == EstadoReserva.FINALIZADA) {
            throw new ExcepcionDeNegocio("No se puede cancelar una reserva finalizada.");
        }

        this.estado = EstadoReserva.CANCELADA;
    }

    public void finalizarReserva() {

        if (this.estado == EstadoReserva.CANCELADA) {
            throw new ExcepcionDeNegocio("No se puede finalizar una reserva cancelada.");
        }

        this.estado = EstadoReserva.FINALIZADA;
    }

    public double getPrecioPorDia() {

        double precioDiario = this.automovil.getClaseDeVehiculo().getPrecioPorDia();

        for (ServiciosComplementarios servicio : this.serviciosComplementarios) {
            precioDiario += servicio.getPrecioPorDia();
        }

        return precioDiario;
    }

}