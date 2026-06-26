package Interfaz;

import LogicaDeNegocio.AministradorClasesDeVehiculo;

import javax.swing.*;

public class VentanaClaseVehiculo extends VentanaFormulario {

    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtPrecioPorDia;

    private AministradorClasesDeVehiculo administrador;

    public VentanaClaseVehiculo(AministradorClasesDeVehiculo administrador) {

        super("Nueva Clase de Vehículo");

        this.administrador = administrador;

    }


    @Override
    protected boolean validarCampos() {

        StringBuilder errores = new StringBuilder();

        if(txtNombre.getText().trim().isEmpty()){
            errores.append("- Debe ingresar el nombre.\n");
        }

        if(txtDescripcion.getText().trim().isEmpty()){
            errores.append("- Debe ingresar una descripción.\n");
        }

        if(txtPrecioPorDia.getText().trim().isEmpty()){

            errores.append("- Debe ingresar el precio por día.\n");

        }else{

            try{

                double precio = Double.parseDouble(txtPrecioPorDia.getText().trim());

                if(precio<=0){
                    errores.append("- El precio por día debe ser mayor que cero.\n");
                }

            }catch(NumberFormatException e){

                errores.append("- El precio por día debe ser numérico.\n");

            }

        }

        if(!errores.isEmpty()){

            mostrarMensajeError(errores.toString());

            return false;

        }

        return true;

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

    @Override
    protected void guardar() {

        administrador.registrarClaseVehiculo(
                txtNombre.getText().trim(),
                txtDescripcion.getText().trim(),
                Double.parseDouble(txtPrecioPorDia.getText().trim()));

        mostrarMensajeInformacion(
                "La clase de vehículo fue registrada exitosamente."
        );

        limpiarCampos();

    }

    @Override
    protected void limpiarCampos() {

        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecioPorDia.setText("");

        txtNombre.requestFocus();

    }

}