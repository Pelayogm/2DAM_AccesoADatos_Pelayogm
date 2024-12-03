package proyectoPokemonADT.ArchivosDelPrograma;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConexionBaseDeDatos {

    private static ConexionBaseDeDatos instancia;

    private ConexionBaseDeDatos () {
    }

    public static ConexionBaseDeDatos getInstancia () {
        if (instancia == null ) {
            instancia = new ConexionBaseDeDatos();
        }
        return instancia;
    }

    public DataSource configurarDataSource() {
        File file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "DBProperties.txt");

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

}
