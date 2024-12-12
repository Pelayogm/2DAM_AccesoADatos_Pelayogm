package proyectoPokemonADT.DAO;

import proyectoPokemonADT.DAO.InterfacesDAO.GimnasioDAO;
import proyectoPokemonADT.Entidades.GimnasioEntidad;
import proyectoPokemonADT.Entidades.TorneoEntidad;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GimnasioDAOImplementacion implements GimnasioDAO {

    private static GimnasioDAOImplementacion instancia;
    private DataSource dataSource;

    private GimnasioDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GimnasioDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new GimnasioDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearGimnasio(GimnasioEntidad gimnasio) {
        String sql = "INSERT INTO GIMNASIO (idGimnasio, nombreGimnasio, tipoGimnasio, nivelGimnasio) VALUES (?,?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gimnasio.getIdGimnasio());
            preparedStatement.setString(2, gimnasio.getNombreGimnasio());
            preparedStatement.setString(3, gimnasio.getTipoGimnasio());
            preparedStatement.setInt(4, gimnasio.getNivelGimnasio());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GimnasioEntidad> obtenerTodosLosGimnasios() {
        List<GimnasioEntidad> listaDeGimnasios = new ArrayList<>();
        String sql = "SELECT * FROM GIMNASIO";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idGimnasio = resultSet.getInt(1);
                String nombreGimnasio = resultSet.getString(2);
                String tipoGimnasio = resultSet.getString(3);
                int nivelGimnasio = resultSet.getInt(4);

                GimnasioEntidad gimnasioEntidad = new GimnasioEntidad(idGimnasio, nombreGimnasio, tipoGimnasio, nivelGimnasio);
                listaDeGimnasios.add(gimnasioEntidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeGimnasios;
    }

    @Override
    public GimnasioEntidad obtenerGimnasioPorId(int id) {
        String sql = "SELECT * FROM GIMNASIO WHERE idGimnasio = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idGimnasio = resultSet.getInt(1);
                String nombreGimnasio = resultSet.getString(2);
                String tipoGimnasio = resultSet.getString(3);
                int nivelGimnasio = resultSet.getInt(4);

                return new GimnasioEntidad(idGimnasio, nombreGimnasio, tipoGimnasio, nivelGimnasio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminarGimnasio(int id) {
        String sql = "DELETE FROM GIMNASIO WHERE idGimnasio = ?";
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
