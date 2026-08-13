package Interfaz;

import javax.swing.*;
import java.awt.*;

public abstract class VentanaFormulario extends JFrame {

    protected JPanel panelFormulario;
    protected JPanel panelBotones;

    protected JButton btnGuardar;
    protected JButton btnCancelar;

    protected GridBagConstraints gbc;

    public VentanaFormulario(String titulo) {

        setTitle(titulo);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelFormulario = new JPanel(new GridBagLayout());

        contenedor.add(panelFormulario, BorderLayout.NORTH);

        add(contenedor, BorderLayout.CENTER);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        inicializarComponentes();

        btnGuardar.addActionListener(e -> {

            if (validarCampos()) {
                guardar();
            }

        });

        btnCancelar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void agregarComponente(Component etiqueta,
                                     Component componente,
                                     int fila,
                                     int columna) {

        int columnaBase = columna * 2;

        gbc.gridx = columnaBase;
        gbc.gridy = fila;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        panelFormulario.add(etiqueta, gbc);

        gbc.gridx = columnaBase + 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelFormulario.add(componente, gbc);

    }

    protected void mostrarMensajeError(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    }

    protected void mostrarMensajeInformacion(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    protected boolean confirmarOperacion(String mensaje) {

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) == JOptionPane.YES_OPTION;

    }

    protected void limpiarCampo(JTextField campo) {

        campo.setText("");
        campo.requestFocus();

    }

    protected void cerrarVentana() {

        dispose();

    }
    protected JTextField crearCampoSoloLectura() {

        JTextField campo = new JTextField(20);

        campo.setEditable(false);
        campo.setFocusable(false);

        return campo;

    }

    protected abstract void inicializarComponentes();

    protected abstract boolean validarCampos();

    protected abstract void guardar();

    protected abstract void limpiarCampos();

}