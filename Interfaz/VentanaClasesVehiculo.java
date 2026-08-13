package Interfaz;

import Entidades.ClaseDeVehiculo;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaClasesVehiculo extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<ClaseDeVehiculo> clasesVehiculo;

    private JButton btnModificar;
    private JButton btnEliminar;

    public VentanaClasesVehiculo(Controlador controlador) {

        super("Administrar Clases de Vehículo");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Nombre",
                        "Descripción",
                        "Precio por Día"
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

        btnModificar.addActionListener(e -> modificarClaseVehiculo());

        btnEliminar.addActionListener(e -> eliminarClaseVehiculo());

    }

    @Override
    protected void cargarDatos() {

        clasesVehiculo = controlador.listarClasesVehiculo();

        for (ClaseDeVehiculo clase : clasesVehiculo) {

            modeloTabla.addRow(new Object[]{
                    clase.getNombre(),
                    clase.getDescripcion(),
                    clase.getPrecioPorDia()
            });

        }

    }

    private void modificarClaseVehiculo() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        ClaseDeVehiculo claseSeleccionada =
                clasesVehiculo.get(getFilaSeleccionada());

        VentanaModificarClaseVehiculo ventana =
                new VentanaModificarClaseVehiculo(
                        controlador,
                        claseSeleccionada
                );

        ventana.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                refrescarTabla();

            }

        });

    }

    private void eliminarClaseVehiculo() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        ClaseDeVehiculo claseSeleccionada =
                clasesVehiculo.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea eliminar la clase \"" +
                        claseSeleccionada.getNombre() +
                        "\"?")) {
            return;
        }

        try {

            controlador.eliminarClaseVehiculo(claseSeleccionada);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "La clase de vehículo fue eliminada correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

}