package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ResponseDTO_helper {
    private String date;
    private HashMap<String, String> params;
    private String responseCode;
    private ArrayList<DTO> response;

    public ResponseDTO_helper() {
        //Empty Constructor
    }

    public void setParameters(HashMap<String, String> params) {
        this.params = params;
    }

    public void setResponse(ArrayList<DTO> response) {
        this.response = response;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ResponseDTO build() {
        return (new ResponseDTO(date, params, responseCode, response));

    }


}
