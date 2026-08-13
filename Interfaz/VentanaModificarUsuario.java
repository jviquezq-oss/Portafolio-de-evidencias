package Interfaz;

import Entidades.UsuarioVentas;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.util.regex.Pattern;

public class VentanaModificarUsuario extends VentanaFormulario {

    private final Controlador controlador;
    private final UsuarioVentas usuario;

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreoElectronico;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;

    public VentanaModificarUsuario(Controlador controlador,
                                   UsuarioVentas usuario) {

        super("Modificar Vendedor");

        this.controlador = controlador;
        this.usuario = usuario;

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        txtNombre = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtCorreoElectronico = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        txtConfirmarContrasena = new JPasswordField(20);

        agregarComponente(
                new JLabel("Nombre:"),
                txtNombre,
                0,
                0
        );

        agregarComponente(
                new JLabel("Apellidos:"),
                txtApellidos,
                0,
                1
        );

        agregarComponente(
                new JLabel("Correo Electrónico:"),
                txtCorreoElectronico,
                1,
                0
        );

        agregarComponente(
                new JLabel("Contraseña:"),
                txtContrasena,
                1,
                1
        );

        agregarComponente(
                new JLabel("Confirmar Contraseña:"),
                txtConfirmarContrasena,
                2,
                0
        );

    }

    private void cargarDatos() {

        txtNombre.setText(usuario.getNombre());
        txtApellidos.setText(usuario.getApellidos());
        txtCorreoElectronico.setText(usuario.getCorreoElectronico());
        txtContrasena.setText(usuario.getContrasena());
        txtConfirmarContrasena.setText(usuario.getContrasena());

    }

    @Override
    protected boolean validarCampos() {

        StringBuilder errores = new StringBuilder();

        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreoElectronico.getText().trim();

        String contrasena = new String(txtContrasena.getPassword());
        String confirmar = new String(txtConfirmarContrasena.getPassword());

        if (nombre.isBlank()) {
            errores.append("- Debe ingresar el nombre.\n");
        }

        if (apellidos.isBlank()) {
            errores.append("- Debe ingresar los apellidos.\n");
        }

        if (correo.isBlank()) {

            errores.append("- Debe ingresar el correo electrónico.\n");

        } else {

            Pattern patronCorreo =
                    Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

            if (!patronCorreo.matcher(correo).matches()) {
                errores.append("- El correo electrónico no es válido.\n");
            }

        }

        if (contrasena.isBlank()) {
            errores.append("- Debe ingresar una contraseña.\n");
        }

        if (!contrasena.equals(confirmar)) {
            errores.append("- Las contraseñas no coinciden.\n");
        }

        if (contrasena.length() < 6) {
            errores.append("- La contraseña debe tener al menos 6 caracteres.\n");
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

            controlador.modificarUsuario(
                    usuario,
                    txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    txtCorreoElectronico.getText().trim(),
                    new String(txtContrasena.getPassword())
            );

            mostrarMensajeInformacion(
                    "El vendedor fue modificado correctamente."
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