package Interfaz;

import Entidades.UsuarioDeSistema;
import Entidades.UsuarioVentas;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaAdministrarUsuarios extends VentanaTabla {

    private final Controlador controlador;

    private ArrayList<UsuarioVentas> usuarios;

    private JButton btnModificar;
    private JButton btnEliminar;

    public VentanaAdministrarUsuarios(Controlador controlador) {

        super("Administrar Vendedores");

        this.controlador = controlador;

        refrescarTabla();

    }

    @Override
    protected void inicializarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Nombre Completo",
                        "Correo Electrónico"
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

        btnModificar.addActionListener(e -> modificarUsuario());

        btnEliminar.addActionListener(e -> eliminarUsuario());

    }

    @Override
    protected void cargarDatos() {

        modeloTabla.setRowCount(0);

        usuarios = new ArrayList<>();

        for (UsuarioDeSistema usuario : controlador.listarUsuarios()) {

            if (usuario instanceof UsuarioVentas) {

                UsuarioVentas usuarioVentas =
                        (UsuarioVentas) usuario;

                usuarios.add(usuarioVentas);

                modeloTabla.addRow(new Object[]{
                        usuarioVentas.getNombreCompleto(),
                        usuarioVentas.getCorreoElectronico()
                });

            }

        }

    }

    private void modificarUsuario() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        UsuarioVentas usuarioSeleccionado =
                usuarios.get(getFilaSeleccionada());

        VentanaModificarUsuario ventana =
                new VentanaModificarUsuario(
                        controlador,
                        usuarioSeleccionado
                );

        ventana.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                refrescarTabla();

            }

        });

    }

    private void eliminarUsuario() {

        if (!hayFilaSeleccionada()) {
            return;
        }

        UsuarioVentas usuarioSeleccionado =
                usuarios.get(getFilaSeleccionada());

        if (!confirmarOperacion(
                "¿Desea eliminar al vendedor?\n\n" +
                        usuarioSeleccionado.getNombreCompleto()
        )) {

            return;

        }

        try {

            controlador.eliminarUsuario(usuarioSeleccionado);

            refrescarTabla();

            mostrarMensajeInformacion(
                    "El vendedor fue eliminado correctamente."
            );

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

}