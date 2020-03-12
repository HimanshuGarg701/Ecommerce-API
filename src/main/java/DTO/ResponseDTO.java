package DTO;

import java.util.ArrayList;
import java.util.HashMap;
import org.bson.Document;

public class ResponseDTO implements DTO {
    public final String date;
    public final HashMap<String, String> params;
    public final String responseCode;
    public final ArrayList<Document> response;

    public ResponseDTO(String date, HashMap<String, String> params, String responseCode, ArrayList<Document> response) {
        this.date = date;
        this.params = params;
        this.responseCode = responseCode;
        this.response = response;
    }
}
