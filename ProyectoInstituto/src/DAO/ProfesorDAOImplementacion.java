package DAO;

import entidades.ProfesorEntidad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOImplementacion implements ProfesorDAO {

    public static ProfesorDAOImplementacion instacia;
    private DataSource dataSource;

    private ProfesorDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProfesorDAOImplementacion getInstacia (DataSource dataSource) {
        if (instacia == null) {
            instacia = new ProfesorDAOImplementacion(dataSource);
        }
            return instacia;
        }

    @Override
    public void crearProfesor(ProfesorEntidad profesor) {
        String sql = "INSERT INTO PROFESOR (cod_profesor, nombre_profesor, ciudad)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, profesor.getCodProfesor());
            preparedStatement.setString(2, profesor.getNombreProfesor());
            preparedStatement.setString(3, profesor.getResidencia());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProfesorEntidad obtenerProfesorPorId(String id) {
        String sql = "SELECT * FROM PROFESOR WHERE PROFESOR.COD_PROFESOR = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombreProfesor = resultSet.getNString(2);
                String residenciaProfesor = resultSet.getNString(3);
                return new ProfesorEntidad(id, nombreProfesor,residenciaProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProfesorEntidad> obtenerTodosLosProfesores() {
        List<ProfesorEntidad> listaDeProfesores = new ArrayList<>();
        String sql = "SELECT * FROM PROFESOR";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String idProfesor = resultSet.getNString(1);
                String nombreProfesor = resultSet.getNString(2);
                String residenciaProfesor = resultSet.getNString(3);
                ProfesorEntidad profesor = new ProfesorEntidad(idProfesor, nombreProfesor, residenciaProfesor);
                listaDeProfesores.add(profesor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeProfesores;
    }

    @Override
    public void actualizarProfesor(ProfesorEntidad profesor) {
        String sql = "UPDATE PROFESOR SET nombre_profesor = ?, ciudad = ? WHERE cod_profesor = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, profesor.getNombreProfesor());
            preparedStatement.setString(2, profesor.getResidencia());
            preparedStatement.setString(3, profesor.getCodProfesor());
            preparedStatement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarProfesor(String id) {
        String sql = "DELETE FROM PROFESOR WHERE COD_PROFESOR = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
