import static spark.Spark.*;

import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;
import process.*;
import spark.Request;
import spark.Response;

public class Main {

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

        //final HashMap<String, String> z;
        get("/addItem", Main::addItem);
        get("/addPaymentMethod", Main::addPaymentMethod);
        get("/createTransaction", Main::createTransaction);
        get("/listTransactions", Main::listTransactions);
        get("/getAllPaymentMethods", Main::getPayments);
        get("/listItems", Main::listItems);
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
