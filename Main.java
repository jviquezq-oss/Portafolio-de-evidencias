
import Interfaz.VentanaLogin;
import LogicaDeNegocio.*;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Controlador controlador = new Controlador();

            new VentanaLogin(controlador);

        });

    }

}