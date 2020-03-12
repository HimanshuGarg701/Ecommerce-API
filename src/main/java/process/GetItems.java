package process;

import DAO.ItemDAO;
import DTO.DTO;
import DTO.ItemDTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GetItems implements Processor {

    HashMap<String, String> arguments;
    Gson gson = new Gson();

    public GetItems(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<DTO> getItem() {
        return ItemDAO.getInstance().getAllItems();
    }

    public ResponseDTO process() {
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        ArrayList<DTO> response = null;
        String responseCode;
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            response = this.getItem();
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "ERROR";
        }
        rbh.setResponse(response);
        rbh.setResponseCode(responseCode);
        return rbh.build();

    }
}