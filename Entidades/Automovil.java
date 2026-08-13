package Entidades;

import Excepciones.ExcepcionDeNegocio;

public class Automovil {

    private String modelo;
    private int anio;
    private String numeroVin;
    private String numeroPlaca;
    private Combustibles combustible;
    private TipoVehiculo tipoDeVehiculo;
    private ClaseDeVehiculo claseDeVehiculo;
    private Marca marca;
    private EstadoAutomovil estadoAutomovil;

    public Automovil(String modelo, int anio, String numeroVin, String numeroPlaca, Combustibles combustible, TipoVehiculo tipoDeVehiculo, ClaseDeVehiculo claseDeVehiculo, Marca marca) {
        this.modelo = modelo;
        this.anio = anio;
        this.numeroVin = numeroVin;
        this.numeroPlaca = numeroPlaca;
        this.combustible = combustible;
        this.tipoDeVehiculo = tipoDeVehiculo;
        this.claseDeVehiculo = claseDeVehiculo;
        this.marca = marca;
        this.estadoAutomovil = EstadoAutomovil.DISPONIBLE;
    }

    public Automovil(String modelo, int anio, String numeroVin, String numeroPlaca, Combustibles combustible, TipoVehiculo tipoDeVehiculo, ClaseDeVehiculo claseDeVehiculo, Marca marca, EstadoAutomovil estadoAutomovil) {
        this.modelo = modelo;
        this.anio = anio;
        this.numeroVin = numeroVin;
        this.numeroPlaca = numeroPlaca;
        this.combustible = combustible;
        this.tipoDeVehiculo = tipoDeVehiculo;
        this.claseDeVehiculo = claseDeVehiculo;
        this.marca = marca;
        this.estadoAutomovil = estadoAutomovil;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getNumeroVin() {
        return numeroVin;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Combustibles getCombustible() {
        return combustible;
    }

    public TipoVehiculo getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }

    public ClaseDeVehiculo getClaseDeVehiculo() {
        return claseDeVehiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public EstadoAutomovil getEstadoAutomovil() {
        return estadoAutomovil;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setNumeroVin(String numeroVin) {
        this.numeroVin = numeroVin;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public void setCombustible(Combustibles combustible) {
        this.combustible = combustible;
    }

    public void setTipoDeVehiculo(TipoVehiculo tipoDeVehiculo) {
        this.tipoDeVehiculo = tipoDeVehiculo;
    }

    public void setClaseDeVehiculo(ClaseDeVehiculo claseDeVehiculo) {
        this.claseDeVehiculo = claseDeVehiculo;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setEstadoAutomovil(EstadoAutomovil estadoAutomovil) {
        this.estadoAutomovil = estadoAutomovil;
    }

    public void rentar() {

        if (estadoAutomovil == EstadoAutomovil.ALQUILADO) {
            throw new ExcepcionDeNegocio("Este vehículo ya se encuentra alquilado.");
        }

        if (estadoAutomovil == EstadoAutomovil.MANTENIMIENTO) {
            throw new ExcepcionDeNegocio("El vehículo se encuentra en mantenimiento.");
        }

        estadoAutomovil = EstadoAutomovil.ALQUILADO;
    }

    public void reservar() {

        if (estadoAutomovil != EstadoAutomovil.DISPONIBLE) {
            throw new ExcepcionDeNegocio("El vehículo no se encuentra disponible para reservar.");
        }

        estadoAutomovil = EstadoAutomovil.RESERVADO;
    }

    public void liberarVehiculo() {

        if (estadoAutomovil == EstadoAutomovil.DISPONIBLE) {
            throw new ExcepcionDeNegocio("El vehículo ya se encuentra disponible.");
        }

        estadoAutomovil = EstadoAutomovil.DISPONIBLE;
    }

    public void enviarAMantenimiento() {

        if (estadoAutomovil == EstadoAutomovil.ALQUILADO) {
            throw new ExcepcionDeNegocio("No es posible enviar un vehículo alquilado a mantenimiento.");
        }

        estadoAutomovil = EstadoAutomovil.MANTENIMIENTO;
    }

    @Override
    public String toString() {
        return marca + " " + modelo;
    }

}