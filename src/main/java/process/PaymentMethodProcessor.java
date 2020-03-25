package process;

//import DAO.PaymentMethodDAO;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import DTO.DTO;
import DTO.PaymentMethodDTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;
import process.Processor;

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
        ArrayList<DTO> response = new ArrayList<>();
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
//            PaymentMethodDAO paymentmethoddao = PaymentMethodDAO.getInstance();
//            PaymentMethodDTO paymentMethod = new PaymentMethodDTO(paymentmethoddao.getIndex(), arguments.get("method"));
//            paymentmethoddao.addPaymentMethod(paymentMethod);


            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("csc-415");
            MongoCollection<Document> myColection = db.getCollection("PaymentMethod");
            Document doc = new Document("name", arguments.get("method"));
            myColection.insertOne(doc);




//            response.add(paymentMethod);
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "Error";
        }
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }


    public static void main(String[] args) {
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("method","blah");
        PaymentMethodProcessor p = new PaymentMethodProcessor(arg);
        p.process();

    }
}

