package process;

import DAO.ItemDAO;
import DTO.DTO;
import DTO.ResponseDTO;
import DTO.ItemDTO;
import DTO.ResponseDTO_helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ItemsProcessor implements Processor {

    HashMap<String, String> arguments;

    public ItemsProcessor(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public ResponseDTO process() {
        String responseCode;
        ArrayList<DTO> response = new ArrayList<>();
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        try {
            ItemDAO itemdao = ItemDAO.getInstance();
            ItemDTO item = new ItemDTO(itemdao.getIndex(), arguments.get("name"), (arguments.get("price")));
            itemdao.addItem(item);
            response.add(item);
            responseCode = "OK";
        } catch (Exception e) {
            responseCode = "Error";
        }
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }
}
