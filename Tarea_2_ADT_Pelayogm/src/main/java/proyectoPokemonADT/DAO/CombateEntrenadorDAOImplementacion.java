package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntrenadorEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CombateEntrenadorDAOImplementacion implements CombateEntrenadorDAO {

    private static CombateEntrenadorDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateEntrenadorDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CombateEntrenadorDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateEntrenadorDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public void crearCombateTorneo (CombateEntrenadorEntidad combateEntrenador) {
        String sql = "INSERT INTO COMBATE_ENTRENADOR (idCombate, idEntrenador1, idEntrenador2) VALUES (?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, combateEntrenador.getIdCombate());
            preparedStatement.setInt(2, combateEntrenador.getIdEntrenador1());
            preparedStatement.setInt(3, combateEntrenador.getIdEntrenador2());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCombateTorneo (int id) {
        String sql = "DELETE FROM COMBATE_ENTRENADOR WHERE COMBATE_ENTRENADOR.idCombate = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CombateEntrenadorEntidad> obtenerTodosLosParticipantes() {
        return List.of();
    }

    @Override
    public void actualizarCombateEntrenador (int idCombate, CombateEntrenadorEntidad combateEntrenador) {
        String sql = "UPDATE combate_entrenador SET idEntrenador1 = ?, idEntrenador2 = ? WHERE idCombateEntrenador = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, combateEntrenador.getIdEntrenador1());
            preparedStatement.setInt(2, combateEntrenador.getIdEntrenador2());
            preparedStatement.setInt(3, combateEntrenador.getIdCombate());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> obtenerParticipantesPorIdDelCombate (int idCombate) {
        List<Integer> participantes = new ArrayList<>();
        String sql = "SELECT idEntrenador1, idEntrenador2 FROM combate_entrenador WHERE idCombate = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCombate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idEntrenador1 = resultSet.getInt(1);
                int idEntrenador2 = resultSet.getInt(2);

                participantes.add(idEntrenador1);
                participantes.add(idEntrenador2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participantes;
    }

}
