package kz.bars.family.budget.receipt.api.actuator.health;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.bson.Document;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final MongoClient mongoClient;

    public MyHealthIndicator(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    private int check() {
        try {
            // Try to connect to the MongoDB server
            MongoDatabase database = mongoClient.getDatabase("receiptDB");
            MongoCollection<Document> collection = database.getCollection("receipts");
            collection.find().first();
            return 0;
        } catch (Exception e) {
            // If there is an exception, return an error code
            return 1;
        }
    }

}
