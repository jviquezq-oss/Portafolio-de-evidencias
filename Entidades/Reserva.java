package Entidades;

import Excepciones.ExcepcionDeNegocio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

    private int numeroReserva;

    private Automovil automovil;
    private Cliente cliente;
    private UsuarioDeSistema vendedor;

    private Factura factura;

    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;

    private EstadoReserva estado;

    public Reserva(Automovil automovil, Cliente cliente, UsuarioDeSistema vendedor, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        if (automovil == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vehículo.");
        }
        if (cliente == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un cliente.");
        }
        if (vendedor == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un vendedor.");
        }
        if (fechaFinalizacion.isBefore(fechaInicio)) {
            throw new ExcepcionDeNegocio("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }
        this.automovil = automovil;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;

        this.estado = EstadoReserva.ACTIVA;
    }
    public Reserva(int numeroReserva, Automovil automovil, Cliente cliente, UsuarioDeSistema vendedor, LocalDate fechaInicio, LocalDate fechaFinalizacion, EstadoReserva estado) {
        this.numeroReserva = numeroReserva;
        this.automovil = automovil;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
    }

    public long getCantidadDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFinalizacion);
    }

    public double getPrecioPorDia() {
        return automovil.getClaseDeVehiculo().getPrecioPorDia();
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

    public Factura generarFactura() {

        if (factura != null) {
            return factura;
        }

        factura = new Factura(
                this,
                getPrecioNeto(),
                getImpuesto(),
                getPrecioTotal()
        );

        return factura;
    }

    public boolean verificarSobreExtension(LocalDate fechaDevolucion) {

        if (!fechaDevolucion.isAfter(fechaFinalizacion)) {
            return false;
        }

        fechaFinalizacion = fechaDevolucion;

        return true;
    }

    public void cancelarReserva() {

        if (estado == EstadoReserva.FINALIZADA) {
            throw new ExcepcionDeNegocio("No se puede cancelar una reserva finalizada.");
        }

        estado = EstadoReserva.CANCELADA;
    }

    public void finalizarReserva() {

        if (estado == EstadoReserva.CANCELADA) {
            throw new ExcepcionDeNegocio("No se puede finalizar una reserva cancelada.");
        }

        estado = EstadoReserva.FINALIZADA;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public UsuarioDeSistema getVendedor() {
        return vendedor;
    }

    public Factura getFactura() {
        return factura;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}