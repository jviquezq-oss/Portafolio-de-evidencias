package Entidades;

import java.time.LocalDate;

public class Factura {

    private int numeroFactura;
    private Reserva reserva;
    private LocalDate fechaEmision;
    private double subtotal;
    private double impuesto;
    private double total;

    public Factura(Reserva reserva, double subtotal, double impuesto, double total) {
        this.reserva = reserva;
        this.fechaEmision = LocalDate.now();
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }
    public Factura(int numeroFactura, Reserva reserva, LocalDate fechaEmision, double subtotal, double impuesto, double total) {
        this.numeroFactura = numeroFactura;
        this.reserva = reserva;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void imprimirFactura() {

        System.out.println("==================================================");
        System.out.println("FACTURA");
        System.out.println("==================================================");

        System.out.println("Numero Factura: " + numeroFactura);
        System.out.println("Fecha Emision: " + fechaEmision);

        System.out.println("\nCLIENTE");
        System.out.println("Nombre: " + reserva.getCliente().getNombreCompleto());
        System.out.println("Cedula: " + reserva.getCliente().getIdentificacion());

        System.out.println("\nVENDEDOR");
        System.out.println("Nombre: " + reserva.getVendedor().getNombreCompleto());

        System.out.println("\nVEHICULO");
        System.out.println("Modelo: " + reserva.getAutomovil().getModelo());
        System.out.println("Placa: " + reserva.getAutomovil().getNumeroPlaca());

        System.out.println("\nRESERVA");
        System.out.println("Fecha Inicio: " + reserva.getFechaInicio());
        System.out.println("Fecha Finalizacion: " + reserva.getFechaFinalizacion());

        System.out.println("\nTOTALES");
        System.out.println("Subtotal: ₡" + subtotal);
        System.out.println("Impuesto: ₡" + impuesto);
        System.out.println("Total: ₡" + total);

        System.out.println("==================================================");
    }
}