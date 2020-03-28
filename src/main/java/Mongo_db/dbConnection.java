package Mongo_db;

import com.mongodb.MongoClient;

public class dbConnection {
    public static MongoClient mongoClient = new MongoClient(MongoDB_consts.host, MongoDB_consts.port);
}