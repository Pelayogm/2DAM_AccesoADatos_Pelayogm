package DAO;

import entidades.AlumnoEntidad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnoDAOImplementacion implements AlumnoDAO {

    public static AlumnoDAOImplementacion instancia;
    private DataSource dataSource;

    private AlumnoDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static AlumnoDAOImplementacion getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new AlumnoDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearAlumno(AlumnoEntidad alumno) {
        String sql = "INSERT INTO ALUMNO (cod_alumno, nombre_alumno, apellidos_alumno, fecha_nacimiento, grupo) VALUES (?,?,?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alumno.getCodAlumno());
            preparedStatement.setString(2, alumno.getNombreAlumno());
            preparedStatement.setString(3, alumno.getApellidosAlumno());
            preparedStatement.setDate(4, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            preparedStatement.setString(5, String.valueOf(alumno.getGrupo()));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AlumnoEntidad obtenerAlumnoPorId(String id) {
        String sql = "SELECT FROM * FROM ALUMNO WHERE ALUMNO.COD_ALUMNO = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                String codAlumno = resultSet.getNString(1);
                String nomAlumno = resultSet.getNString(2);
                String apeAlumno = resultSet.getNString(3);
                Date dateAlumno = resultSet.getDate(4);
                char grupoAlumno = resultSet.getString("grupo").charAt(0);
                return new AlumnoEntidad(codAlumno, nomAlumno, apeAlumno, dateAlumno, grupoAlumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AlumnoEntidad> obtenerTodosLosAlumnos() {
        List<AlumnoEntidad> listaDeAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM ALUMNO";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String codAlumno = resultSet.getNString(1);
                String nomAlumno = resultSet.getNString(2);
                String apeAlumno = resultSet.getNString(3);
                Date dateAlumno = resultSet.getDate(4);
                char grupoAlumno = resultSet.getString("grupo").charAt(0);
                AlumnoEntidad alumnoEntidad = new AlumnoEntidad(codAlumno, nomAlumno, apeAlumno, dateAlumno, grupoAlumno);
                listaDeAlumnos.add(alumnoEntidad);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeAlumnos;
    }

    @Override
    public void actualizarAlumno(AlumnoEntidad alumno) {
        String sql = "UPDATE ALUMNO SET nombre_alumno = ?, apellidos_alumno = ?, fecha_nacimiento = ?, grupo = ? WHERE cod_alumno = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alumno.getNombreAlumno());
            preparedStatement.setString(2, alumno.getApellidosAlumno());
            preparedStatement.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            preparedStatement.setString(4, String.valueOf(alumno.getGrupo()));
            preparedStatement.setString(5, alumno.getCodAlumno());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAlumno(String id) {
        String sql = "DELETE FROM ALUMNO WHERE cod_alumno = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
