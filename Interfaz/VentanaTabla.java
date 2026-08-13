package Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class VentanaTabla extends JFrame {

    protected JTable tabla;
    protected DefaultTableModel modeloTabla;

    protected JPanel panelPrincipal;
    protected JPanel panelBotones;

    protected JButton btnCerrar;

    public VentanaTabla(String titulo) {

        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelPrincipal = new JPanel(new BorderLayout(10,10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        setContentPane(panelPrincipal);

        // Crear primero el panel de botones
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnCerrar);

        // Ahora la clase hija puede agregar botones
        inicializarTabla();

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(850,350));

        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected void agregarBoton(JButton boton){

        panelBotones.add(boton,panelBotones.getComponentCount()-1);

        panelBotones.revalidate();
        panelBotones.repaint();

    }

    protected int getFilaSeleccionada(){

        return tabla.getSelectedRow();

    }

    protected boolean hayFilaSeleccionada(){

        if(tabla.getSelectedRow()==-1){

            mostrarMensajeError("Debe seleccionar un registro.");

            return false;

        }

        return true;

    }

    protected void refrescarTabla(){

        modeloTabla.setRowCount(0);

        cargarDatos();

    }

    protected boolean confirmarOperacion(String mensaje){

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        )==JOptionPane.YES_OPTION;

    }

    protected void mostrarMensajeInformacion(String mensaje){

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    protected void mostrarMensajeError(String mensaje){

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    }

    protected abstract void inicializarTabla();

    protected abstract void cargarDatos();

}