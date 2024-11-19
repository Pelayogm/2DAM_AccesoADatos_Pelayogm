import java.io.File;
import java.sql.*;

public class Main {
    public static void main(String [] args) throws Exception {
        Persona persona = new Persona("Tomas", "Alvarez", "900 54 32 12" );

        verDatos();
        insertarDatos(persona);
        verDatos();
    }

    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    static int ultimoid = 0;

    public static void verDatos() throws Exception {
        try {
            String url = "jdbc:mysql://localhost/test";
            connection = DriverManager.getConnection(url, "root", "");
            statement = connection.createStatement();
            String sql = "SELECT * FROM PERSONA";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String idPersona = resultSet.getString(1);
                ultimoid = Integer.parseInt(idPersona);
                String nombrePersona = resultSet.getString(2);
                String apellidoPersona = resultSet.getString(3);
                System.out.println(idPersona + " " + nombrePersona + " " + apellidoPersona);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL " + e.getMessage());
        } finally {
            if (resultSet != null) {
                //resultSet.close();
            }
            if (statement != null) {
                //statement.close();
            }
            if (connection != null) {
                //connection.close();
            }
        }
    }

    public static int insertarDatos(Persona persona) throws Exception {
        Connection connection = statement.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSONA VALUES (?,?,?,?)");
        preparedStatement.setInt(1, ultimoid + 1);
        preparedStatement.setString(2, persona.getNombre());
        preparedStatement.setString(3, persona.getApellido());
        preparedStatement.setString(4, persona.getTelefono());
        return preparedStatement.executeUpdate();
    }

    public static int actualizarDatos(String dato) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PERSONA SET NOMBRE='?'");
        preparedStatement.setString(1, dato);
        return preparedStatement.executeUpdate();
    }
}


