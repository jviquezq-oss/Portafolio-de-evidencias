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
    public void registrarClaseVehiculo(String nombre, String descripcion, double precioPorDia){
        ClaseDeVehiculo nuevaClase = new ClaseDeVehiculo(nombre, descripcion, precioPorDia);
        clasesDeVehiculo.add(nuevaClase);

    }
    public static void modificarClaseVehiculo(){
        VentanaModificarClaseVehiculo.modificarClaseVehiculo(clasesDeVehiculo);
    }

}
