package com.example.Tarea_3_ADT_Pelayogm.MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class EntrenadorMongoDAO {
    private static final String DB_NOMBRE = "tarea4_pelayogm";
    private static final String COLLECTION_NOMBRE = "entrenadores";
    private MongoCollection<Document> collection;

    public EntrenadorMongoDAO(MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collection = database.getCollection(COLLECTION_NOMBRE);
    }
}
