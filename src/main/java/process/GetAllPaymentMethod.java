package process;

import Mongo_db.MongoDB_consts;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import DTO.DTO;
import DTO.PaymentMethodDTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GetAllPaymentMethod implements Processor {
    Gson gson = new Gson();
    HashMap<String, String> arguments;

    public GetAllPaymentMethod(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<Document> getPayment() {
        ArrayList<Document> paymentMethodList = new ArrayList<>();

        MongoDB_consts mdb_const = new MongoDB_consts();

        MongoClient mongoClient = new MongoClient(mdb_const.host, mdb_const.port);
        MongoDatabase db = mongoClient.getDatabase(mdb_const.db_name);
        MongoCollection<Document> myColection = db.getCollection(mdb_const.paymentMethod_col);
        MongoCursor<Document> cursor = myColection.find().iterator();
        try {
            while (cursor.hasNext()) {
                paymentMethodList.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return paymentMethodList;
    }

    public ResponseDTO process() {
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        ArrayList<Document> response = null;
        String responseCode = "";
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            response = this.getPayment();
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "ERROR";
        }
        rbh.setResponse(response);
        rbh.setResponseCode(responseCode);
        return rbh.build();
    }


//    public static void main(String[] args) {
//        HashMap<String, String> arg = new HashMap<String, String>();
//        arg.put("method"," new blah");
//        GetAllPaymentMethod p = new GetAllPaymentMethod(arg);
//        p.process();
//    }

}
