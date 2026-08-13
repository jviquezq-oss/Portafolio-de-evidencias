package LogicaDeNegocio;

import Entidades.*;
import Excepciones.ExcepcionDeNegocio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controlador {

    private final AdministradorClientes administradorClientes;
    private final AdministradorAutomovil administradorAutomovil;
    private final AdministradorUsuarios administradorUsuarios;
    private final AministradorClasesDeVehiculo administradorClasesVehiculo;
    private final AdministradorReservas administradorReservas;
    private final AdministradorFacturas administradorFacturas;

    public Controlador() {

        administradorClientes = new AdministradorClientes();
        administradorAutomovil = new AdministradorAutomovil();
        administradorUsuarios = new AdministradorUsuarios();
        administradorClasesVehiculo = new AministradorClasesDeVehiculo();
        administradorReservas = new AdministradorReservas();
        administradorFacturas = new AdministradorFacturas();

    }

    public void registrarCliente(Cliente cliente) throws ExcepcionDeNegocio {

        administradorClientes.registrarCliente(cliente);

    }

    public ArrayList<Cliente> listarClientes() {

        return administradorClientes.listarClientes();

    }

    public Cliente buscarCliente(String identificacion) {

        return administradorClientes.buscarCliente(identificacion);

    }

    public void modificarCliente(Cliente cliente, String nombre, String primerApellido, String segundoApellido, String correoElectronico, String numeroTelefono) throws ExcepcionDeNegocio {
        administradorClientes.modificarCliente(cliente, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono);
    }

    public void eliminarCliente(Cliente cliente) throws ExcepcionDeNegocio {

        administradorClientes.eliminarCliente(cliente);

    }
    public void registrarClaseVehiculo(String nombre, String descripcion, double precioPorDia) throws ExcepcionDeNegocio {

        administradorClasesVehiculo.registrarClaseVehiculo(
                nombre,
                descripcion,
                precioPorDia);

    }

    public ArrayList<ClaseDeVehiculo> listarClasesVehiculo() {

        return administradorClasesVehiculo.listarClasesVehiculo();

    }

    public ClaseDeVehiculo buscarClaseVehiculo(int idClase) {

        return administradorClasesVehiculo.buscarClaseVehiculo(idClase);

    }

    public void modificarClaseVehiculo(ClaseDeVehiculo claseVehiculo, String nombre, String descripcion, double precioPorDia) throws ExcepcionDeNegocio {
        administradorClasesVehiculo.modificarClaseVehiculo(claseVehiculo, nombre, descripcion, precioPorDia);

    }

    public void eliminarClaseVehiculo(ClaseDeVehiculo claseVehiculo) throws ExcepcionDeNegocio {

        administradorClasesVehiculo.eliminarClaseVehiculo(claseVehiculo);

    }

    public void registrarAutomovil(String modelo, int año, String numeroVin, String numeroPlaca, Combustibles combustible, TipoVehiculo tipoVehiculo, ClaseDeVehiculo claseVehiculo, Marca marca) throws ExcepcionDeNegocio {
        administradorAutomovil.registrarAutomovil(modelo, año, numeroVin, numeroPlaca, combustible, tipoVehiculo, claseVehiculo, marca);
    }

    public ArrayList<Automovil> listarAutomoviles() {

        return administradorAutomovil.listarAutomoviles();

    }

    public Automovil buscarAutomovil(String numeroVin) {

        return administradorAutomovil.buscarAutomovil(numeroVin);

    }

    public void modificarAutomovil(Automovil automovil, Marca marca, TipoVehiculo tipoVehiculo, ClaseDeVehiculo claseVehiculo) throws ExcepcionDeNegocio {
        administradorAutomovil.modificarAutomovil(automovil, marca, tipoVehiculo, claseVehiculo);
    }

    public void eliminarAutomovil(Automovil automovil) throws ExcepcionDeNegocio {

        administradorAutomovil.eliminarAutomovil(automovil);

    }
    public void registrarUsuario(UsuarioDeSistema usuario) throws ExcepcionDeNegocio {

        administradorUsuarios.registrarUsuario(usuario);

    }

    public ArrayList<UsuarioDeSistema> listarUsuarios() {

        return administradorUsuarios.listarUsuarios();

    }

    public UsuarioDeSistema buscarUsuario(int idUsuario) {

        return administradorUsuarios.buscarUsuario(idUsuario);

    }

    public void modificarUsuario(UsuarioDeSistema usuario, String nombre, String apellidos, String correoElectronico, String contrasena) throws ExcepcionDeNegocio {

        administradorUsuarios.modificarUsuario(usuario, nombre, apellidos, correoElectronico, contrasena);

    }

    public void eliminarUsuario(UsuarioDeSistema usuario) throws ExcepcionDeNegocio {

        administradorUsuarios.eliminarUsuario(usuario);

    }

    public UsuarioDeSistema autenticarUsuario(String correoElectronico, String contrasena) throws ExcepcionDeNegocio {

        return administradorUsuarios.autenticarUsuario(correoElectronico, contrasena);

    }

    public void registrarReserva(Automovil automovil, Cliente cliente, UsuarioDeSistema vendedor, LocalDate fechaInicio, LocalDate fechaFinalizacion) throws ExcepcionDeNegocio {

        administradorReservas.registrarReserva(automovil, cliente, vendedor, fechaInicio, fechaFinalizacion);

    }

    public ArrayList<Reserva> listarReservas() {

        return administradorReservas.listarReservas();

    }

    public Reserva buscarReserva(int numeroReserva) {

        return administradorReservas.buscarReserva(numeroReserva);

    }

    public void modificarReserva(Reserva reserva, Automovil nuevoAutomovil, LocalDate fechaInicio, LocalDate fechaFinal) throws ExcepcionDeNegocio {

        administradorReservas.modificarReserva(reserva, nuevoAutomovil, fechaInicio, fechaFinal);

    }

    public void eliminarReserva(Reserva reserva) throws ExcepcionDeNegocio {

        administradorReservas.eliminarReserva(reserva);

    }

    public void finalizarReserva(Reserva reserva) throws ExcepcionDeNegocio {

        administradorReservas.finalizarReserva(reserva);

    }
    public void generarFactura(Reserva reserva) throws ExcepcionDeNegocio {

        administradorFacturas.generarFactura(reserva);

    }

    public ArrayList<Factura> listarFacturas() {

        return administradorFacturas.listarFacturas();

    }

    public Factura buscarFactura(int numeroFactura) {

        return administradorFacturas.buscarFactura(numeroFactura);

    }

    public void eliminarFactura(Factura factura) throws ExcepcionDeNegocio {

        administradorFacturas.eliminarFactura(factura);

    }

}