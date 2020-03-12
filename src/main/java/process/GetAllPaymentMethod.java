package process;

import DAO.PaymentMethodDAO;
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

    public ArrayList<DTO> getPayment() {
        return PaymentMethodDAO.getInstance().getAllPaymentMethods();
    }

    public ResponseDTO process() {
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        ArrayList<DTO> response = null;
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
}
