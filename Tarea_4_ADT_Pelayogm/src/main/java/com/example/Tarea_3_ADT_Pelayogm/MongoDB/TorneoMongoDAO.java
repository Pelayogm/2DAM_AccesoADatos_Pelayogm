package com.example.Tarea_3_ADT_Pelayogm.MongoDB;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Combate;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.CombateEntrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TorneoMongoDAO {
    private static final String DB_NOMBRE = "tarea4_pelayogm";
    private static final String COLLECTION_NOMBRE = "torneos";
    private MongoCollection<Document> collection;

    public TorneoMongoDAO(MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collection = database.getCollection(COLLECTION_NOMBRE);
    }

    public void insertarTorneo(Torneo torneo) {
        Document torneoMongo = new Document("_id", torneo.getIdTorneo());
        torneoMongo.append("nombreTorneo", torneo.getNombreTorneo());
        torneoMongo.append("codigoTorneo", torneo.getCodigoTorneo());
        torneoMongo.append("puntosTorneo", torneo.getPuntosVictoriaTorneo());
        torneoMongo.append("idAdminTorneo", torneo.getIdAdminTorneo());
        torneoMongo.append("idGanadorTorneo", torneo.getIdGanador());

        List<Document> listaDeCombates = new ArrayList<>();
        for (int i = 0; i < torneo.getCombates().size(); i++) {
            Combate combateCurrent = torneo.getCombates().get(i);
            CombateEntrenador combateEntrenadorCurrent = torneo.getCombates().get(i).getCombateEntrenador();

            Document combate = new Document("_id", combateCurrent.getIdCombate());
            combate.append("fechaCombate", combateCurrent.getFechaCombate());
            combate.append("idTorneo", combateCurrent.getTorneo().getIdTorneo());
            //Combate entrenador pasado a Document
                Document combateEntrenador = new Document("_id", combateCurrent.getIdCombate());
                combateEntrenador.append("idEntrenador1", combateEntrenadorCurrent.getIdEntrenador1());
                combateEntrenador.append("idEntrenador2", combateEntrenadorCurrent.getIdEntrenador2());
                combateEntrenador.append("idGanador", combateEntrenadorCurrent.getIdGanador());
            combate.append("combateEntrenador", combateEntrenador);
            listaDeCombates.add(combate);
        }

        torneoMongo.append("combatesTorneo", listaDeCombates);
        collection.insertOne(torneoMongo);
    }

    public void actualizarTorneo(Torneo torneo) {
        List<Document> listaDeCombates = new ArrayList<>();
        for (int i = 0; i < torneo.getCombates().size(); i++) {
            Combate combateCurrent = torneo.getCombates().get(i);
            CombateEntrenador combateEntrenadorCurrent = torneo.getCombates().get(i).getCombateEntrenador();

            Document combate = new Document("_id", combateCurrent.getIdCombate());
            combate.append("fechaCombate", combateCurrent.getFechaCombate());
            combate.append("idTorneo", combateCurrent.getTorneo().getIdTorneo());
            //Combate entrenador pasado a Document
            Document combateEntrenador = new Document("_id", combateCurrent.getIdCombate());
            combateEntrenador.append("idEntrenador1", combateEntrenadorCurrent.getIdEntrenador1());
            combateEntrenador.append("idEntrenador2", combateEntrenadorCurrent.getIdEntrenador2());
            combateEntrenador.append("idGanador", combateEntrenadorCurrent.getIdGanador());
            combate.append("combateEntrenador", combateEntrenador);
            listaDeCombates.add(combate);
        }

        collection.updateOne(Filters.eq("_id", torneo.getIdTorneo()), Updates.set("idGanadorTorneo", torneo.getIdGanador()));
        collection.updateOne(Filters.eq("_id", torneo.getIdTorneo()), Updates.set("combatesTorneo", listaDeCombates));
    }

    public void mostrarTodosLosTorneos() {
        for (Document torneos: collection.find()) {
            System.out.println(torneos.get("_id") + " - " + torneos.get("nombreTorneo") + " Region: " + torneos.get("codigoTorneo"));
        }
    }

    public void mostrarTorneoPorId(int idTorneo) {
        for (Document torneos: collection.find(Filters.eq("_id", idTorneo))) {
            System.out.println(torneos.toJson());
        }
    }

    public int mostrarGanadorTorneo(int idTorneo) {
        for (Document torneos: collection.find(Filters.eq("_id", idTorneo))) {
            String idGanador = torneos.get("idGanadorTorneo").toString();
           return Integer.parseInt(idGanador);
        }
        return -1;
    }

    public void mostrarInformacionDeUnaRegion(String regionTorneo) {
        for (Document torneos: collection.find(Filters.eq("codigoTorneo", regionTorneo))) {
            System.out.println(torneos.toJson());
        }
    }

    public void mostrarLosTorneosDeMasPuntos() {
        ArrayList<Float> puntos = new ArrayList<>();
        ArrayList<Integer> idsTorneos = new ArrayList<>();
        for (Document torneos: collection.find()) {
            puntos.add(Float.parseFloat(torneos.get("puntosTorneo").toString()));
            idsTorneos.add(Integer.parseInt(torneos.get("_id").toString()));
        }

        if (!puntos.isEmpty()) {
            int idPrimerTorneo = 0;
            float puntosPrimer = 0;
            int idSegundoTorneo = 0;
            float puntosSegundo = 0;

            for (int i = 0; i < puntos.size(); i++) {
                if (puntos.get(i) > puntosPrimer) {
                    if (puntosPrimer > puntosSegundo) {
                        puntosSegundo = puntosPrimer;
                        idSegundoTorneo = idPrimerTorneo;
                    }
                    puntosPrimer = puntos.get(i);
                    idPrimerTorneo = idsTorneos.get(i);
                } else if (puntos.get(i) > puntosSegundo) {
                    puntosSegundo = puntos.get(i);
                    idSegundoTorneo = idsTorneos.get(i);
                }
            }
            System.out.println("Primer torneo:");
            mostrarTorneoYPuntos(idPrimerTorneo);
            System.out.println("Segundo torneo:");
            mostrarTorneoYPuntos(idSegundoTorneo);
        }
    }

    public void mostrarTorneoYPuntos(int idTorneo) {
        for (Document torneos: collection.find(Filters.eq("_id", idTorneo))) {
            System.out.println("Nombre Torneo: " + torneos.get("nombreTorneo") + " | Puntos Torneo: " + torneos.get("puntosTorneo"));
        }
    }
}
