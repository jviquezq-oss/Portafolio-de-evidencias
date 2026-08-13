package Interfaz;

import Entidades.Cliente;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaAdministrarClientes extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<Cliente> clientes;

    private JButton btnModificar;
    private JButton btnEliminar;

    public VentanaAdministrarClientes(Controlador controlador) {

        super("Administrar Clientes");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Identificación",
                        "Nombre",
                        "Primer Apellido",
                        "Segundo Apellido",
                        "Nacionalidad",
                        "Fecha Nacimiento",
                        "Correo Electrónico",
                        "Teléfono"
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

        btnModificar.addActionListener(e -> modificarCliente());

        btnEliminar.addActionListener(e -> eliminarCliente());

    }

    @Override
    protected void cargarDatos() {

        modeloTabla.setRowCount(0);

        clientes = controlador.listarClientes();

        for (Cliente cliente : clientes) {

            modeloTabla.addRow(new Object[]{
                    cliente.getIdentificacion(),
                    cliente.getNombre(),
                    cliente.getPrimerApellido(),
                    cliente.getSegundoApellido(),
                    cliente.getNacionalidad(),
                    cliente.getFechaDeNacimiento(),
                    cliente.getCorreoElectronico(),
                    cliente.getNumeroTelefono()
            });

        }

    }

    private void modificarCliente() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Cliente clienteSeleccionado =
                clientes.get(getFilaSeleccionada());

        VentanaModificarCliente ventana =
                new VentanaModificarCliente(
                        controlador,
                        clienteSeleccionado
                );

        ventana.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                refrescarTabla();

            }

        });

    }

    private void eliminarCliente() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        Cliente clienteSeleccionado =
                clientes.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea eliminar el siguiente cliente?\n\n" +
                        clienteSeleccionado.getNombre() + " " +
                        clienteSeleccionado.getPrimerApellido() + "\n" +
                        "Identificación: " +
                        clienteSeleccionado.getIdentificacion()
        )) {

            return;

        }

        try {

            controlador.eliminarCliente(clienteSeleccionado);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "El cliente fue eliminado correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

}