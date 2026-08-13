package Entidades;

import java.time.LocalDate;

public class Cliente {
    private String identificacion;
    private Nacionalidad nacionalidad;
    private LocalDate fechaDeNacimiento;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private String numeroTelefono;

    public Cliente(String identificacion, Nacionalidad nacionalidad, LocalDate fechaDeNacimiento, String nombre, String primerApellido, String segundoApellido, String correoElectronico, String numeroTelefono) {
        this.identificacion = identificacion;
        this.nacionalidad = nacionalidad;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }
    public String getNombreCompleto(){return this.nombre+" "+this.primerApellido+" "+this.segundoApellido;}
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return this.identificacion+" - "+this.nombre+" "+this.primerApellido+" "+this.segundoApellido;
    }
}
