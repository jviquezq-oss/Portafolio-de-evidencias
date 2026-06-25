package Entidades;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Factura {

    private static int consecutivoFacturas;
    private int numeroFactura;
    private LocalDate fechaEmision;
    private String nombreCliente;
    private String cedulaCliente;
    private String nombreVendedor;
    private String modeloVehiculo;
    private String placaVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private List<ServiciosComplementarios> serviciosComplementarios;
    private double subtotal;
    private double total;

    public Factura(String nombreCliente, String cedulaCliente, String nombreVendedor, String modeloVehiculo, String placaVehiculo, LocalDate fechaInicio, LocalDate fechaFinalizacion, List<ServiciosComplementarios> serviciosComplementarios, double subtotal, double total) {

        consecutivoFacturas++;
        this.numeroFactura = consecutivoFacturas;
        this.fechaEmision = LocalDate.now();
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
        this.nombreVendedor = nombreVendedor;
        this.modeloVehiculo = modeloVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.serviciosComplementarios = serviciosComplementarios;

        this.subtotal = subtotal;
        this.total = total;
    }

    public void imprimirFactura() {

        System.out.println("==================================================");
        System.out.println("FACTURA");
        System.out.println("==================================================");
        System.out.println("Numero Factura: " + numeroFactura);
        System.out.println("Fecha Emision: " + fechaEmision);

        System.out.println("\nCLIENTE");
        System.out.println("Nombre: " + nombreCliente);
        System.out.println("Cedula: " + cedulaCliente);

        System.out.println("\nVENDEDOR");
        System.out.println("Nombre: " + nombreVendedor);

        System.out.println("\nVEHICULO");
        System.out.println("Modelo: " + modeloVehiculo);
        System.out.println("Placa: " + placaVehiculo);

        System.out.println("\nRESERVA");
        System.out.println("Fecha Inicio: " + fechaInicio);
        System.out.println("Fecha Finalizacion: " + fechaFinalizacion);

        System.out.println("\nSERVICIOS COMPLEMENTARIOS");

        if (serviciosComplementarios.isEmpty()) {
            System.out.println("No hay servicios complementarios.");
        } else {
            for (ServiciosComplementarios servicio : serviciosComplementarios) {
                System.out.println("- " + servicio.getNombre() + " : ₡" + servicio.getPrecioPorDia());
            }
        }

        System.out.println("\nTOTALES");
        System.out.println("Subtotal: ₡" + subtotal);
        System.out.println("Total: ₡" + total);
        System.out.println("==================================================");
    }
}

