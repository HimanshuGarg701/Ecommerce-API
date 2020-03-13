package process;

import Mongo_db.MongoDB_consts;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import DTO.DTO;
import DTO.PaymentMethodDTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PaymentMethodProcessor implements Processor {

    HashMap<String, String> arguments;

    public PaymentMethodProcessor(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }


    public ResponseDTO process() {
        String responseCode;
        ArrayList<Document> response = new ArrayList<>();
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            MongoDB_consts mdb_const = new MongoDB_consts();

            MongoClient mongoClient = new MongoClient(mdb_const.host, mdb_const.port);
            MongoDatabase db = mongoClient.getDatabase(mdb_const.db_name);
            MongoCollection<Document> myColection = db.getCollection(mdb_const.paymentMethod_col);
            long machine_code = myColection.count();
            Document doc = new Document("machine_code",machine_code).append("name", arguments.get("method"));
            myColection.insertOne(doc);
            response.add(doc);
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "Error";
        }
        System.out.println(response);
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }


//    public static void main(String[] args) {
//        HashMap<String, String> arg = new HashMap<String, String>();
//        arg.put("method"," new blah");
//        PaymentMethodProcessor p = new PaymentMethodProcessor(arg);
//        System.out.println(p.process());
//
//    }
}
