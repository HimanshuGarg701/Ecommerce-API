package Mongo_db;

import com.mongodb.MongoClient;

public class dbConnection {
    private static MongoClient mongoClient;

    private dbConnection() {
    }

    public static MongoClient getInstance() {
        if (mongoClient == null) {
            mongoClient = new MongoClient(MongoDB_consts.host, MongoDB_consts.port);

        }
        return mongoClient;
    }

}