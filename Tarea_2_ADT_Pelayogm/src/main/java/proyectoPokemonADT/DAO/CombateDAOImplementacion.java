package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntidad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CombateDAOImplementacion implements CombateDAO {

    private static CombateDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CombateDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearCombate(CombateEntidad combate) {
        String sql = "INSERT INTO COMBATE (idCombate, fechaCombate, idTorneo) VALUES (?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, combate.getIdCombate());
            preparedStatement.setDate(2, combate.getFechaCombate());
            preparedStatement.setInt(3, combate.getIdTorneo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CombateEntidad> obtenerTodosLosCombates() {
        List<CombateEntidad> listaDeCombates = new ArrayList<>();
        String sql = "SELECT * FROM COMBATE";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCombate = resultSet.getInt(1);
                Date fechaCombate = resultSet.getDate(2);
                int idTorneo = resultSet.getInt(3);

                CombateEntidad combateEntidad = new CombateEntidad(idCombate, fechaCombate, idTorneo);
                listaDeCombates.add(combateEntidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeCombates;
    }

    @Override
    public List<CombateEntidad> obtenerTodosLosCombatesDeUnTorneo(int id) {
        List<CombateEntidad> listaDeCombates = new ArrayList<>();
        String sql = "SELECT * FROM COMBATE WHERE COMBATE.idTorneo = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                int idCombate = resultSet.getInt(1);
                Date fechaCombate = resultSet.getDate(2);
                int idTorneo = resultSet.getInt(3);

                CombateEntidad combateEntidad = new CombateEntidad(idCombate, fechaCombate, idTorneo);
                listaDeCombates.add(combateEntidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeCombates;
    }

    @Override
    public CombateEntidad obtenerCombatePorId(int id) {
        String sql = "SELECT * FROM COMBATE WHERE idCombate = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                int idCombate = resultSet.getInt(1);
                Date fechaCombate = resultSet.getDate(2);
                int idTorneo = resultSet.getInt(3);

                return new CombateEntidad(idCombate, fechaCombate, idTorneo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarCombate(CombateEntidad combate) {
        String sql = "UPDATE CARNET SET fechaCombate = ?, idTorneo = ?, WHERE idCombate = ? ";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, combate.getFechaCombate());
            preparedStatement.setInt(2, combate.getIdTorneo());
            preparedStatement.setInt(3, combate.getIdCombate());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCombate(int id) {
        String sql = "DELETE FROM COMBATE WHERE idCombate = ?";
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
