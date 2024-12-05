package proyectoPokemonADT.DAO;

import proyectoPokemonADT.DAO.InterfacesDAO.EntrenadorTorneoDAO;
import proyectoPokemonADT.Entidades.EntrenadorTorneoEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorTorneoDAOImplementacion implements EntrenadorTorneoDAO {

    private static EntrenadorTorneoDAOImplementacion instancia;
    private DataSource dataSource;

    private EntrenadorTorneoDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EntrenadorTorneoDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadorTorneoDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearEntrenadorTorneo(EntrenadorTorneoEntidad entrenadorTorneo) {
        String sql = "INSERT INTO TORNEO_ENTRENADOR (idTorneo, idEntrenador) VALUES (?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entrenadorTorneo.getIdTorneo());
            preparedStatement.setInt(2, entrenadorTorneo.getIdUsuario());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEntrenadorTorneo(int id) {

    }

    @Override
    public List<Integer> obtenerTorneosDeUnEntrenador(int idUsuario) {
        List<Integer> listaDeIdDeTorneo = new ArrayList<>();
        String sql = "SELECT idTorneo FROM TORNEO_ENTRENADOR WHERE idEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listaDeIdDeTorneo.add(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeIdDeTorneo;
    }

    @Override
    public List<Integer> obtenerIdDeEntrenadorPorIdTorneo(int idTorneo) {
        List<Integer> listaDeIdDeParticipantes = new ArrayList<>();
        String sql = "SELECT idEntrenador FROM TORNEO_ENTRENADOR WHERE idTorneo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idTorneo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listaDeIdDeParticipantes.add(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeIdDeParticipantes;
    }
}
