package Interfaz;

import Entidades.ClaseDeVehiculo;
import Entidades.Combustibles;
import Entidades.Marca;
import Entidades.TipoVehiculo;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VentanaAutomovil extends VentanaFormulario {

    private final Controlador controlador;

    private JTextField txtModelo;
    private JTextField txtNumeroVin;
    private JTextField txtNumeroPlaca;
    private JTextField txtAnio;

    private JComboBox<Marca> cmbMarca;
    private JComboBox<Combustibles> cmbCombustible;
    private JComboBox<TipoVehiculo> cmbTipoVehiculo;
    private JComboBox<ClaseDeVehiculo> cmbClaseVehiculo;

    public VentanaAutomovil(Controlador controlador) {

        super("Nuevo Vehículo");

        this.controlador = controlador;

        cargarClasesVehiculo();

    }

    @Override
    protected void inicializarComponentes() {

        txtModelo = new JTextField(20);
        txtNumeroVin = new JTextField(20);
        txtNumeroPlaca = new JTextField(20);
        txtAnio = new JTextField(20);

        cmbMarca = new JComboBox<>(Marca.values());
        cmbCombustible = new JComboBox<>(Combustibles.values());
        cmbTipoVehiculo = new JComboBox<>(TipoVehiculo.values());
        cmbClaseVehiculo = new JComboBox<>();

        agregarComponente(new JLabel("Modelo:"), txtModelo, 0, 0);
        agregarComponente(new JLabel("Marca:"), cmbMarca, 0, 1);

        agregarComponente(new JLabel("Número VIN:"), txtNumeroVin, 1, 0);
        agregarComponente(new JLabel("Combustible:"), cmbCombustible, 1, 1);

        agregarComponente(new JLabel("Número de Placa:"), txtNumeroPlaca, 2, 0);
        agregarComponente(new JLabel("Tipo Vehículo:"), cmbTipoVehiculo, 2, 1);

        agregarComponente(new JLabel("Año:"), txtAnio, 3, 0);
        agregarComponente(new JLabel("Clase Vehículo:"), cmbClaseVehiculo, 3, 1);

    }

    private void cargarClasesVehiculo() {

        ArrayList<ClaseDeVehiculo> clasesVehiculo =
                controlador.listarClasesVehiculo();

        for (ClaseDeVehiculo clase : clasesVehiculo) {

            cmbClaseVehiculo.addItem(clase);

        }

    }

    @Override
    protected boolean validarCampos() {

        StringBuilder errores = new StringBuilder();

        if (txtModelo.getText().trim().isEmpty()) {
            errores.append("- Debe ingresar el modelo.\n");
        }

        if (txtNumeroVin.getText().trim().isEmpty()) {
            errores.append("- Debe ingresar el número VIN.\n");
        }

        if (txtNumeroPlaca.getText().trim().isEmpty()) {
            errores.append("- Debe ingresar el número de placa.\n");
        }

        if (txtNumeroVin.getText().trim().length() > 0 &&
                txtNumeroVin.getText().trim().length() < 5) {

            errores.append("- El número VIN es demasiado corto.\n");

        }

        if (txtNumeroPlaca.getText().trim().length() > 0 &&
                txtNumeroPlaca.getText().trim().length() < 4) {

            errores.append("- El número de placa es demasiado corto.\n");

        }

        try {

            int anio = Integer.parseInt(txtAnio.getText().trim());

            if (anio < 1950 || anio > LocalDate.now().getYear() + 1) {
                errores.append("- El año ingresado no es válido.\n");
            }

        } catch (Exception e) {

            errores.append("- Debe ingresar un año válido.\n");

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

        if (!errores.isEmpty()) {

            mostrarMensajeError(errores.toString());

            return false;

        }

        return true;

    }

    @Override
    protected void guardar() {

        try {

            controlador.registrarAutomovil(
                    txtModelo.getText().trim(),
                    Integer.parseInt(txtAnio.getText().trim()),
                    txtNumeroVin.getText().trim(),
                    txtNumeroPlaca.getText().trim(),
                    (Combustibles) cmbCombustible.getSelectedItem(),
                    (TipoVehiculo) cmbTipoVehiculo.getSelectedItem(),
                    (ClaseDeVehiculo) cmbClaseVehiculo.getSelectedItem(),
                    (Marca) cmbMarca.getSelectedItem()
            );

            mostrarMensajeInformacion(
                    "Vehículo registrado exitosamente."
            );

            limpiarCampos();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    @Override
    protected void limpiarCampos() {

        txtModelo.setText("");
        txtNumeroVin.setText("");
        txtNumeroPlaca.setText("");
        txtAnio.setText("");

        cmbMarca.setSelectedIndex(0);
        cmbCombustible.setSelectedIndex(0);
        cmbTipoVehiculo.setSelectedIndex(0);

        if (cmbClaseVehiculo.getItemCount() > 0) {
            cmbClaseVehiculo.setSelectedIndex(0);
        }

        txtModelo.requestFocus();

    }

}