package Interfaz;

import Entidades.Reserva;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaVerReservas extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<Reserva> reservas;

    private JButton btnModificar;
    private JButton btnCancelar;
    private JButton btnFinalizar;
    private JButton btnFactura;

    public VentanaVerReservas(Controlador controlador) {

        super("Administrar Reservas");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Reserva",
                        "Cliente",
                        "Vehículo",
                        "Vendedor",
                        "Inicio",
                        "Finalización",
                        "Estado",
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

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        btnModificar = new JButton("Modificar");
        btnCancelar = new JButton("Cancelar");
        btnFinalizar = new JButton("Finalizar");
        btnFactura = new JButton("Factura");

        agregarBoton(btnModificar);
        agregarBoton(btnCancelar);
        agregarBoton(btnFinalizar);
        agregarBoton(btnFactura);

        btnModificar.addActionListener(e -> modificarReserva());

        btnCancelar.addActionListener(e -> cancelarReserva());

        btnFinalizar.addActionListener(e -> finalizarReserva());

        btnFactura.addActionListener(e -> generarFactura());

    }

    @Override
    protected void cargarDatos() {

        modeloTabla.setRowCount(0);

        reservas = controlador.listarReservas();

        for (Reserva reserva : reservas) {

            modeloTabla.addRow(new Object[]{
                    reserva.getNumeroReserva(),
                    reserva.getCliente(),
                    reserva.getAutomovil(),
                    reserva.getVendedor(),
                    reserva.getFechaInicio(),
                    reserva.getFechaFinalizacion(),
                    reserva.getEstado(),
                    reserva.getPrecioTotal()
            });

        }

    }

    private void modificarReserva() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Reserva reservaSeleccionada =
                reservas.get(getFilaSeleccionada());

        VentanaModificarReserva ventana =
                new VentanaModificarReserva(
                        controlador,
                        reservaSeleccionada
                );

        ventana.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                refrescarTabla();

            }

        });

    }

    private void cancelarReserva() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Reserva reservaSeleccionada =
                reservas.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea cancelar la reserva #" +
                        reservaSeleccionada.getNumeroReserva() +
                        "?"
        )) {

            return;

        }

        try {

            controlador.eliminarReserva(reservaSeleccionada);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "La reserva fue cancelada correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    private void finalizarReserva() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Reserva reservaSeleccionada =
                reservas.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea finalizar la reserva #" +
                        reservaSeleccionada.getNumeroReserva() +
                        "?"
        )) {

            return;

        }

        try {

            controlador.finalizarReserva(reservaSeleccionada);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "La reserva fue finalizada correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    private void generarFactura() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Reserva reservaSeleccionada =
                reservas.get(getFilaSeleccionada());

        try {

            controlador.generarFactura(reservaSeleccionada);

            mostrarMensajeInformacion(
                    "La factura fue generada correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

}