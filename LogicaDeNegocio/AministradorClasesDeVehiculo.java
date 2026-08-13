package LogicaDeNegocio;

import DAO.ClaseVehiculoDAO;
import Entidades.ClaseDeVehiculo;
import Excepciones.ExcepcionDeNegocio;

import java.util.ArrayList;

public class AministradorClasesDeVehiculo {

    private final ClaseVehiculoDAO claseVehiculoDAO;

    public AministradorClasesDeVehiculo() {

        claseVehiculoDAO = new ClaseVehiculoDAO();

    }

    public void registrarClaseVehiculo(String nombre, String descripcion, double precioPorDia) throws ExcepcionDeNegocio {
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionDeNegocio("Debe ingresar el nombre.");
        }

        if (precioPorDia <= 0) {
            throw new ExcepcionDeNegocio("El precio por día debe ser mayor que cero.");
        }

        ClaseDeVehiculo nuevaClase =
                new ClaseDeVehiculo(
                        nombre,
                        descripcion,
                        precioPorDia);

        if (!claseVehiculoDAO.registrar(nuevaClase)) {
            throw new ExcepcionDeNegocio("No fue posible registrar la clase de vehículo.");
        }

    }

    public ArrayList<ClaseDeVehiculo> listarClasesVehiculo() {

        return claseVehiculoDAO.listar();

    }

    public ClaseDeVehiculo buscarClaseVehiculo(int idClase) {

        return claseVehiculoDAO.buscar(idClase);

    }

    public void modificarClaseVehiculo(ClaseDeVehiculo claseVehiculo, String nombre, String descripcion, double precioPorDia) throws ExcepcionDeNegocio {
        if (claseVehiculo == null) {
            throw new ExcepcionDeNegocio("La clase de vehículo no existe.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionDeNegocio("Debe ingresar el nombre.");
        }

        if (precioPorDia <= 0) {
            throw new ExcepcionDeNegocio("El precio por día debe ser mayor que cero.");
        }

        claseVehiculo.setNombre(nombre);
        claseVehiculo.setDescripcion(descripcion);
        claseVehiculo.setPrecioPorDia(precioPorDia);

        if (!claseVehiculoDAO.modificar(claseVehiculo)) {
            throw new ExcepcionDeNegocio("No fue posible modificar la clase de vehículo.");
        }

    }

    public void eliminarClaseVehiculo(ClaseDeVehiculo clase) throws ExcepcionDeNegocio {
        if (clase == null) {
            throw new ExcepcionDeNegocio("La clase de vehículo no existe.");
        }

        if (!claseVehiculoDAO.eliminar(clase.gerIdClase())) {
            throw new ExcepcionDeNegocio("No fue posible eliminar la clase de vehículo.");
        }

    }

}