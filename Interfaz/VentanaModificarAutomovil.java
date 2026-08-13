package Interfaz;

import Entidades.Automovil;
import Entidades.ClaseDeVehiculo;
import Entidades.Marca;
import Entidades.TipoVehiculo;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.util.ArrayList;

public class VentanaModificarAutomovil extends VentanaFormulario {

    private final Controlador controlador;
    private final Automovil automovil;

    private JTextField txtModelo;
    private JTextField txtAnio;
    private JTextField txtNumeroVin;
    private JTextField txtNumeroPlaca;
    private JTextField txtCombustible;

    private JComboBox<Marca> cmbMarca;
    private JComboBox<TipoVehiculo> cmbTipoVehiculo;
    private JComboBox<ClaseDeVehiculo> cmbClaseVehiculo;

    public VentanaModificarAutomovil(Controlador controlador,
                                     Automovil automovil) {

        super("Modificar Vehículo");

        this.controlador = controlador;
        this.automovil = automovil;

        cargarClasesVehiculo();

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        txtModelo = new JTextField(20);
        txtAnio = new JTextField(20);
        txtNumeroVin = new JTextField(20);
        txtNumeroPlaca = new JTextField(20);
        txtCombustible = new JTextField(20);

        txtModelo.setEditable(false);
        txtAnio.setEditable(false);
        txtNumeroVin.setEditable(false);
        txtNumeroPlaca.setEditable(false);
        txtCombustible.setEditable(false);

        cmbMarca = new JComboBox<>(Marca.values());
        cmbTipoVehiculo = new JComboBox<>(TipoVehiculo.values());
        cmbClaseVehiculo = new JComboBox<>();

        agregarComponente(new JLabel("Modelo:"), txtModelo, 0, 0);
        agregarComponente(new JLabel("Marca:"), cmbMarca, 0, 1);

        agregarComponente(new JLabel("Año:"), txtAnio, 1, 0);
        agregarComponente(new JLabel("Tipo:"), cmbTipoVehiculo, 1, 1);

        agregarComponente(new JLabel("Número VIN:"), txtNumeroVin, 2, 0);
        agregarComponente(new JLabel("Clase:"), cmbClaseVehiculo, 2, 1);

        agregarComponente(new JLabel("Placa:"), txtNumeroPlaca, 3, 0);
        agregarComponente(new JLabel("Combustible:"), txtCombustible, 3, 1);

    }

    private void cargarClasesVehiculo() {

        ArrayList<ClaseDeVehiculo> clasesVehiculo =
                controlador.listarClasesVehiculo();

        for (ClaseDeVehiculo clase : clasesVehiculo) {

            cmbClaseVehiculo.addItem(clase);

        }

    }

    private void cargarDatos() {

        txtModelo.setText(automovil.getModelo());
        txtAnio.setText(String.valueOf(automovil.getAnio()));
        txtNumeroVin.setText(automovil.getNumeroVin());
        txtNumeroPlaca.setText(automovil.getNumeroPlaca());
        txtCombustible.setText(automovil.getCombustible().toString());

        cmbMarca.setSelectedItem(automovil.getMarca());
        cmbTipoVehiculo.setSelectedItem(automovil.getTipoDeVehiculo());
        cmbClaseVehiculo.setSelectedItem(automovil.getClaseDeVehiculo());

    }

    @Override
    protected boolean validarCampos() {

        if (cmbMarca.getSelectedItem() == null) {

            mostrarMensajeError(
                    "Debe seleccionar una marca."
            );

            return false;

        }

        if (cmbTipoVehiculo.getSelectedItem() == null) {

            mostrarMensajeError(
                    "Debe seleccionar un tipo de vehículo."
            );

            return false;

        }

        if (cmbClaseVehiculo.getSelectedItem() == null) {

            mostrarMensajeError(
                    "Debe seleccionar una clase de vehículo."
            );

            return false;

        }

        return true;

    }

    @Override
    protected void guardar() {

        try {

            controlador.modificarAutomovil(
                    automovil,
                    (Marca) cmbMarca.getSelectedItem(),
                    (TipoVehiculo) cmbTipoVehiculo.getSelectedItem(),
                    (ClaseDeVehiculo) cmbClaseVehiculo.getSelectedItem()
            );

            mostrarMensajeInformacion(
                    "Vehículo modificado correctamente."
            );

            cerrarVentana();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    @Override
    protected void limpiarCampos() {

        cargarDatos();

    }

}