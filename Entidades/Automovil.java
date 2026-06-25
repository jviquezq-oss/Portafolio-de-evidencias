package Entidades;

import java.time.LocalDate;
import Excepciones.ExcepcionDeNegocio;

public class Automovil {
    private String modelo;
    private LocalDate año;
    private String numberoVin;
    private String numeroPlaca;
    private Combustibles combustuble;
    private TipoVehiculo tipoDeVehiculo;
    private ClaseDeVehiculo claseDeVehiculo;
    private Marca marca;
    private boolean rentado = false;
    private boolean enMantenimiento = false;

    public Automovil(String modelo, LocalDate año, String numberoVin, String numeroPlaca, Combustibles combustuble, TipoVehiculo tipoDeVehiculo, ClaseDeVehiculo claseDeVehiculo, Marca marca) {
        this.modelo = modelo;
        this.año = año;
        this.numberoVin = numberoVin;
        this.numeroPlaca = numeroPlaca;
        this.combustuble = combustuble;
        this.tipoDeVehiculo = tipoDeVehiculo;
        this.claseDeVehiculo = claseDeVehiculo;
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalDate getAño() {
        return año;
    }

    public String getNumberoVin() {
        return numberoVin;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public Combustibles getCombustuble() {
        return combustuble;
    }

    public boolean isRentado() {
        return rentado;
    }

    public ClaseDeVehiculo getClaseDeVehiculo() {
        return claseDeVehiculo;
    }

    public void setClaseDeVehiculo(ClaseDeVehiculo claseDeVehiculo) {
        this.claseDeVehiculo = claseDeVehiculo;
    }

    public TipoVehiculo getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setTipoDeVehiculo(TipoVehiculo tipoDeVehiculo) {
        this.tipoDeVehiculo = tipoDeVehiculo;
    }
    public void rentar(){
        if(this.rentado){
            throw new ExcepcionDeNegocio("Este vehiculo ya se encuentra rentado.");
        } else if (this.enMantenimiento) {
            throw new ExcepcionDeNegocio("El vehiculo se encuentra en mantenimiento.");
        }else {
            this.rentado = true;
        }
    }
    public void liberarVehiculo(){
        if(this.rentado){
            this.rentado = false;
        }else{
            throw new ExcepcionDeNegocio("Este vehiculo no se ecuentra rentado.");
        }
    }

    @Override
    public String toString() {
        return "Automovil{" +
                "modelo='" + modelo + '\'' +
                ", año=" + año +
                ", numberoVin='" + numberoVin + '\'' +
                ", numeroPlaca='" + numeroPlaca + '\'' +
                ", combustuble=" + combustuble +
                ", tipoDeVehiculo=" + tipoDeVehiculo +
                ", claseDeVehiculo=" + claseDeVehiculo +
                ", rentado=" + rentado +
                ", enMantenimiento=" + enMantenimiento +
                '}';
    }
}
