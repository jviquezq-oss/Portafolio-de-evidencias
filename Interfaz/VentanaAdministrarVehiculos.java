package Interfaz;

import Entidades.Automovil;
import Entidades.EstadoAutomovil;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaAdministrarVehiculos extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<Automovil> automoviles;

    private JButton btnModificar;
    private JButton btnEliminar;

    public VentanaAdministrarVehiculos(Controlador controlador) {

        super("Administrar Vehículos");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Marca",
                        "Modelo",
                        "Placa",
                        "VIN",
                        "Año",
                        "Clase",
                        "Tipo",
                        "Combustible",
                        "Estado"
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
        btnEliminar = new JButton("Eliminar");

        agregarBoton(btnModificar);
        agregarBoton(btnEliminar);

        btnModificar.addActionListener(e -> modificarVehiculo());

        btnEliminar.addActionListener(e -> eliminarVehiculo());

    }

    @Override
    protected void cargarDatos() {

        modeloTabla.setRowCount(0);

        automoviles = controlador.listarAutomoviles();

        for (Automovil automovil : automoviles) {

            String estado;

            if (automovil.getEstadoAutomovil() == EstadoAutomovil.ALQUILADO) {

                estado = "Rentado";

            } else {

                estado = "Disponible";

            }

            modeloTabla.addRow(new Object[]{
                    automovil.getMarca(),
                    automovil.getModelo(),
                    automovil.getNumeroPlaca(),
                    automovil.getNumeroVin(),
                    automovil.getAnio(),
                    automovil.getClaseDeVehiculo().getNombre(),
                    automovil.getTipoDeVehiculo(),
                    automovil.getCombustible(),
                    estado
            });

        }

    }

    private void modificarVehiculo() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Automovil automovilSeleccionado =
                automoviles.get(getFilaSeleccionada());

        VentanaModificarAutomovil ventana =
                new VentanaModificarAutomovil(
                        controlador,
                        automovilSeleccionado
                );

        ventana.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                refrescarTabla();

            }

        });

    }

    private void eliminarVehiculo() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Automovil automovilSeleccionado =
                automoviles.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea eliminar el vehículo con placa " +
                        automovilSeleccionado.getNumeroPlaca() +
                        "?"
        )) {

            return;

        }

        try {

            controlador.eliminarAutomovil(automovilSeleccionado);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "El vehículo fue eliminado correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

}