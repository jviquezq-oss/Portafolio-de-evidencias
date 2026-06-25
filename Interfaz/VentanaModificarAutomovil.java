package Interfaz;

import Entidades.Automovil;
import Entidades.ClaseDeVehiculo;
import Entidades.Marca;
import Entidades.TipoVehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaModificarAutomovil {

    public static void modificarAutomovil(List<Automovil> automoviles, List<ClaseDeVehiculo> clasesVehiculo) {

        if (automoviles == null || automoviles.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No existen vehículos registrados.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Automovil automovilSeleccionado = (Automovil) JOptionPane.showInputDialog(
                null,
                "Seleccione el vehículo que desea modificar:",
                "Modificar Vehículo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                automoviles.toArray(),
                automoviles.get(0)
        );

        if (automovilSeleccionado == null) {
            return;
        }

        JComboBox<Marca> cmbMarca = new JComboBox<>(Marca.values());
        cmbMarca.setSelectedItem(automovilSeleccionado.getMarca());

        JComboBox<TipoVehiculo> cmbTipoVehiculo = new JComboBox<>(TipoVehiculo.values());
        cmbTipoVehiculo.setSelectedItem(automovilSeleccionado.getTipoDeVehiculo());

        JComboBox<ClaseDeVehiculo> cmbClaseVehiculo =
                new JComboBox<>(clasesVehiculo.toArray(new ClaseDeVehiculo[0]));

        cmbClaseVehiculo.setSelectedItem(automovilSeleccionado.getClaseDeVehiculo());

        JTextField txtModelo = new JTextField(automovilSeleccionado.getModelo(), 20);
        JTextField txtAnio = new JTextField(String.valueOf(automovilSeleccionado.getAño().getYear()), 20);
        JTextField txtVin = new JTextField(automovilSeleccionado.getNumberoVin(), 20);
        JTextField txtPlaca = new JTextField(automovilSeleccionado.getNumeroPlaca(), 20);
        JTextField txtCombustible = new JTextField(automovilSeleccionado.getCombustuble().toString(), 20);
        JTextField txtRentado = new JTextField(automovilSeleccionado.isRentado() ? "Sí" : "No", 20);

        txtModelo.setEditable(false);
        txtAnio.setEditable(false);
        txtVin.setEditable(false);
        txtPlaca.setEditable(false);
        txtCombustible.setEditable(false);
        txtRentado.setEditable(false);

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
        panel.add(new JLabel("Año:"), gbc);

        gbc.gridx = 1;
        panel.add(txtAnio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Número VIN:"), gbc);

        gbc.gridx = 1;
        panel.add(txtVin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Número de Placa:"), gbc);

        gbc.gridx = 1;
        panel.add(txtPlaca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Combustible:"), gbc);

        gbc.gridx = 1;
        panel.add(txtCombustible, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Rentado:"), gbc);

        gbc.gridx = 1;
        panel.add(txtRentado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Marca:"), gbc);

        gbc.gridx = 1;
        panel.add(cmbMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Tipo Vehículo:"), gbc);

        gbc.gridx = 1;
        panel.add(cmbTipoVehiculo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Clase Vehículo:"), gbc);

        gbc.gridx = 1;
        panel.add(cmbClaseVehiculo, gbc);

        int resultado = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Modificar Vehículo",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        automovilSeleccionado.setMarca((Marca) cmbMarca.getSelectedItem());
        automovilSeleccionado.setTipoDeVehiculo((TipoVehiculo) cmbTipoVehiculo.getSelectedItem());
        automovilSeleccionado.setClaseDeVehiculo((ClaseDeVehiculo) cmbClaseVehiculo.getSelectedItem());

        JOptionPane.showMessageDialog(
                null,
                "El vehículo fue modificado exitosamente.",
                "Modificación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}