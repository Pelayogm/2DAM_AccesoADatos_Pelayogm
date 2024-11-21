import DAO.AlumnoDAOImplementacion;
import DTO.AlumnoDTO;
import com.mysql.cj.jdbc.MysqlDataSource;
import servicios.AlumnoServicio;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main (String [] args) {
        DataSource dataSource = configurarDataSource();
        AlumnoDAOImplementacion alumnoDAOImplementacion = AlumnoDAOImplementacion.getInstancia(dataSource);
        AlumnoServicio alumnoServicio = AlumnoServicio.getInstancia(dataSource);
        AlumnoDTO alumnoDTO = new AlumnoDTO("37", "Jorge", "Ruiz de Garcia Paez", 19, 'C');
        try {
            alumnoServicio.crearAlumno(alumnoDTO);
            alumnoServicio.obtenerAlumnoPorId("37");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataSource configurarDataSource() {
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
}
