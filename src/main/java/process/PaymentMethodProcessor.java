package process;

import DAO.PaymentMethodDAO;
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
        ArrayList<DTO> response = new ArrayList<>();
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            PaymentMethodDAO paymentmethoddao = PaymentMethodDAO.getInstance();
            PaymentMethodDTO paymentMethod = new PaymentMethodDTO(paymentmethoddao.getIndex(), arguments.get("method"));
            response.add(paymentMethod);
            paymentmethoddao.addPaymentMethod(paymentMethod);
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "Error";
        }
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }
}
