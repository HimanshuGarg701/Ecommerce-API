package process;

import java.util.HashMap;

public class Factory {
    public static Processor makeProcessor(String[] endPoint, HashMap<String, String> endPointTask) {
        switch (endPoint[0]) {
            case ("addPaymentMethod"):
                return new PaymentMethodProcessor(endPointTask);

            case ("getAllPaymentMethods"):
                return new GetAllPaymentMethod(endPointTask);

            case ("addItem"):
                return new ItemsProcessor(endPointTask);

            case ("listItems"):
                return new GetItems(endPointTask);

            case ("createTransaction"):
                return new CreateTransactions(endPointTask);

            case ("listTransactions"):
                return new ListTransactions(endPointTask);

            default:
                return new DefaultProcessor(endPointTask);
        }
    }
}
