package Interfaz;

import Entidades.ClaseDeVehiculo;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;

public class VentanaModificarClaseVehiculo extends VentanaFormulario {

    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtPrecioPorDia;

    private final Controlador controlador;
    private final ClaseDeVehiculo claseVehiculo;

    public VentanaModificarClaseVehiculo(Controlador controlador, ClaseDeVehiculo claseVehiculo) {

        super("Modificar Clase de Vehículo");

        this.controlador = controlador;
        this.claseVehiculo = claseVehiculo;

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        txtNombre = new JTextField(20);

        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        txtPrecioPorDia = new JTextField(20);

        agregarComponente(
                new JLabel("Nombre:"),
                txtNombre,
                0,
                0
        );

        agregarComponente(
                new JLabel("Precio por Día:"),
                txtPrecioPorDia,
                1,
                0
        );

        agregarComponente(
                new JLabel("Descripción:"),
                new JScrollPane(txtDescripcion),
                2,
                0
        );

    }

    private void cargarDatos() {

        txtNombre.setText(claseVehiculo.getNombre());

        txtDescripcion.setText(claseVehiculo.getDescripcion());

        txtPrecioPorDia.setText(String.valueOf(claseVehiculo.getPrecioPorDia()));

    }

    @Override
    protected boolean validarCampos() {

        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().trim().isEmpty()) {
            errores.append("- Debe ingresar el nombre.\n");
        }

        if (txtDescripcion.getText().trim().isEmpty()) {
            errores.append("- Debe ingresar la descripción.\n");
        }

        if (txtPrecioPorDia.getText().trim().isEmpty()) {

            errores.append("- Debe ingresar el precio por día.\n");

        } else {

            try {

                double precio = Double.parseDouble(txtPrecioPorDia.getText().trim());

                if (precio <= 0) {
                    errores.append("- El precio debe ser mayor que cero.\n");
                }

            } catch (NumberFormatException e) {

                errores.append("- El precio debe ser numérico.\n");

            }

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

            controlador.modificarClaseVehiculo(
                    claseVehiculo,
                    txtNombre.getText().trim(),
                    txtDescripcion.getText().trim(),
                    Double.parseDouble(txtPrecioPorDia.getText().trim())
            );

            mostrarMensajeInformacion(
                    "La clase de vehículo fue modificada correctamente."
            );

            dispose();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    @Override
    protected void limpiarCampos() {

        cargarDatos();

    }

}