package DAO;

import DTO.DTO;
import DTO.PaymentMethodDTO;

import java.util.ArrayList;

import Mongo_db.MongoDB_consts;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class PaymentMethodDAO {
    private static PaymentMethodDAO InstancePaymentDao;

    private PaymentMethodDAO() {
    }

    private MongoDB_consts mdb_const = new MongoDB_consts();
    private MongoClient mongoClient = new MongoClient(mdb_const.host, mdb_const.port);
    private MongoDatabase db = mongoClient.getDatabase(mdb_const.db_name);
    private MongoCollection<Document> paymentMethod_collection = db.getCollection(mdb_const.paymentMethod_col);

    public static PaymentMethodDAO getInstance() {
        if (InstancePaymentDao == null) {
            InstancePaymentDao = new PaymentMethodDAO();
        }
        return InstancePaymentDao;
    }


    public void addPaymentMethod(PaymentMethodDTO paymentMethod) {
        Document doc = new Document("machineCode", paymentMethod.machineCode).append("name", paymentMethod.name);
        paymentMethod_collection.insertOne(doc);
    }


    public ArrayList<DTO> getAllPaymentMethods() {
        ArrayList<DTO> paymentMethodList = new ArrayList<>();

        MongoCursor<Document> cursor = paymentMethod_collection.find().iterator();
        Document doc;

        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                paymentMethodList.add(new PaymentMethodDTO((long) doc.get("machineCode"), (String) doc.get("name")));
            }
        } catch (Throwable err) {
            System.out.println("error in PaymentMethodDAO: " + err.toString());
        } finally {
            cursor.close();
        }
        return paymentMethodList;
    }

    public long getIndex() {
        return paymentMethod_collection.countDocuments();

    }

}
