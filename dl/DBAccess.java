package dl;

import java.sql.*;

public class DBAccess {
    // La clase DBAccess tiene los miembros necesarios para controlar la conexión con la base de datos.

    // Atributos
    private final Connection connection;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    // Métodos
    // Constructor
    public DBAccess(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    // Rutina que recibe un String que contiene una sentencia de MySQL y la ejecuta utilizando
    // una rutina de un objeto de la clase Connection.
    public void ejecutarStatement(String pStatement) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(pStatement);
    }
    public ResultSet ejecutarQuery(String pQuery) throws SQLException {

        statement = connection.createStatement();
        return statement.executeQuery(pQuery);

    }
    public ResultSet ejecutarQuery(String pQuery, int pValor) throws SQLException {

        preparedStatement = connection.prepareStatement(pQuery);
        preparedStatement.setInt(1, pValor);

        return preparedStatement.executeQuery();

    }
    public ResultSet ejecutarQuery(String pQuery, String pValor) throws SQLException {

        preparedStatement = connection.prepareStatement(pQuery);
        preparedStatement.setString(1, pValor);

        return preparedStatement.executeQuery();

    }
}
