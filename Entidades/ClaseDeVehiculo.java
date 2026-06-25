package Entidades;

public class ClaseDeVehiculo {
    private String nombre;
    private String descripcion;
    private double precioPorDia;

    public ClaseDeVehiculo(String nombre, String descripcion, double precioPorDia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioPorDia = precioPorDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
