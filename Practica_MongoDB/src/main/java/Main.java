import Colecciones.EstudianteDAO;
import com.mongodb.client.MongoClient;

public class Main {
    public static void main (String [] args) {
        try (MongoClient client = ConexionMongoDB.conectar()) {
            EstudianteDAO estudianteDAO = new EstudianteDAO(client);
            //estudianteDAO.mostrarEstudiantesPorEdad(17);
            //estudianteDAO.mostrarEstudiantesPorCurso("Mecatrónica");
            estudianteDAO.mostrarEstudiantes();
            estudianteDAO.insertarEstudiante("Timoteo", 22, "Instalaciones de Television");
            //estudianteDAO.actualizarEdad("Alberto", 19);
            estudianteDAO.mostrarEstudiantes();
            estudianteDAO.eliminarAlumnoConflictivo("Timoteo");
            estudianteDAO.mostrarEstudiantes();
            estudianteDAO.calcularEdadPromedio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
