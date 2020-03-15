package DAO;

import DTO.DTO;
import DTO.TransactionDTO;
import Mongo_db.MongoDB_consts;
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

    private MongoDB_consts mdb_const = new MongoDB_consts();
    private MongoClient mongoClient = new MongoClient(mdb_const.host, mdb_const.port);
    private MongoDatabase db = mongoClient.getDatabase(mdb_const.db_name);
    private MongoCollection<Document> transaction_collection = db.getCollection(mdb_const.transaction_col);

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