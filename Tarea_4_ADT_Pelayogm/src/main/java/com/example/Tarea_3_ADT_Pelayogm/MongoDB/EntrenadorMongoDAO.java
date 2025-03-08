package com.example.Tarea_3_ADT_Pelayogm.MongoDB;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

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

    public void mostrarEntrenadorPorId(int idEntrenador) {
        for (Document entrenadores: collection.find(Filters.eq("_id", idEntrenador))) {
            Document carnet = (Document) entrenadores.get("carnetEntrenador");
            System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador") + " Puntos: " + carnet.get("puntosCarnet"));
        }
    }

    //public void mostrarTodosLosEntrenadoresConMasVictorias() {
    //    for (Document entrenadores: collection.find()) {
    //        Document carnet = (Document) entrenadores.get("carnetEntrenador");
    //        System.out.println(entrenadores.get("_id") + " - " + entrenadores.get("nombreEntrenador") + " Puntos: " + carnet.get("puntosCarnet"));
    //    }
    //}

}
