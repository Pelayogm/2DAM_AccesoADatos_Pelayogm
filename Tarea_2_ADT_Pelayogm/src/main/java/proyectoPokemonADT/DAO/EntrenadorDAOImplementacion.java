package proyectoPokemonADT.DAO;

import proyectoPokemonADT.DAO.InterfacesDAO.EntrenadorDAO;
import proyectoPokemonADT.Entidades.EntrenadorEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAOImplementacion implements EntrenadorDAO {

    private static EntrenadorDAOImplementacion instancia;
    private DataSource dataSource;

    private EntrenadorDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EntrenadorDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadorDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearEntrenador(EntrenadorEntidad entrenador) {
        String sql = "INSERT INTO ENTRENADOR (idEntrenador, nombreEntrenador, nacionalidadEntrenador) VALUES (?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entrenador.getIdEntrenador());
            preparedStatement.setString(2, entrenador.getNombreEntrenador());
            preparedStatement.setString(3, entrenador.getNacionalidadEntrenador());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<EntrenadorEntidad> obtenerTodosLosEntrenadores() {
        List<EntrenadorEntidad> listaDeEntrenadores = new ArrayList<>();
        String sql = "SELECT * FROM ENTRENADOR";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt(1);
                String nombreEntrenador = resultSet.getString(2);
                String nacionalidadEntrenador = resultSet.getString(3);

                EntrenadorEntidad entrenadorEntidad = new EntrenadorEntidad(idEntrenador, nombreEntrenador, nacionalidadEntrenador);
                listaDeEntrenadores.add(entrenadorEntidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeEntrenadores;
    }

    @Override
    public EntrenadorEntidad obtenerEntrenadorPorId(int id) {
        String sql = "SELECT * FROM ENTRENADOR WHERE ENTRENADOR.idEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt(1);
                String nombreEntrenador = resultSet.getString(2);
                String nacionalidadEntrenador = resultSet.getString(3);

                return new EntrenadorEntidad(idEntrenador, nombreEntrenador, nacionalidadEntrenador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarEntrenador(EntrenadorEntidad entrenador) {
        String sql = "UPDATE ENTRENADOR SET nombreEntrenador = ?, nacionalidadEntrenador = ?, WHERE idEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entrenador.getNombreEntrenador());
            preparedStatement.setString(2, entrenador.getNacionalidadEntrenador());
            preparedStatement.setInt(3, entrenador.getIdEntrenador());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEntrenador(int id) {
        String sql = "DELETE FROM ENTRENADOR WHERE idEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
