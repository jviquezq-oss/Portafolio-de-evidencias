package Interfaz;

import Entidades.Factura;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VentanaVerFacturas extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<Factura> facturas;

    private JButton btnEliminar;
    private JButton btnImprimir;

    public VentanaVerFacturas(Controlador controlador) {

        super("Administrar Facturas");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Factura",
                        "Reserva",
                        "Cliente",
                        "Vehículo",
                        "Fecha Emisión",
                        "Subtotal",
                        "Impuesto",
                        "Total"
                },
                0
        ) {

            @Override
            public boolean isCellEditable(int fila, int columna) {

                return false;

            }

        };

        tabla = new JTable(modeloTabla);

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        btnEliminar = new JButton("Eliminar");
        btnImprimir = new JButton("Imprimir");

        agregarBoton(btnEliminar);
        agregarBoton(btnImprimir);

        btnEliminar.addActionListener(
                e -> eliminarFactura()
        );

        btnImprimir.addActionListener(
                e -> imprimirFactura()
        );

    }

    @Override
    protected void cargarDatos() {

        modeloTabla.setRowCount(0);

        facturas = controlador.listarFacturas();

        for (Factura factura : facturas) {

            modeloTabla.addRow(new Object[]{
                    factura.getNumeroFactura(),
                    factura.getReserva().getNumeroReserva(),
                    factura.getReserva().getCliente(),
                    factura.getReserva().getAutomovil(),
                    factura.getFechaEmision(),
                    factura.getSubtotal(),
                    factura.getImpuesto(),
                    factura.getTotal()
            });

        }

    }

    private void eliminarFactura() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Factura facturaSeleccionada =
                facturas.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea eliminar la factura #" +
                        facturaSeleccionada.getNumeroFactura() +
                        "?"
        )) {

            return;

        }

        try {

            controlador.eliminarFactura(
                    facturaSeleccionada
            );

            refrescarTabla();

            mostrarMensajeInformacion(
                    "La factura fue eliminada correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(
                    e.getMessage()
            );

        }

    }

    private void imprimirFactura() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Factura facturaSeleccionada =
                facturas.get(getFilaSeleccionada());

        facturaSeleccionada.imprimirFactura();

        mostrarMensajeInformacion(
                "La factura fue enviada a la consola."
        );

    }

}