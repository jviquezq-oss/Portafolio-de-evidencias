package Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class VentanaTabla extends JFrame {

    protected JTable tabla;

    protected DefaultTableModel modeloTabla;

    protected JPanel panelBotones;

    protected JButton btnCerrar;

    public VentanaTabla(String titulo) {

        setTitle(titulo);
        setSize(900,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10,10));

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());

        inicializarTabla();

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        cargarDatos();

    }

    protected void mostrarMensajeError(String mensaje){

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    }

    protected void mostrarMensajeInformacion(String mensaje){

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    protected abstract void inicializarTabla();

    protected abstract void cargarDatos();

}