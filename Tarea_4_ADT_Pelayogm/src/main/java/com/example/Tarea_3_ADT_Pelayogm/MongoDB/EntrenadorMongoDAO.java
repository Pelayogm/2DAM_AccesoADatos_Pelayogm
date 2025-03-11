package com.example.Tarea_3_ADT_Pelayogm.MongoDB;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;

public class EntrenadorMongoDAO {
    private static final String DB_NOMBRE = "tarea4_pelayogm";
    private static final String COLLECTION_NOMBRE = "entrenadores";
    private MongoCollection<Document> collection;

    public EntrenadorMongoDAO(MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collection = database.getCollection(COLLECTION_NOMBRE);
    }

    public void insertarEntrenador(Entrenador entrenador) {
        Document entrenadorMongo = new Document("_id", entrenador.getIdEntrenador());
        entrenadorMongo.append("nombreEntrenador", entrenador.getNombreEntrenador());
        entrenadorMongo.append("nacionalidadEntrenador", entrenador.getNacionalidadEntrenador());

        Carnet carnet = entrenador.getCarnetEntrenador();
            Document carnetMongo = new Document("_id", carnet.getIdCarnet());
            carnetMongo.append("fechaExpedicion", carnet.getFechaExpedicion());
            carnetMongo.append("puntosCarnet", carnet.getPuntosCarnet());
            carnetMongo.append("numeroVictorias", carnet.getNumeroVictorias());

        entrenadorMongo.append("carnetEntrenador", carnetMongo);
        collection.insertOne(entrenadorMongo);
    }

    public void actualizarEntrenador(Entrenador entrenador) {
        Carnet carnet = entrenador.getCarnetEntrenador();
        Document carnetMongo = new Document("_id", carnet.getIdCarnet());
        carnetMongo.append("fechaExpedicion", carnet.getFechaExpedicion());
        carnetMongo.append("puntosCarnet", carnet.getPuntosCarnet());
        carnetMongo.append("numeroVictorias", carnet.getNumeroVictorias());

        collection.updateOne(Filters.eq("_id", entrenador.getIdEntrenador()), Updates.set("carnetEntrenador", carnetMongo));
    }

    public void mostrarTodosLosEntrenadores() {
        for (Document entrenadores: collection.find()) {
            Document carnet = (Document) entrenadores.get("carnetEntrenador");
            System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador") + " Puntos: " + carnet.get("puntosCarnet"));
        }
    }

    public void mostrarTodosLosEntrenadoresSinPuntos() {
        for (Document entrenadores: collection.find()) {
            System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador"));
        }
    }

    public void mostrarEntrenadorPorId(int idEntrenador) {
        for (Document entrenadores: collection.find(Filters.eq("_id", idEntrenador))) {
            Document carnet = (Document) entrenadores.get("carnetEntrenador");
            System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador") + " Puntos: " + carnet.get("puntosCarnet"));
        }
    }

    public void mostrarEntrenadorVictorias(int idEntrenador) {
        for (Document entrenadores: collection.find(Filters.eq("_id", idEntrenador))) {
            Document carnet = (Document) entrenadores.get("carnetEntrenador");
            System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador") + " Puntos: " + carnet.get("numeroVictorias"));
        }
    }

    public void mostrarTodosLosEntrenadoresConMasVictorias() {
        ArrayList<Integer> victorias = new ArrayList<>();
        ArrayList<Integer> idsEntrenadores = new ArrayList<>();
        for (Document entrenadores: collection.find()) {
            Document carnet = (Document) entrenadores.get("carnetEntrenador");
            victorias.add((Integer) carnet.get("numeroVictorias"));
            idsEntrenadores.add(Integer.parseInt(entrenadores.get("_id").toString()));
        }

        if (!victorias.isEmpty()) {
            int idPrimerGanador = 0;
            int victoriasPrimer = 0;
            int idSegundoGanador = 0;
            int victoriasSegundo = 0;

            for (int i = 0; i < victorias.size(); i++) {
                if (victorias.get(i) > victoriasPrimer) {
                    victoriasPrimer = victorias.get(i);
                    idPrimerGanador = idsEntrenadores.get(i);
                } else if (victorias.get(i) > victoriasSegundo) {
                    victoriasSegundo = victorias.get(i);
                    idSegundoGanador = idsEntrenadores.get(i);
                }
            }
            System.out.println("Primer ganador:");
            mostrarEntrenadorVictorias(idPrimerGanador);
            System.out.println("Segundo ganador:");
            mostrarEntrenadorVictorias(idSegundoGanador);

        }
    }

}
