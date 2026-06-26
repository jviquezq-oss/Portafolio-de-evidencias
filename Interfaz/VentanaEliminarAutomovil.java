package Interfaz;

import Entidades.Automovil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaEliminarAutomovil extends JFrame {

    private JTable tablaAutomoviles;
    private DefaultTableModel modeloTabla;
    private List<Automovil> automoviles;

    public VentanaEliminarAutomovil(List<Automovil> automoviles) {

        this.automoviles = automoviles;

        setTitle("Eliminar Vehículos");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout(10, 10));

        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "Marca",
                        "Modelo",
                        "Placa",
                        "Tipo",
                        "Clase",
                        "Estado"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaAutomoviles = new JTable(modeloTabla);
        tablaAutomoviles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaAutomoviles.getTableHeader().setReorderingAllowed(false);

        cargarAutomoviles();

        JScrollPane scroll = new JScrollPane(tablaAutomoviles);

        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnEliminar.addActionListener(e -> eliminarAutomovil());

        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarAutomoviles() {

        modeloTabla.setRowCount(0);

        for (Automovil automovil : automoviles) {

            modeloTabla.addRow(new Object[]{
                    automovil.getMarca(),
                    automovil.getModelo(),
                    automovil.getNumeroPlaca(),
                    automovil.getTipoDeVehiculo(),
                    automovil.getClaseDeVehiculo().getNombre(),
                    automovil.isRentado()
            });

        }

    }

    private void eliminarAutomovil() {

        int filaSeleccionada = tablaAutomoviles.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un vehículo para eliminar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Automovil automovilSeleccionado = automoviles.get(filaSeleccionada);

        if (automovilSeleccionado.isRentado() == true) {

            JOptionPane.showMessageDialog(
                    this,
                    "No es posible eliminar el vehículo.\n\n" +
                            "Estado actual: " + automovilSeleccionado.isRentado(),
                    "Operación no permitida",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar el vehículo seleccionado?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        automoviles.remove(filaSeleccionada);

        cargarAutomoviles();

        JOptionPane.showMessageDialog(
                this,
                "El vehículo fue eliminado exitosamente.",
                "Eliminación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

}