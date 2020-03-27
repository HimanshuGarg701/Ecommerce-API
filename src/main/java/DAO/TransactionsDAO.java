package DAO;

import DTO.DTO;
import DTO.TransactionDTO;
import Mongo_db.MongoDB_consts;
import Mongo_db.dbConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class TransactionsDAO {
    private static TransactionsDAO InstanceTransactionsDAO;

    private TransactionsDAO() {
    }
    private MongoClient mongoClient = dbConnection.mongoClient;
    private MongoDatabase db = mongoClient.getDatabase(MongoDB_consts.db_name);
    private MongoCollection<Document> transaction_collection = db.getCollection(MongoDB_consts.transaction_col);

    public static TransactionsDAO getInstance() {
        if (InstanceTransactionsDAO == null) {
            InstanceTransactionsDAO = new TransactionsDAO();
        }
        return InstanceTransactionsDAO;
    }

    public void addTransaction(TransactionDTO transaction) {
        Document doc = new Document("machineCode", transaction.machineCode)
                .append("itemCode", transaction.itemCode)
                .append("paymentMethod", transaction.paymentMethod);
        transaction_collection.insertOne(doc);
    }

    public ArrayList<DTO> getAllTransactions() {
        ArrayList<DTO> transactionsList = new ArrayList<>();

        MongoCursor<Document> cursor = transaction_collection.find().iterator();
        Document doc;

        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                transactionsList.add(new TransactionDTO(
                        (long) doc.get("machineCode"),
                        (long) doc.get("itemCode"),
                        (long) doc.get("paymentMethod")));
            }
        } catch (Throwable err) {
            System.out.println("error in TransactionsDAO: " + err.toString());
        } finally {
            cursor.close();
        }
        return transactionsList;
    }

    public long getIndex() {
        return transaction_collection.countDocuments();

    }
}