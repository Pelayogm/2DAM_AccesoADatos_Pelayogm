import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabasePersonasConFile {
    public static void main(String [] args) throws Exception {

        DatabasePersonasConFile database = new DatabasePersonasConFile();
        database.dataSource();
        database.verDatos();
    }

    private Connection connection;
    private Statement statement;
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
            statement = connection.createStatement();
            String sql = "SELECT * FROM PERSONA";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String idPersona = resultSet.getString(1);
                String nombrePersona = resultSet.getString(2);
                String apellidoPersona = resultSet.getString(3);

                System.out.println(idPersona + " " + nombrePersona + " " + apellidoPersona);
            }

        } catch (SQLException e) {
            System.out.println("Error durante la conexion a la base de datos " + e.getMessage());
        }
    }
}
