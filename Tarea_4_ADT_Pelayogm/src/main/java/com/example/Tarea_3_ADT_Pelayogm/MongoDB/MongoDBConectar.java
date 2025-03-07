package com.example.Tarea_3_ADT_Pelayogm.MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConectar {
    public static MongoClient conectar() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
