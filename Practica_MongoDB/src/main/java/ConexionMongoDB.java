import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConexionMongoDB {
    public static MongoClient conectar() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
