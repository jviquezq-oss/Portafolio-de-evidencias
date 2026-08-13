package DAO;

import Entidades.ClaseDeVehiculo;
import dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClaseVehiculoDAO {

    public boolean registrar(ClaseDeVehiculo clase) {

        try {

            String query = String.format(
                    "INSERT INTO CLASE_VEHICULO(nombre, descripcion, precio_por_dia) " +
                            "VALUES('%s','%s',%f)",
                    clase.getNombre(),
                    clase.getDescripcion(),
                    clase.getPrecioPorDia());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public ClaseDeVehiculo buscar(int idClase) {

        try {

            String query =
                    "SELECT * FROM CLASE_VEHICULO WHERE id_clase=?";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query, idClase);

            if (!resultado.next()) {
                return null;
            }

            return new ClaseDeVehiculo(resultado.getInt("id_clase"), resultado.getString("nombre"), resultado.getString("descripcion"), resultado.getDouble("precio_por_dia"));

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public ArrayList<ClaseDeVehiculo> listar() {

        ArrayList<ClaseDeVehiculo> lista =
                new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM CLASE_VEHICULO ORDER BY nombre";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query);

            while (resultado.next()) {

                ClaseDeVehiculo clase =
                        new ClaseDeVehiculo(resultado.getInt("id_clase"), resultado.getString("nombre"), resultado.getString("descripcion"), resultado.getDouble("precio_por_dia"));

                lista.add(clase);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(ClaseDeVehiculo clase) {

        try {

            String query = String.format(
                    "UPDATE CLASE_VEHICULO SET " +
                            "nombre='%s', " +
                            "descripcion='%s', " +
                            "precio_por_dia=%f " +
                            "WHERE id_clase=%d",
                    clase.getNombre(),
                    clase.getDescripcion(),
                    clase.getPrecioPorDia(),
                    clase.gerIdClase());

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int idClase) {

        try {

            String query = String.format(
                    "DELETE FROM CLASE_VEHICULO WHERE id_clase=%d",
                    idClase);

            Connector.getConnection().ejecutarStatement(query);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean existe(int idClase) {

        try {

            String query =
                    "SELECT * FROM CLASE_VEHICULO WHERE id_clase=?";

            ResultSet resultado =
                    Connector.getConnection()
                            .ejecutarQuery(query, idClase);

            return resultado.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

}