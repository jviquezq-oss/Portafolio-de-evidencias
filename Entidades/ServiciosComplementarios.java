package Entidades;

public class ServiciosComplementarios {
    private String nombre;
    private double precioPorDia;

    public ServiciosComplementarios(double precioPorDia, String nombre) {
        this.precioPorDia = precioPorDia;
        this.nombre = nombre;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ServiciosComplementarios{" +
                "nombre='" + nombre + '\'' +
                ", precioPorDia=" + precioPorDia +
                '}';
    }
}
