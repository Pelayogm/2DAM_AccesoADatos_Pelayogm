import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseActualizar {
    public static void main(String [] args) throws Exception {

        DatabaseActualizar database = new DatabaseActualizar();
        database.dataSource();
        database.verDatos();
    }

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public DataSource dataSource() {
        File file = new File(".", "DBProperties.txt");

        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        MysqlDataSource mysqlDataSource = null;
        try {
            fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(properties.getProperty("URL"));
            mysqlDataSource.setUser(properties.getProperty("USUARIO"));
            mysqlDataSource.setPassword(properties.getProperty("PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDataSource;
    }

    public void verDatos() {
        try {
            connection = dataSource().getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM PERSONA";
            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultSet.updateString(4, "Armadillo");
                resultSet.updateRow();

                String idPersona = resultSet.getString(1);
                String nombrePersona = resultSet.getString(2);
                String apellidoPersona = resultSet.getString(3);
                String telefonoPersona = resultSet.getString(4);

                System.out.println(idPersona + " " + nombrePersona + " " + apellidoPersona + " " + telefonoPersona);
            }
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error durante la conexion a la base de datos " + e.getMessage());
        }
    }
}
