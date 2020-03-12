package process;

import DTO.DTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DefaultProcessor implements Processor {

    HashMap<String, String> arguments;

    public DefaultProcessor(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }


    @Override
    public ResponseDTO process() {
        String responseCode;
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        ArrayList<DTO> response = null;
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        rbh.setResponseCode("ERROR");
        rbh.setResponse(response);
        return rbh.build();
    }
}
