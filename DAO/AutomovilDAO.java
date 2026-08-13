package DAO;

import Entidades.Automovil;
import Entidades.ClaseDeVehiculo;
import Entidades.Combustibles;
import Entidades.EstadoAutomovil;
import Entidades.Marca;
import Entidades.TipoVehiculo;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AutomovilDAO {

    public boolean registrar(Automovil automovil) {

        try {

            String query = String.format(
                    "INSERT INTO AUTOMOVIL(vin, placa, modelo, anio, marca, tipo, combustible, estado, id_clase) " +
                            "VALUES('%s','%s','%s',%d,'%s','%s','%s','%s',%d)",
                    automovil.getNumeroVin(),
                    automovil.getNumeroPlaca(),
                    automovil.getModelo(),
                    automovil.getAnio(),
                    automovil.getMarca().name(),
                    automovil.getTipoDeVehiculo().name(),
                    automovil.getCombustible().name(),
                    automovil.getEstadoAutomovil().name(),
                    automovil.getClaseDeVehiculo().gerIdClase());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public Automovil buscar(String numeroVin) {

        try {

            String query = "SELECT * FROM AUTOMOVIL WHERE vin=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroVin);

            if (!resultado.next()) {
                return null;
            }

            ClaseVehiculoDAO claseVehiculoDAO =
                    new ClaseVehiculoDAO();

            ClaseDeVehiculo clase = claseVehiculoDAO.buscar(resultado.getInt("id_clase"));

            return new Automovil(
                    resultado.getString("modelo"),
                    resultado.getInt("anio"),
                    resultado.getString("vin"),
                    resultado.getString("placa"),
                    Combustibles.valueOf(resultado.getString("combustible")),
                    TipoVehiculo.valueOf(resultado.getString("tipo")),
                    clase,
                    Marca.valueOf(resultado.getString("marca")),
                    EstadoAutomovil.valueOf(resultado.getString("estado"))
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public ArrayList<Automovil> listar() {

        ArrayList<Automovil> lista =
                new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM AUTOMOVIL ORDER BY marca, modelo";

            ResultSet resultado =
                    Connector.getConnection().ejecutarQuery(query);

            while (resultado.next()) {

                ClaseVehiculoDAO claseVehiculoDAO =
                        new ClaseVehiculoDAO();

                ClaseDeVehiculo clase = claseVehiculoDAO.buscar(
                                resultado.getInt("id_clase"));
                Automovil automovil =
                        new Automovil(
                                resultado.getString("modelo"),
                                resultado.getInt("anio"),
                                resultado.getString("vin"),
                                resultado.getString("placa"),
                                Combustibles.valueOf(resultado.getString("combustible")),
                                TipoVehiculo.valueOf(resultado.getString("tipo")),
                                clase,
                                Marca.valueOf(resultado.getString("marca")),
                                EstadoAutomovil.valueOf(resultado.getString("estado"))
                        );

                lista.add(automovil);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }
    public boolean modificar(Automovil automovil) {

        try {

            String query = String.format(
                    "UPDATE AUTOMOVIL SET " +
                            "placa='%s', " +
                            "modelo='%s', " +
                            "anio=%d, " +
                            "marca='%s', " +
                            "tipo='%s', " +
                            "combustible='%s', " +
                            "estado='%s', " +
                            "id_clase=%d " +
                            "WHERE vin='%s'",
                    automovil.getNumeroPlaca(),
                    automovil.getModelo(),
                    automovil.getAnio(),
                    automovil.getMarca().name(),
                    automovil.getTipoDeVehiculo().name(),
                    automovil.getCombustible().name(),
                    automovil.getEstadoAutomovil().name(),
                    automovil.getClaseDeVehiculo().gerIdClase(),
                    automovil.getNumeroVin());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(String numeroVin) {

        try {

            String query = String.format("DELETE FROM AUTOMOVIL WHERE vin='%s'", numeroVin);

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean existe(String numeroVin) {

        try {

            String query = "SELECT * FROM AUTOMOVIL WHERE vin=?";
            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroVin);

            return resultado.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }
    public Automovil buscarPorPlaca(String numeroPlaca) {
        try {
            String query = "SELECT * FROM AUTOMOVIL WHERE placa=?";

            ResultSet resultado = Connector.getConnection().ejecutarQuery(query, numeroPlaca);
            if (!resultado.next()) {
                return null;
            }
            ClaseVehiculoDAO claseVehiculoDAO = new ClaseVehiculoDAO();
            ClaseDeVehiculo clase = claseVehiculoDAO.buscar(resultado.getInt("id_clase"));
            return new Automovil(
                    resultado.getString("modelo"),
                    resultado.getInt("anio"),
                    resultado.getString("vin"),
                    resultado.getString("placa"),
                    Combustibles.valueOf(resultado.getString("combustible")),
                    TipoVehiculo.valueOf(resultado.getString("tipo")),
                    clase,
                    Marca.valueOf(resultado.getString("marca")),
                    EstadoAutomovil.valueOf(resultado.getString("estado"))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}