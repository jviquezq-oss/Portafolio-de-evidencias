package Interfaz;

import Entidades.*;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VentanaNuevaReserva extends VentanaFormulario {

    private final Controlador controlador;

    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Automovil> cmbAutomovil;
    private JComboBox<UsuarioVentas> cmbVendedor;

    private JComboBox<Integer> cmbDiaInicio;
    private JComboBox<String> cmbMesInicio;
    private JComboBox<Integer> cmbAnioInicio;

    private JComboBox<Integer> cmbDiaFinal;
    private JComboBox<String> cmbMesFinal;
    private JComboBox<Integer> cmbAnioFinal;

    public VentanaNuevaReserva(Controlador controlador) {

        super("Nueva Reserva");

        this.controlador = controlador;

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        cmbCliente = new JComboBox<>();

        cmbAutomovil = new JComboBox<>();

        cmbVendedor = new JComboBox<>();

        cmbDiaInicio = new JComboBox<>();
        cmbDiaFinal = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {

            cmbDiaInicio.addItem(i);
            cmbDiaFinal.addItem(i);

        }

        String[] meses = {
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
        };

        cmbMesInicio = new JComboBox<>(meses);
        cmbMesFinal = new JComboBox<>(meses);

        cmbAnioInicio = new JComboBox<>();
        cmbAnioFinal = new JComboBox<>();

        for (int i = LocalDate.now().getYear();
             i <= LocalDate.now().getYear() + 5;
             i++) {

            cmbAnioInicio.addItem(i);
            cmbAnioFinal.addItem(i);

        }

        JPanel panelInicio = new JPanel();

        panelInicio.add(cmbDiaInicio);
        panelInicio.add(cmbMesInicio);
        panelInicio.add(cmbAnioInicio);

        JPanel panelFinal = new JPanel();

        panelFinal.add(cmbDiaFinal);
        panelFinal.add(cmbMesFinal);
        panelFinal.add(cmbAnioFinal);

        agregarComponente(
                new JLabel("Cliente:"),
                cmbCliente,
                0,
                0
        );

        agregarComponente(
                new JLabel("Vehículo:"),
                cmbAutomovil,
                0,
                1
        );

        agregarComponente(
                new JLabel("Vendedor:"),
                cmbVendedor,
                1,
                0
        );

        agregarComponente(
                new JLabel("Fecha Inicio:"),
                panelInicio,
                1,
                1
        );

        agregarComponente(
                new JLabel("Fecha Final:"),
                panelFinal,
                2,
                0
        );

    }

    private void cargarDatos() {

        for (Cliente cliente : controlador.listarClientes()) {

            cmbCliente.addItem(cliente);

        }

        for (Automovil automovil : controlador.listarAutomoviles()) {

            if (automovil.getEstadoAutomovil() ==
                    EstadoAutomovil.DISPONIBLE) {

                cmbAutomovil.addItem(automovil);

            }

        }

        ArrayList<UsuarioDeSistema> usuarios =
                controlador.listarUsuarios();

        for (UsuarioDeSistema usuario : usuarios) {

            if (usuario instanceof UsuarioVentas) {

                cmbVendedor.addItem(
                        (UsuarioVentas) usuario
                );

            }

        }

    }

    @Override
    protected boolean validarCampos() {

        if (cmbCliente.getItemCount() == 0) {

            mostrarMensajeError(
                    "No existen clientes registrados."
            );

            return false;

        }

        if (cmbAutomovil.getItemCount() == 0) {

            mostrarMensajeError(
                    "No existen vehículos disponibles."
            );

            return false;

        }

        if (cmbVendedor.getItemCount() == 0) {

            mostrarMensajeError(
                    "No existen vendedores registrados."
            );

            return false;

        }

        try {

            LocalDate inicio = LocalDate.of(
                    (Integer) cmbAnioInicio.getSelectedItem(),
                    cmbMesInicio.getSelectedIndex() + 1,
                    (Integer) cmbDiaInicio.getSelectedItem()
            );

            LocalDate fin = LocalDate.of(
                    (Integer) cmbAnioFinal.getSelectedItem(),
                    cmbMesFinal.getSelectedIndex() + 1,
                    (Integer) cmbDiaFinal.getSelectedItem()
            );

            if (inicio.isBefore(LocalDate.now())) {

                mostrarMensajeError(
                        "La fecha de inicio no puede ser anterior a hoy."
                );

                return false;

            }

            if (fin.isBefore(inicio)) {

                mostrarMensajeError(
                        "La fecha final debe ser posterior a la fecha inicial."
                );

                return false;

            }

        } catch (DateTimeException e) {

            mostrarMensajeError(
                    "La fecha ingresada no es válida."
            );

            return false;

        }

        return true;

    }

    @Override
    protected void guardar() {

        LocalDate inicio = LocalDate.of(
                (Integer) cmbAnioInicio.getSelectedItem(),
                cmbMesInicio.getSelectedIndex() + 1,
                (Integer) cmbDiaInicio.getSelectedItem()
        );

        LocalDate fin = LocalDate.of(
                (Integer) cmbAnioFinal.getSelectedItem(),
                cmbMesFinal.getSelectedIndex() + 1,
                (Integer) cmbDiaFinal.getSelectedItem()
        );

        try {

            controlador.registrarReserva(
                    (Automovil) cmbAutomovil.getSelectedItem(),
                    (Cliente) cmbCliente.getSelectedItem(),
                    (UsuarioVentas) cmbVendedor.getSelectedItem(),
                    inicio,
                    fin
            );

            mostrarMensajeInformacion(
                    "La reserva fue registrada correctamente."
            );

            cerrarVentana();

        } catch (ExcepcionDeNegocio e) {

            mostrarMensajeError(
                    e.getMessage()
            );

        }

    }

    @Override
    protected void limpiarCampos() {

        cmbCliente.setSelectedIndex(0);
        cmbAutomovil.setSelectedIndex(0);
        cmbVendedor.setSelectedIndex(0);

    }

}