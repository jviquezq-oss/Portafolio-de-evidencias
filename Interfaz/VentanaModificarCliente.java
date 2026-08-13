package Interfaz;

import Entidades.Cliente;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;

public class VentanaModificarCliente extends VentanaFormulario {

    private final Controlador controlador;
    private final Cliente cliente;

    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JTextField txtCorreoElectronico;
    private JTextField txtNumeroTelefono;

    private JTextField txtNacionalidad;
    private JTextField txtFechaNacimiento;

    public VentanaModificarCliente(Controlador controlador,
                                   Cliente cliente) {

        super("Modificar Cliente");

        this.controlador = controlador;
        this.cliente = cliente;

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        txtIdentificacion = new JTextField(20);
        txtNombre = new JTextField(20);
        txtPrimerApellido = new JTextField(20);
        txtSegundoApellido = new JTextField(20);
        txtCorreoElectronico = new JTextField(20);
        txtNumeroTelefono = new JTextField(20);

        txtNacionalidad = new JTextField(20);
        txtFechaNacimiento = new JTextField(20);

        txtIdentificacion.setEditable(false);
        txtNacionalidad.setEditable(false);
        txtFechaNacimiento.setEditable(false);

        agregarComponente(new JLabel("Nombre:"), txtNombre, 0, 0);
        agregarComponente(new JLabel("Primer Apellido:"), txtPrimerApellido, 0, 1);

        agregarComponente(new JLabel("Segundo Apellido:"), txtSegundoApellido, 1, 0);
        agregarComponente(new JLabel("Identificación:"), txtIdentificacion, 1, 1);

        agregarComponente(new JLabel("Correo Electrónico:"), txtCorreoElectronico, 2, 0);
        agregarComponente(new JLabel("Número Telefónico:"), txtNumeroTelefono, 2, 1);

        agregarComponente(new JLabel("Nacionalidad:"), txtNacionalidad, 3, 0);
        agregarComponente(new JLabel("Fecha Nacimiento:"), txtFechaNacimiento, 3, 1);

    }

    private void cargarDatos() {

        txtIdentificacion.setText(cliente.getIdentificacion());
        txtNombre.setText(cliente.getNombre());
        txtPrimerApellido.setText(cliente.getPrimerApellido());
        txtSegundoApellido.setText(cliente.getSegundoApellido());
        txtCorreoElectronico.setText(cliente.getCorreoElectronico());
        txtNumeroTelefono.setText(cliente.getNumeroTelefono());

        txtNacionalidad.setText(cliente.getNacionalidad().toString());
        txtFechaNacimiento.setText(cliente.getFechaDeNacimiento().toString());

    }

    @Override
    protected boolean validarCampos() {

        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el nombre.\n");
        } else if (!txtNombre.getText().trim().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            errores.append("- El nombre solo puede contener letras.\n");
        }

        if (txtPrimerApellido.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el primer apellido.\n");
        } else if (!txtPrimerApellido.getText().trim().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            errores.append("- El primer apellido solo puede contener letras.\n");
        }

        if (txtSegundoApellido.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el segundo apellido.\n");
        } else if (!txtSegundoApellido.getText().trim().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            errores.append("- El segundo apellido solo puede contener letras.\n");
        }

        if (txtCorreoElectronico.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el correo electrónico.\n");
        } else if (!txtCorreoElectronico.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errores.append("- El correo electrónico no es válido.\n");
        }

        if (txtNumeroTelefono.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el número telefónico.\n");
        } else if (!txtNumeroTelefono.getText().trim().matches("\\d{8,15}")) {
            errores.append("- El número telefónico solo puede contener números.\n");
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

            controlador.modificarCliente(
                    cliente,
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtCorreoElectronico.getText().trim(),
                    txtNumeroTelefono.getText().trim()
            );

            mostrarMensajeInformacion(
                    "El cliente fue modificado correctamente."
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