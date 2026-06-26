package LogicaDeNegocio;

import Entidades.Automovil;
import Entidades.*;
import Interfaz.VentanaAutomovil;
import Interfaz.VentanaEliminarAutomovil;
import Interfaz.VentanaModificarAutomovil;
import Excepciones.ExcepcionDeNegocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministradorAutomovil {
    private static List<Automovil>automovilesDisponibles = new ArrayList<>();

    public static List<Automovil> getAutomovilesDisponibles(){
        return automovilesDisponibles;
    }

    public void registrarAutomovil(String modelo, int anio, String numeroVin, String numeroPlaca, Combustibles combustible, TipoVehiculo tipoVehiculo, ClaseDeVehiculo claseVehiculo, Marca marca) throws ExcepcionDeNegocio {
        for (Automovil automovil : automovilesDisponibles) {

            if (automovil.getNumberoVin().equalsIgnoreCase(numeroVin)) {
                throw new ExcepcionDeNegocio("Ya existe un vehículo registrado con ese número VIN.");
            }

            if (automovil.getNumeroPlaca().equalsIgnoreCase(numeroPlaca)) {
                throw new ExcepcionDeNegocio("Ya existe un vehículo registrado con ese número de placa.");
            }

        }

        Automovil nuevoAutomovil = new Automovil(modelo, LocalDate.of(anio, 1, 1), numeroVin, numeroPlaca, combustible, tipoVehiculo, claseVehiculo, marca);

        automovilesDisponibles.add(nuevoAutomovil);

    }

    public  void modificarAutomovil(){
        VentanaModificarAutomovil.modificarAutomovil(automovilesDisponibles,AministradorClasesDeVehiculo.getClasesDeVehiculo());
    }
    public  void eliminarAutomovil(){
        new VentanaEliminarAutomovil(automovilesDisponibles);
    }
}
