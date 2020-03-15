import static spark.Spark.*;

import DTO.ResponseDTO;
import builder.ResponseBuilder;
import builder.ResponseDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;
import process.*;
import spark.Request;
import spark.Response;

public class SparkDemo {

    public static String processRoute(Request req, Response res) {
        Set<String> params = req.queryParams();
        for (String param : params) {
            // possible for query param to be an array
            System.out.println(param + " : " + req.queryParamsValues(param)[0]);
        }
        // do stuff with a mapped version http://javadoc.io/doc/com.sparkjava/spark-core/2.8.0
        // http://sparkjava.com/documentation#query-maps
        // print the id query value
        System.out.println(req.queryMap().get("id").value());
        return "done!";
    }

    public static HashMap<String, String> factory(Request req, Response res) {
        Set<String> params = req.queryParams();
        HashMap<String, String> map = new HashMap<>();
        for (String param : params) {
            map.put(param, req.queryParamsValues(param)[0]);
        }
        return map;
    }

    public static void main(String[] args) {
        port(1234);
        // calling get will make your app start listening for the GET path with the /hello endpoint
        ResponseBuilder builder = new ResponseBuilder()
                .setDate(new Date())
                .setName("Hello world");
        ResponseDto dto = builder.build();


        //final HashMap<String, String> z;
        get("/addItem", SparkDemo::addItem);
        get("/addPaymentMethod", SparkDemo::addPaymentMethod);
        get("/createTransaction", SparkDemo::createTransaction);
        get("/listTransactions", SparkDemo::listTransactions);
        get("/getAllPaymentMethods", SparkDemo::getPayments);
        get("/listItems", SparkDemo::listItems);
    }

    private static Object addItem(Request req, Response res) {
        return new Gson().toJson(new ItemsProcessor(factory(req, res)).process());
    }

    private static Object addPaymentMethod(Request req, Response res) {
        return new Gson().toJson(new PaymentMethodProcessor(factory(req, res)).process());
    }

    private static Object createTransaction(Request req, Response res) {
        return new Gson().toJson(new CreateTransactions(factory(req, res)).process());
    }

    private static Object listTransactions(Request req, Response res) {
        return new Gson().toJson(new ListTransactions(factory(req, res)).process());
    }

    private static Object getPayments(Request req, Response res) {
        return new Gson().toJson(new GetAllPaymentMethod(factory(req, res)).process());
    }

    private static Object listItems(Request req, Response res) {
        return new Gson().toJson(new GetItems(factory(req, res)).process());
    }
}
