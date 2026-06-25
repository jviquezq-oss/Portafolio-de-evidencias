package LogicaDeNegocio;

import Entidades.ClaseDeVehiculo;
import Interfaz.VentanaModificarClaseVehiculo;

import java.util.ArrayList;
import java.util.List;

public class AministradorClasesDeVehiculo {

    private static List<ClaseDeVehiculo> clasesDeVehiculo = new ArrayList<>();
    public static List<ClaseDeVehiculo> getClasesDeVehiculo(){
        return clasesDeVehiculo;
    }
    public static void agregarClaseVehiculo(){
        ClaseDeVehiculo nuevaClaseVehiculo = Interfaz.VentanaClaseVehiculo.solicitarClaseVehiculo();
        if(nuevaClaseVehiculo != null){
            clasesDeVehiculo.add(nuevaClaseVehiculo);
        }
    }
    public static void modificarClaseVehiculo(){
        VentanaModificarClaseVehiculo.modificarClaseVehiculo(clasesDeVehiculo);
    }

}
