package Colecciones;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;

public class EstudianteDAO {

    private static final String DB_NOMBRE = "escuela";
    private static final String COLLECTION_NOMBRE = "estudiantes";
    private MongoCollection<Document> collection;

    public EstudianteDAO (MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collection = database.getCollection(COLLECTION_NOMBRE);
    }

    public void insertarEstudiante (String nombre, int edad, String curso) {
        Document estudiante = new Document("nombre", nombre)
                .append("edad", edad)
                .append("curso", curso);
        collection.insertOne(estudiante);
        System.out.println(nombre + " insertado en MongolianDB");

    }

    public void mostrarEstudiantes() {
        for (Document estudiante: collection.find()) {
            //System.out.println(estudiante.getString("nombre"));
            System.out.println(estudiante.toJson());
        }
    }

    public void mostrarEstudiantesPorEdad(int edad) {
        for (Document estudiante: collection.find(Filters.gt("edad", edad))) {
            System.out.println(estudiante.toJson());
        }
    }

    public void mostrarEstudiantesPorCurso(String curso) {
        for (Document estudiante: collection.find(Filters.eq("curso", curso))) {
            System.out.println(estudiante.toJson());
        }
    }

    public void actualizarEdad (String nombre, int edadNueva) {
        collection.updateOne(Filters.eq("nombre", nombre), Updates.set("edad", edadNueva));
        System.out.println("Actualizado con éxito");
    }

    public void eliminarAlumnoConflictivo(String nombre) {
        collection.deleteOne(Filters.eq("nombre", nombre));
        System.out.println("A " + nombre + " se le acaba de aplicar un correctivo directo.");
    }

    public void calcularEdadPromedio() {
        AggregateIterable<Document> resultado;
        resultado = collection.aggregate(Arrays.asList(
                new Document("$group", new Document("_id", "nombre")
                        .append("edadPromedio", new Document("$avg", "$edad"))
        )));

        for (Document document : resultado) {
            System.out.println("Edad promedio: " + document.getDouble("edadPromedio"));
        }
    }
}
