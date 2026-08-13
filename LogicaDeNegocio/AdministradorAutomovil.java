package LogicaDeNegocio;

import DAO.AutomovilDAO;
import DAO.ClaseVehiculoDAO;
import Entidades.Automovil;
import Entidades.ClaseDeVehiculo;
import Entidades.Combustibles;
import Entidades.EstadoAutomovil;
import Entidades.Marca;
import Entidades.TipoVehiculo;
import Excepciones.ExcepcionDeNegocio;

import java.util.ArrayList;

public class AdministradorAutomovil {

    private final AutomovilDAO automovilDAO;
    private final ClaseVehiculoDAO claseVehiculoDAO;

    public AdministradorAutomovil() {

        automovilDAO = new AutomovilDAO();
        claseVehiculoDAO = new ClaseVehiculoDAO();

    }

    public void registrarAutomovil(String modelo, int anio, String numeroVin, String numeroPlaca, Combustibles combustible, TipoVehiculo tipoVehiculo, ClaseDeVehiculo claseVehiculo, Marca marca) throws ExcepcionDeNegocio {

        if (automovilDAO.buscar(numeroVin) != null) {
            throw new ExcepcionDeNegocio("Ya existe un vehículo registrado con ese número VIN.");
        }

        if (automovilDAO.buscarPorPlaca(numeroPlaca) != null) {
            throw new ExcepcionDeNegocio("Ya existe un vehículo registrado con ese número de placa.");
        }

        if (claseVehiculo == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar una clase de vehículo.");
        }

        if (claseVehiculoDAO.buscar(claseVehiculo.gerIdClase()) == null) {
            throw new ExcepcionDeNegocio("La clase de vehículo no existe.");
        }

        Automovil nuevoAutomovil =
                new Automovil(
                        modelo,
                        anio,
                        numeroVin,
                        numeroPlaca,
                        combustible,
                        tipoVehiculo,
                        claseVehiculo,
                        marca);

        if (!automovilDAO.registrar(nuevoAutomovil)) {
            throw new ExcepcionDeNegocio("No fue posible registrar el vehículo.");
        }

    }

    public ArrayList<Automovil> listarAutomoviles() {

        return automovilDAO.listar();

    }

    public Automovil buscarAutomovil(String numeroVin) {

        return automovilDAO.buscar(numeroVin);

    }

    public void modificarAutomovil(Automovil automovil, Marca marca, TipoVehiculo tipoVehiculo, ClaseDeVehiculo claseVehiculo) throws ExcepcionDeNegocio {

        if (automovil == null) {
            throw new ExcepcionDeNegocio("El vehículo no existe.");
        }

        if (marca == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar una marca.");
        }

        if (tipoVehiculo == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar un tipo de vehículo.");
        }

        if (claseVehiculo == null) {
            throw new ExcepcionDeNegocio("Debe seleccionar una clase de vehículo.");
        }

        if (claseVehiculoDAO.buscar(claseVehiculo.gerIdClase()) == null) {
            throw new ExcepcionDeNegocio("La clase de vehículo no existe.");
        }

        automovil.setMarca(marca);
        automovil.setTipoDeVehiculo(tipoVehiculo);
        automovil.setClaseDeVehiculo(claseVehiculo);

        if (!automovilDAO.modificar(automovil)) {
            throw new ExcepcionDeNegocio("No fue posible modificar el vehículo.");
        }

    }

    public void eliminarAutomovil(Automovil automovil) throws ExcepcionDeNegocio {

        if (automovil == null) {
            throw new ExcepcionDeNegocio("El vehículo no existe.");
        }

        if (automovil.getEstadoAutomovil() == EstadoAutomovil.ALQUILADO) {
            throw new ExcepcionDeNegocio("No es posible eliminar un vehículo que actualmente se encuentra alquilado.");
        }

        if (automovil.getEstadoAutomovil() == EstadoAutomovil.RESERVADO) {
            throw new ExcepcionDeNegocio("No es posible eliminar un vehículo que actualmente se encuentra reservado.");
        }

        if (!automovilDAO.eliminar(automovil.getNumeroVin())) {
            throw new ExcepcionDeNegocio("No fue posible eliminar el vehículo.");
        }

    }

}