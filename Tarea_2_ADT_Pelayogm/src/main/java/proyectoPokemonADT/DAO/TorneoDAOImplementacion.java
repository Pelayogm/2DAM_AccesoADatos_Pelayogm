package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.TorneoEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TorneoDAOImplementacion implements TorneoDAO {

    private static TorneoDAOImplementacion instancia;
    private DataSource dataSource;

    private TorneoDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TorneoDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneoDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearTorneo(TorneoEntidad torneo) {
        String sql = "INSERT INTO TORNEO (idTorneo, nombreTorneo, codigoTorneo, puntosVictoriaTorneo) VALUES (?,?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, torneo.getIdTorneo());
            preparedStatement.setString(2, torneo.getNombreTorneo());
            preparedStatement.setString(3, torneo.getCodigoTorneo());
            preparedStatement.setDouble(4, torneo.getPuntosVictoriaTorneo());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TorneoEntidad> obtenerTodosLosTorneos() {
        List<TorneoEntidad> listaDeTorneos = new ArrayList<>();
        String sql = "SELECT * FROM TORNEO";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idTorneo = resultSet.getInt(1);
                String nombreTorneo = resultSet.getString(2);
                String nacionalidadTorneo = resultSet.getString(3);
                double puntosTorneo = resultSet.getDouble(4);

                TorneoEntidad torneoEntidad = new TorneoEntidad(idTorneo, nombreTorneo, nacionalidadTorneo, puntosTorneo);
                listaDeTorneos.add(torneoEntidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeTorneos;
    }

    @Override
    public TorneoEntidad obtenerTorneoPorId(int id) {
        String sql = "SELECT * FROM TORNEO WHERE idTorneo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                int idTorneo = resultSet.getInt(1);
                String nombreTorneo = resultSet.getString(2);
                String codigoTorneo = resultSet.getString(3);
                double puntosTorneo = resultSet.getDouble(4);

                return new TorneoEntidad(idTorneo, nombreTorneo, codigoTorneo, puntosTorneo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarTorneo(TorneoEntidad torneo) {
        String sql = "UPDATE TORNEO SET nombreTorneo = ?, codigoTorneo = ?, puntosVictoria = ? ,WHERE idEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, torneo.getNombreTorneo());
            preparedStatement.setString(2, torneo.getCodigoTorneo());
            preparedStatement.setDouble(3, torneo.getPuntosVictoriaTorneo());
            preparedStatement.setDouble(4, torneo.getIdTorneo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTorno(int id) {
        String sql = "DELETE FROM TORNEO WHERE idTorneo = ?";
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
