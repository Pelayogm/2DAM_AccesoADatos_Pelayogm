package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CarnetEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CarnetDAOImplementacion implements CarnetDAO {

    private static CarnetDAOImplementacion instancia;
    private DataSource dataSource;

    private CarnetDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CarnetDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CarnetDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public void crearCarnet(CarnetEntidad carnet) {
        String sql = "INSERT INTO CARNET (idEntrenadorCarnet, fechaExpedicionCarnet, puntosCarnet, numVictoriasCarnet) VALUES (?,?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, carnet.getIdCarnet());
            preparedStatement.setDate(2, carnet.getFechaExpedicionCarnet());
            preparedStatement.setDouble(3, carnet.getPuntosCarnet());
            preparedStatement.setInt(4, carnet.getVictoriasCarnet());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CarnetEntidad> obtenerTodosLosCarnets() {
        List<CarnetEntidad> listDeCarnets = new ArrayList<>();
        String sql = "SELECT * FROM CARNET";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCarnet = resultSet.getInt(1);
                Date fechaCarnet = resultSet.getDate(2);
                double puntosCarnet = resultSet.getDouble(3);
                int victoriasCarnet = resultSet.getInt(4);

                CarnetEntidad carnetEntidad = new CarnetEntidad(idCarnet, fechaCarnet, puntosCarnet, victoriasCarnet);
                listDeCarnets.add(carnetEntidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDeCarnets;
    }

    @Override
    public CarnetEntidad obtenerCarnetPorId(int id) {
        String sql = "SELECT * FROM CARNET WHERE idCarnet = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                int idCarnet = resultSet.getInt(1);
                Date fechaCarnet = resultSet.getDate(2);
                double puntosCarnet = resultSet.getDouble(3);
                int victoriasCarnet = resultSet.getInt(3);

                return new CarnetEntidad(idCarnet, fechaCarnet, puntosCarnet, victoriasCarnet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarCarnet(CarnetEntidad carnet) {
        String sql = "UPDATE CARNET SET fechaExpedicionCarnet = ?, puntosCarnet = ?, numVictoriasCarnet = ? WHERE idEntrenadorCarnet = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, carnet.getFechaExpedicionCarnet());
            preparedStatement.setDouble(2, carnet.getPuntosCarnet());
            preparedStatement.setInt(3, carnet.getVictoriasCarnet());
            preparedStatement.setInt(4, carnet.getIdCarnet());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCarnet(int id) {
        String sql = "DELETE FROM CARNET WHERE idEntrenadorCarnet = ?";
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
