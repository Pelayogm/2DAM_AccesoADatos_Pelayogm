package DAO;

import entidades.ModuloEntidad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAOImplementacion implements ModuloDAO {

    public static ModuloDAOImplementacion instancia;
    private DataSource dataSource;

    private ModuloDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ModuloDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new ModuloDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearModulo(ModuloEntidad modulo) {
        String sql = "INSERT INTO MODULO (cod_modulo, nombre_modulo, cod_ciclo, curso, cod_profesor)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, modulo.getCod_modulo());
            preparedStatement.setString(2, modulo.getNombre_modulo());
            preparedStatement.setString(3, modulo.getCod_ciclo());
            preparedStatement.setString(4, modulo.getCurso());
            preparedStatement.setString(5, modulo.getCod_profesor());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ModuloEntidad obtenerModuloPorId(String id) {
        String sql = "SELECT * FROM MODULO WHERE MODULO.COD_MODULO = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombreModulo = resultSet.getNString(2);
                String codCiclo = resultSet.getNString(3);
                String curso = resultSet.getNString(4);
                String codProfesor = resultSet.getNString(5);
                return new ModuloEntidad(id, nombreModulo, codCiclo, curso, codProfesor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ModuloEntidad> obtenerTodosLosModulos() {
        List <ModuloEntidad> listaDeModulos = new ArrayList<>();
        String sql = "SELECT * FROM MODULOS";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String codModulo = resultSet.getNString(1);
                String nombreModulo = resultSet.getNString(2);
                String codCiclo = resultSet.getNString(3);
                String curso = resultSet.getNString(4);
                String codProfesor = resultSet.getNString(5);
                ModuloEntidad moduloEntidad = new ModuloEntidad(codModulo, nombreModulo, codCiclo, curso, codProfesor);
                listaDeModulos.add(moduloEntidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeModulos;
    }

    @Override
    public void actualizarModulo(ModuloEntidad moduloEntidad) {
        String sql = "UPDATE MODULO SET nombre_modulo = ?, cod_ciclo = ?, curso = ?, cod_profesor = ? WHERE cod_modulo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, moduloEntidad.getNombre_modulo());
            preparedStatement.setString(2, moduloEntidad.getCod_ciclo());
            preparedStatement.setString(3, moduloEntidad.getCurso());
            preparedStatement.setString(4, moduloEntidad.getCod_profesor());
            preparedStatement.setString(5, moduloEntidad.getCod_modulo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void eliminarModulo(String id) {
        String sql = "DELETE FROM MODULO WHERE COD_MODULO = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> modulosDelProfesorId(String id) {
        List<String> listaDeModulos = new ArrayList<>();
        String sql = "SELECT NOMBRE_MODULO FROM MODULO WHERE COD_PROFESOR = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                String nombreModulo = resultSet.getNString(1);
                listaDeModulos.add(nombreModulo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeModulos;
    }
}
