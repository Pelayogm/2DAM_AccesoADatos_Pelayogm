package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CarnetEntidad;
import proyectoPokemonADT.Entidades.TorneoAdminEntidad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TorneoAdminDAOImplementacion implements TorneoAdminDAO {

    private static TorneoAdminDAOImplementacion instancia;
    private DataSource dataSource;

    private TorneoAdminDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TorneoAdminDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneoAdminDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearTorneoAdmin(TorneoAdminEntidad torneoAdmin) {
        String sql = "INSERT INTO TORNEO_ADMIN (idTorneo, idAdminTorneo) VALUES (?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, torneoAdmin.getIdTorneo());
            preparedStatement.setInt(2, torneoAdmin.getIdAdminTorneo());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TorneoAdminEntidad> obtenerTodosAdminTorneo() {
        List<TorneoAdminEntidad> listDeTorneoAdmin = new ArrayList<>();
        String sql = "SELECT * FROM TORNEO_ADMIN";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idTorneo = resultSet.getInt(1);
                int idAdmin = resultSet.getInt(2);

                TorneoAdminEntidad torneoAdminEntidad = new TorneoAdminEntidad(idTorneo, idAdmin);
                listDeTorneoAdmin.add(torneoAdminEntidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDeTorneoAdmin;
    }

    @Override
    public int obtenerAdminTorneoPorId(int id) {
        String sql = "SELECT * FROM TORNEO_ADMIN WHERE TORNEO_ADMIN.idAdminTorneo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<TorneoAdminEntidad> obtenerTorneosPorAdminId(int id) {
        List<TorneoAdminEntidad> listDeTorneoAdmin = new ArrayList<>();
        String sql = "SELECT * FROM TORNEO_ADMIN WHERE TORNEO_ADMIN.idAdminTorneo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idTorneo = resultSet.getInt(1);
                int idAdmin = resultSet.getInt(2);

                TorneoAdminEntidad torneoAdminEntidad = new TorneoAdminEntidad(idTorneo, idAdmin);
                listDeTorneoAdmin.add(torneoAdminEntidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDeTorneoAdmin;
    }

    @Override
    public void eliminarTorneoAdmin(int id) {
        String sql = "DELETE FROM TORNEO_ADMIN WHERE TORNEO_ADMIN.idAdminTorneo = ?";
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
