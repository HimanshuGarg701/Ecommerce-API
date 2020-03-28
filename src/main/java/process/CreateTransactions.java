package process;

import DAO.ItemDAO;
import DAO.PaymentMethodDAO;
import DAO.TransactionsDAO;
import DTO.DTO;
import DTO.ItemDTO;
import DTO.PaymentMethodDTO;
import DTO.ResponseDTO;
import DTO.ResponseDTO_helper;
import DTO.TransactionDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CreateTransactions implements Processor {
    HashMap<String, String> arguments;

    public CreateTransactions(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public ResponseDTO process() {
        String responseCode = "Error";
        ArrayList<DTO> response = new ArrayList<>();
        ResponseDTO_helper rbh = new ResponseDTO_helper();
        rbh.setDate(new Date().toString());
        rbh.setParameters(arguments);
        if (!(arguments.get("itemCode") == null || arguments.get("paymentMethod") == null
                || arguments.get("itemCode").equals("") || arguments.get("paymentMethod").equals(""))) {
            try {
                boolean itemIsPresent = false;
                int counter = 0;

//             check if the item exist
                ArrayList<DTO> allItems = ItemDAO.getInstance().getAllItems();
                int length = allItems.size();
                ItemDTO item = null;
                while (counter < length) {
                    item = (ItemDTO) allItems.get(counter);
                    if (arguments.get("itemCode").equals(String.valueOf(item.machineCode))) {
                        itemIsPresent = true;
                        break;
                    }
                    counter++;
                }
                boolean paymentMethodIsPresent = false;
                ArrayList<DTO> allPaymentMethods = PaymentMethodDAO.getInstance().getAllPaymentMethods();
                PaymentMethodDTO PaymentMethod = null;
                counter = 0;
                length = allPaymentMethods.size();
                while (counter < length) {
                    PaymentMethod = (PaymentMethodDTO) allPaymentMethods.get(counter);
                    if (arguments.get("paymentMethod").equals(String.valueOf(PaymentMethod.machineCode))) {
                        paymentMethodIsPresent = true;
                        break;
                    }
                    counter++;
                }

                if (itemIsPresent && paymentMethodIsPresent) {
                    responseCode = "Ok";
                    TransactionsDAO transactiondao = TransactionsDAO.getInstance();
                    TransactionDTO transaction = new TransactionDTO(transactiondao.getAllTransactions().size(), item.machineCode, PaymentMethod.machineCode);
                    response.add(transaction);
                    transactiondao.addTransaction(transaction);
                }
            } catch (Exception e) {
                responseCode = "Error";
            }
        }
        rbh.setResponseCode(responseCode);
        rbh.setResponse(response);
        return rbh.build();
    }
}
