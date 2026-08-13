package Menu;

import Interfaz.*;
import LogicaDeNegocio.Controlador;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private final Controlador controlador;

    private JButton btnNuevaClaseVehiculo;
    private JButton btnAdministrarClasesVehiculo;

    private JButton btnNuevoVehiculo;
    private JButton btnAdministrarVehiculos;

    private JButton btnNuevoCliente;
    private JButton btnAdministrarClientes;

    private JButton btnNuevaReserva;
    private JButton btnAdministrarReservas;

    private JButton btnNuevoUsuario;
    private JButton btnAdministrarUsuarios;

    private JButton btnAdministrarFacturas;

    private JButton btnSalir;

    public MenuPrincipal(Controlador controlador) {

        this.controlador = controlador;

        inicializarComponentes();

        registrarEventos();

        pack();

        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void inicializarComponentes() {

        setTitle("Sistema Rent A Car");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(
                new BoxLayout(
                        panelPrincipal,
                        BoxLayout.Y_AXIS
                )
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel lblTitulo =
                new JLabel("SISTEMA RENT A CAR");

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblTitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        panelPrincipal.add(lblTitulo);

        panelPrincipal.add(
                Box.createVerticalStrut(25)
        );

        JLabel lblClases =
                new JLabel("CLASES DE VEHÍCULO");

        lblClases.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblClases.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblClases);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnNuevaClaseVehiculo =
                new JButton(
                        "Registrar Clase de Vehículo"
                );

        btnAdministrarClasesVehiculo =
                new JButton(
                        "Administrar Clases de Vehículo"
                );

        btnNuevaClaseVehiculo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnAdministrarClasesVehiculo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnNuevaClaseVehiculo);

        panelPrincipal.add(
                Box.createVerticalStrut(5)
        );

        panelPrincipal.add(
                btnAdministrarClasesVehiculo
        );

        panelPrincipal.add(
                Box.createVerticalStrut(20)
        );

        JLabel lblVehiculos =
                new JLabel("VEHÍCULOS");

        lblVehiculos.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblVehiculos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblVehiculos);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnNuevoVehiculo =
                new JButton("Registrar Vehículo");

        btnAdministrarVehiculos =
                new JButton("Administrar Vehículos");

        btnNuevoVehiculo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnAdministrarVehiculos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnNuevoVehiculo);

        panelPrincipal.add(
                Box.createVerticalStrut(5)
        );

        panelPrincipal.add(btnAdministrarVehiculos);

        panelPrincipal.add(
                Box.createVerticalStrut(20)
        );

        JLabel lblClientes =
                new JLabel("CLIENTES");

        lblClientes.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblClientes.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblClientes);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnNuevoCliente =
                new JButton("Registrar Cliente");

        btnAdministrarClientes =
                new JButton("Administrar Clientes");

        btnNuevoCliente.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnAdministrarClientes.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnNuevoCliente);

        panelPrincipal.add(
                Box.createVerticalStrut(5)
        );

        panelPrincipal.add(btnAdministrarClientes);

        panelPrincipal.add(
                Box.createVerticalStrut(25)
        );

        JLabel lblReservas =
                new JLabel("RESERVAS");

        lblReservas.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblReservas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblReservas);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnNuevaReserva =
                new JButton("Registrar Reserva");

        btnAdministrarReservas =
                new JButton("Administrar Reservas");

        btnNuevaReserva.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnAdministrarReservas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnNuevaReserva);

        panelPrincipal.add(
                Box.createVerticalStrut(5)
        );

        panelPrincipal.add(btnAdministrarReservas);

        panelPrincipal.add(
                Box.createVerticalStrut(20)
        );

        JLabel lblUsuarios =
                new JLabel("USUARIOS");

        lblUsuarios.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblUsuarios.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblUsuarios);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnNuevoUsuario =
                new JButton("Registrar Vendedor");

        btnAdministrarUsuarios =
                new JButton("Administrar Vendedores");

        btnNuevoUsuario.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnAdministrarUsuarios.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnNuevoUsuario);

        panelPrincipal.add(
                Box.createVerticalStrut(5)
        );

        panelPrincipal.add(btnAdministrarUsuarios);

        panelPrincipal.add(
                Box.createVerticalStrut(20)
        );

        JLabel lblFacturas =
                new JLabel("FACTURACIÓN");

        lblFacturas.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        lblFacturas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(lblFacturas);

        panelPrincipal.add(
                Box.createVerticalStrut(10)
        );

        btnAdministrarFacturas =
                new JButton("Administrar Facturas");

        btnAdministrarFacturas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnAdministrarFacturas);

        panelPrincipal.add(
                Box.createVerticalStrut(20)
        );

        btnSalir = new JButton("Salir");

        btnSalir.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelPrincipal.add(btnSalir);

        setContentPane(panelPrincipal);

    }

    private void registrarEventos() {

        btnNuevaClaseVehiculo.addActionListener(e ->
                new VentanaClaseVehiculo(
                        controlador
                )
        );

        btnAdministrarClasesVehiculo.addActionListener(e ->
                new VentanaClasesVehiculo(
                        controlador
                )
        );

        btnNuevoVehiculo.addActionListener(e ->
                new VentanaAutomovil(
                        controlador
                )
        );

        btnAdministrarVehiculos.addActionListener(e ->
                new VentanaAdministrarVehiculos(
                        controlador
                )
        );

        btnNuevoCliente.addActionListener(e ->
                new VentanaNuevoCliente(
                        controlador
                )
        );

        btnAdministrarClientes.addActionListener(e ->
                new VentanaAdministrarClientes(
                        controlador
                )
        );

        btnNuevaReserva.addActionListener(e ->
                new VentanaNuevaReserva(
                        controlador
                )
        );

        btnAdministrarReservas.addActionListener(e ->
                new VentanaVerReservas(
                        controlador
                )
        );

        btnNuevoUsuario.addActionListener(e ->
                new VentanaNuevoUsuario(
                        controlador
                )
        );

        btnAdministrarUsuarios.addActionListener(e ->
                new VentanaAdministrarUsuarios(
                        controlador
                )
        );

        btnAdministrarFacturas.addActionListener(e ->
                new VentanaVerFacturas(
                        controlador
                )
        );

        btnSalir.addActionListener(e ->
                dispose()
        );

    }

}