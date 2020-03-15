package DAO;

import DTO.DTO;
import DTO.PaymentMethodDTO;

import java.util.ArrayList;

public class PaymentMethodDAO {
    private static PaymentMethodDAO InstancePaymentDao;

    private PaymentMethodDAO() {
        //Empty Constructor
    }

    /*
        Created an ArrayList of payment methods.
        This ArrayList will store all PaymentMethod objects.
        Each PaymentMethod object holds a name and a machine code.
     */
    private ArrayList<DTO> paymentMethodList = new ArrayList<>();

    // To get the instance of PaymentMethodDao
    public static PaymentMethodDAO getInstance() {
        if (InstancePaymentDao == null) {
            InstancePaymentDao = new PaymentMethodDAO();
        }
        return InstancePaymentDao;
    }

    /*
        The addPaymentMethod(String methodName) accepts the type of payment method. (Also name parameter of PaymentMethodClass)
        Since the PaymentMethod class accepts the method name, So I created new object for each payment method
        and stored all these methods in the ArrayList.
     */
    public void addPaymentMethod(PaymentMethodDTO methodName) {
        paymentMethodList.add(methodName);
    }

    /*
        The getAllPaymentMethods() simply returns the list of PaymentMethods.
     */
    public ArrayList<DTO> getAllPaymentMethods() {
        return paymentMethodList;
    }

    public int getIndex() {
        return paymentMethodList.size();
    }

}
