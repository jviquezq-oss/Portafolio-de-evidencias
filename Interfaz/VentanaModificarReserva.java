package Interfaz;

import Entidades.Automovil;
import Entidades.EstadoAutomovil;
import Entidades.Reserva;
import Excepciones.ExcepcionDeNegocio;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;

public class VentanaModificarReserva extends VentanaFormulario {

    private final Controlador controlador;
    private final Reserva reserva;

    private JTextField txtNumeroReserva;
    private JTextField txtCliente;
    private JTextField txtVendedor;

    private JComboBox<Automovil> cmbAutomovil;

    private JComboBox<Integer> cmbDiaInicio;
    private JComboBox<String> cmbMesInicio;
    private JComboBox<Integer> cmbAnioInicio;

    private JComboBox<Integer> cmbDiaFinal;
    private JComboBox<String> cmbMesFinal;
    private JComboBox<Integer> cmbAnioFinal;

    public VentanaModificarReserva(
            Controlador controlador,
            Reserva reserva) {

        super("Modificar Reserva");

        this.controlador = controlador;
        this.reserva = reserva;

        cargarDatos();

    }

    @Override
    protected void inicializarComponentes() {

        txtNumeroReserva = crearCampoSoloLectura();
        txtCliente = crearCampoSoloLectura();
        txtVendedor = crearCampoSoloLectura();

        cmbAutomovil = new JComboBox<>();

        cmbDiaInicio = new JComboBox<>();
        cmbDiaFinal = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {

            cmbDiaInicio.addItem(i);
            cmbDiaFinal.addItem(i);

        }

        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril",
                "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"
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
                new JLabel("Reserva:"),
                txtNumeroReserva,
                0,
                0
        );

        agregarComponente(
                new JLabel("Cliente:"),
                txtCliente,
                0,
                1
        );

        agregarComponente(
                new JLabel("Vendedor:"),
                txtVendedor,
                1,
                0
        );

        agregarComponente(
                new JLabel("Vehículo:"),
                cmbAutomovil,
                1,
                1
        );

        agregarComponente(
                new JLabel("Fecha Inicio:"),
                panelInicio,
                2,
                0
        );

        agregarComponente(
                new JLabel("Fecha Final:"),
                panelFinal,
                2,
                1
        );

    }

    private void cargarDatos() {

        txtNumeroReserva.setText(
                String.valueOf(reserva.getNumeroReserva())
        );

        txtCliente.setText(
                reserva.getCliente().toString()
        );

        txtVendedor.setText(
                reserva.getVendedor().toString()
        );

        cmbAutomovil.removeAllItems();

        for (Automovil automovil : controlador.listarAutomoviles()) {

            if (automovil.getEstadoAutomovil() == EstadoAutomovil.DISPONIBLE || automovil.getNumeroVin().equals(reserva.getAutomovil().getNumeroVin())) {
                cmbAutomovil.addItem(automovil);
            }

        }

        cmbAutomovil.setSelectedItem(reserva.getAutomovil());

        LocalDate inicio = reserva.getFechaInicio();

        cmbDiaInicio.setSelectedItem(inicio.getDayOfMonth());
        cmbMesInicio.setSelectedIndex(inicio.getMonthValue() - 1);
        cmbAnioInicio.setSelectedItem(inicio.getYear());

        LocalDate fin = reserva.getFechaFinalizacion();

        cmbDiaFinal.setSelectedItem(fin.getDayOfMonth());
        cmbMesFinal.setSelectedIndex(fin.getMonthValue() - 1);
        cmbAnioFinal.setSelectedItem(fin.getYear());

    }

    @Override
    protected boolean validarCampos() {

        if (cmbAutomovil.getSelectedItem() == null) {

            mostrarMensajeError(
                    "Debe seleccionar un vehículo."
            );

            return false;

        }

        try {

            LocalDate fechaInicio = LocalDate.of(
                    (Integer) cmbAnioInicio.getSelectedItem(),
                    cmbMesInicio.getSelectedIndex() + 1,
                    (Integer) cmbDiaInicio.getSelectedItem()
            );

            LocalDate fechaFinal = LocalDate.of(
                    (Integer) cmbAnioFinal.getSelectedItem(),
                    cmbMesFinal.getSelectedIndex() + 1,
                    (Integer) cmbDiaFinal.getSelectedItem()
            );

            if (fechaFinal.isBefore(fechaInicio)) {

                mostrarMensajeError(
                        "La fecha final debe ser posterior a la fecha de inicio."
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

        LocalDate fechaInicio = LocalDate.of(
                (Integer) cmbAnioInicio.getSelectedItem(),
                cmbMesInicio.getSelectedIndex() + 1,
                (Integer) cmbDiaInicio.getSelectedItem()
        );

        LocalDate fechaFinal = LocalDate.of(
                (Integer) cmbAnioFinal.getSelectedItem(),
                cmbMesFinal.getSelectedIndex() + 1,
                (Integer) cmbDiaFinal.getSelectedItem()
        );

        try {

            controlador.modificarReserva(
                    reserva,
                    (Automovil) cmbAutomovil.getSelectedItem(),
                    fechaInicio,
                    fechaFinal
            );

            mostrarMensajeInformacion(
                    "La reserva fue modificada correctamente."
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

        cargarDatos();

    }

}