package Interfaz;

import Entidades.ClaseDeVehiculo;

import javax.swing.*;
import java.awt.*;

public class VentanaClaseVehiculo {

    public static ClaseDeVehiculo solicitarClaseVehiculo() {

        while (true) {

            JTextField txtNombre = new JTextField(20);
            JTextField txtPrecioPorDia = new JTextField(20);

            JTextArea txtDescripcion = new JTextArea(4, 20);
            txtDescripcion.setLineWrap(true);
            txtDescripcion.setWrapStyleWord(true);

            JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

            JPanel panel = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Nombre:"), gbc);

            gbc.gridx = 1;
            panel.add(txtNombre, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Precio por Día:"), gbc);

            gbc.gridx = 1;
            panel.add(txtPrecioPorDia, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.NORTH;

            panel.add(new JLabel("Descripción:"), gbc);

            gbc.gridx = 1;
            panel.add(scrollDescripcion, gbc);

            int resultado = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Nueva Clase de Vehículo",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (resultado != JOptionPane.OK_OPTION) {
                return null;
            }

            StringBuilder errores = new StringBuilder();

            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();

            if (nombre.isBlank()) {
                errores.append("- Debe ingresar el nombre de la clase.\n");
            }

            if (descripcion.isBlank()) {
                errores.append("- Debe ingresar una descripción.\n");
            }

            double precioPorDia = 0;

            try {

                String precioTexto = txtPrecioPorDia.getText().trim();

                if (precioTexto.isBlank()) {

                    errores.append("- Debe ingresar el precio por día.\n");

                } else {

                    precioPorDia = Double.parseDouble(precioTexto);

                    if (precioPorDia <= 0) {
                        errores.append("- El precio por día debe ser mayor que cero.\n");
                    }
                }

            } catch (NumberFormatException e) {

                errores.append("- El precio por día debe ser numérico.\n");
            }

            if (!errores.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        errores.toString(),
                        "Errores de Validación",
                        JOptionPane.ERROR_MESSAGE
                );

                continue;
            }

            return new ClaseDeVehiculo(
                    nombre,
                    descripcion,
                    precioPorDia
            );
        }
    }
}