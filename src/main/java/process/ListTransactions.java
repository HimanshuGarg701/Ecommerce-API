package process;

import DAO.ItemDAO;
import DAO.TransactionsDAO;
import DTO.DTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListTransactions implements Processor {

    HashMap<String, String> arguments;

    public ListTransactions(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<DTO> getTransactions() {
        return TransactionsDAO.getInstance().getAllTransactions();
    }

    @Override
    public ResponseDTO process() {
        String responseCode;
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        ArrayList<DTO> response = null;
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            response = this.getTransactions();
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "Error";
        }
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }
}
