package Interfaz;

import Entidades.Cliente;
import Entidades.Nacionalidad;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;

public class VentanaNuevoCliente extends VentanaFormulario {

    private final Controlador controlador;

    private JTextField txtNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JTextField txtIdentificacion;
    private JTextField txtCorreoElectronico;
    private JTextField txtNumeroTelefono;

    private JComboBox<Nacionalidad> cmbNacionalidad;

    private JComboBox<Integer> cmbDia;
    private JComboBox<String> cmbMes;
    private JComboBox<Integer> cmbAnio;

    public VentanaNuevoCliente(Controlador controlador) {

        super("Nuevo Cliente");

        this.controlador = controlador;

    }

    @Override
    protected void inicializarComponentes() {

        txtNombre = new JTextField(20);
        txtPrimerApellido = new JTextField(20);
        txtSegundoApellido = new JTextField(20);
        txtIdentificacion = new JTextField(20);
        txtCorreoElectronico = new JTextField(20);
        txtNumeroTelefono = new JTextField(20);

        cmbNacionalidad = new JComboBox<>(Nacionalidad.values());

        cmbDia = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            cmbDia.addItem(i);
        }

        cmbMes = new JComboBox<>(new String[]{
                "Enero",
                "Febrero",
                "Marzo",
                "Abril",
                "Mayo",
                "Junio",
                "Julio",
                "Agosto",
                "Septiembre",
                "Octubre",
                "Noviembre",
                "Diciembre"
        });

        cmbAnio = new JComboBox<>();

        for (int i = LocalDate.now().getYear(); i >= 1900; i--) {
            cmbAnio.addItem(i);
        }

        JPanel panelFecha = new JPanel();

        panelFecha.add(cmbDia);
        panelFecha.add(cmbMes);
        panelFecha.add(cmbAnio);

        agregarComponente(new JLabel("Nombre:"), txtNombre, 0, 0);
        agregarComponente(new JLabel("Primer Apellido:"), txtPrimerApellido, 0, 1);

        agregarComponente(new JLabel("Segundo Apellido:"), txtSegundoApellido, 1, 0);
        agregarComponente(new JLabel("Identificación:"), txtIdentificacion, 1, 1);

        agregarComponente(new JLabel("Nacionalidad:"), cmbNacionalidad, 2, 0);
        agregarComponente(new JLabel("Fecha de Nacimiento:"), panelFecha, 2, 1);

        agregarComponente(new JLabel("Correo Electrónico:"), txtCorreoElectronico, 3, 0);
        agregarComponente(new JLabel("Número Telefónico:"), txtNumeroTelefono, 3, 1);

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

        if (txtIdentificacion.getText().trim().isBlank()) {
            errores.append("- Debe ingresar la identificación.\n");
        } else if (!txtIdentificacion.getText().trim().matches("\\d+")) {
            errores.append("- La identificación solo puede contener números.\n");
        }

        if (txtCorreoElectronico.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el correo electrónico.\n");
        } else if (!txtCorreoElectronico.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errores.append("- El correo electrónico no es válido.\n");
        }

        if (txtNumeroTelefono.getText().trim().isBlank()) {
            errores.append("- Debe ingresar el número telefónico.\n");
        } else if (!txtNumeroTelefono.getText().trim().matches("\\d{8,15}")) {
            errores.append("- El número telefónico es inválido.\n");
        }

        try {

            LocalDate.of(
                    (Integer) cmbAnio.getSelectedItem(),
                    cmbMes.getSelectedIndex() + 1,
                    (Integer) cmbDia.getSelectedItem()
            );

        } catch (DateTimeException e) {

            errores.append("- La fecha de nacimiento es inválida.\n");

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

            LocalDate fechaNacimiento = LocalDate.of(
                    (Integer) cmbAnio.getSelectedItem(),
                    cmbMes.getSelectedIndex() + 1,
                    (Integer) cmbDia.getSelectedItem()
            );

            Cliente nuevoCliente = new Cliente(
                    txtIdentificacion.getText().trim(),
                    (Nacionalidad) cmbNacionalidad.getSelectedItem(),
                    fechaNacimiento,
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtCorreoElectronico.getText().trim(),
                    txtNumeroTelefono.getText().trim()
            );

            controlador.registrarCliente(nuevoCliente);

            mostrarMensajeInformacion(
                    "El cliente fue registrado correctamente."
            );

            cerrarVentana();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    @Override
    protected void limpiarCampos() {

        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtIdentificacion.setText("");
        txtCorreoElectronico.setText("");
        txtNumeroTelefono.setText("");

        cmbNacionalidad.setSelectedIndex(0);
        cmbDia.setSelectedIndex(0);
        cmbMes.setSelectedIndex(0);
        cmbAnio.setSelectedIndex(0);

    }

}