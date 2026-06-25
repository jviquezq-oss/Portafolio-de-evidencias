package LogicaDeNegocio;

import Entidades.Automovil;
import Interfaz.VentanaAutomovil;
import Interfaz.VentanaModificarAutomovil;

import java.util.ArrayList;
import java.util.List;

public class AdministradorAutomovil {
    private static List<Automovil>automovilesDisponibles = new ArrayList<>();

    public static List<Automovil>getAutomovilesDisponibles(){
        return getAutomovilesDisponibles();
    }
    public static void registrarVehiculo(){
        Automovil nuevoAutomovil = VentanaAutomovil.solicitarAutomovil(AministradorClasesDeVehiculo.getClasesDeVehiculo());
        if(nuevoAutomovil != null){
            automovilesDisponibles.add(nuevoAutomovil);
        }
    }
    public static void modificarAutomovil(){
        VentanaModificarAutomovil.modificarAutomovil(automovilesDisponibles,AministradorClasesDeVehiculo.getClasesDeVehiculo());
    }
}
