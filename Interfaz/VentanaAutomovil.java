package Interfaz;

import Entidades.Automovil;
import Entidades.ClaseDeVehiculo;
import Entidades.Combustibles;
import Entidades.Marca;
import Entidades.TipoVehiculo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaAutomovil {

    public static Automovil solicitarAutomovil(List<ClaseDeVehiculo> clasesVehiculo) {

        if (clasesVehiculo == null || clasesVehiculo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No existen clases de vehículo registradas. Debe registrar una clase de vehículo primero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;
        }

        while (true) {

            JTextField txtModelo = new JTextField(20);
            JTextField txtVin = new JTextField(20);
            JTextField txtPlaca = new JTextField(20);
            JTextField txtAnio = new JTextField(20);

            JComboBox<Marca> cmbMarca = new JComboBox<>(Marca.values());
            JComboBox<Combustibles> cmbCombustible = new JComboBox<>(Combustibles.values());
            JComboBox<TipoVehiculo> cmbTipoVehiculo = new JComboBox<>(TipoVehiculo.values());

            JComboBox<ClaseDeVehiculo> cmbClaseVehiculo =
                    new JComboBox<>(clasesVehiculo.toArray(new ClaseDeVehiculo[0]));

            JPanel panel = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Modelo:"), gbc);

            gbc.gridx = 1;
            panel.add(txtModelo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Número VIN:"), gbc);

            gbc.gridx = 1;
            panel.add(txtVin, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Número de Placa:"), gbc);

            gbc.gridx = 1;
            panel.add(txtPlaca, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Año:"), gbc);

            gbc.gridx = 1;
            panel.add(txtAnio, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Marca:"), gbc);

            gbc.gridx = 1;
            panel.add(cmbMarca, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(new JLabel("Combustible:"), gbc);

            gbc.gridx = 1;
            panel.add(cmbCombustible, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(new JLabel("Tipo Vehículo:"), gbc);

            gbc.gridx = 1;
            panel.add(cmbTipoVehiculo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            panel.add(new JLabel("Clase Vehículo:"), gbc);

            gbc.gridx = 1;
            panel.add(cmbClaseVehiculo, gbc);

            int resultado = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Nuevo Vehículo",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (resultado != JOptionPane.OK_OPTION) {
                return null;
            }

            StringBuilder errores = new StringBuilder();

            String modelo = txtModelo.getText().trim();
            String vin = txtVin.getText().trim();
            String placa = txtPlaca.getText().trim();

            if (modelo.isBlank()) {
                errores.append("- Debe ingresar el modelo.\n");
            }

            if (vin.isBlank()) {
                errores.append("- Debe ingresar el número VIN.\n");
            }

            if (placa.isBlank()) {
                errores.append("- Debe ingresar el número de placa.\n");
            }

            if (vin.length() > 0 && vin.length() < 5) {
                errores.append("- El número VIN es demasiado corto.\n");
            }

            if (placa.length() > 0 && placa.length() < 4) {
                errores.append("- El número de placa es demasiado corto.\n");
            }

            if (cmbMarca.getSelectedItem() == null) {
                errores.append("- Debe seleccionar una marca.\n");
            }

            if (cmbCombustible.getSelectedItem() == null) {
                errores.append("- Debe seleccionar un combustible.\n");
            }

            if (cmbTipoVehiculo.getSelectedItem() == null) {
                errores.append("- Debe seleccionar un tipo de vehículo.\n");
            }

            if (cmbClaseVehiculo.getSelectedItem() == null) {
                errores.append("- Debe seleccionar una clase de vehículo.\n");
            }

            int anio = 0;

            try {

                String valorAnio = txtAnio.getText().trim();

                if (valorAnio.isBlank()) {

                    errores.append("- Debe ingresar el año.");

                } else {

                    anio = Integer.parseInt(valorAnio);

                    if (anio < 1950 || anio > LocalDate.now().getYear() + 1) {
                        errores.append("- El año ingresado no es válido.\n");
                    }
                }

            } catch (NumberFormatException e) {

                errores.append("- El año debe ser numérico.\n");
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

            Automovil nuevoAutomovil = new Automovil(
                    modelo,
                    LocalDate.of(anio, 1, 1),
                    vin,
                    placa,
                    (Combustibles) cmbCombustible.getSelectedItem(),
                    (TipoVehiculo) cmbTipoVehiculo.getSelectedItem(),
                    (ClaseDeVehiculo) cmbClaseVehiculo.getSelectedItem(),
                    (Marca) cmbMarca.getSelectedItem()
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo registrado exitosamente.\n\n" +
                            "Marca: " + cmbMarca.getSelectedItem() + "\n" +
                            "Modelo: " + modelo + "\n" +
                            "Placa: " + placa,
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return nuevoAutomovil;
        }
    }
}