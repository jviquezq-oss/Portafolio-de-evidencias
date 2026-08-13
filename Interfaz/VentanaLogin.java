package Interfaz;

import Entidades.UsuarioDeSistema;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;
import Menu.MenuPrincipal;

import javax.swing.*;

public class VentanaLogin extends VentanaFormulario {

    private final Controlador controlador;

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public VentanaLogin(Controlador controlador) {

        super("Inicio de Sesión");

        this.controlador = controlador;

    }

    @Override
    protected void inicializarComponentes() {

        txtCorreo = new JTextField(20);
        txtContrasena = new JPasswordField(20);

        agregarComponente(
                new JLabel("Correo Electrónico:"),
                txtCorreo,
                0,
                0
        );

        agregarComponente(
                new JLabel("Contraseña:"),
                txtContrasena,
                1,
                0
        );

    }

    @Override
    protected boolean validarCampos() {

        if (txtCorreo.getText().trim().isBlank()) {

            mostrarMensajeError("Debe ingresar el correo electrónico.");

            return false;

        }

        if (new String(txtContrasena.getPassword()).isBlank()) {

            mostrarMensajeError("Debe ingresar la contraseña.");

            return false;

        }

        return true;

    }

    @Override
    protected void guardar() {

        try {

            controlador.autenticarUsuario(
                    txtCorreo.getText().trim(),
                    new String(txtContrasena.getPassword())
            );

            new MenuPrincipal(controlador);

            cerrarVentana();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(e.getMessage());

        }

    }

    @Override
    protected void limpiarCampos() {

        txtCorreo.setText("");
        txtContrasena.setText("");

    }

}