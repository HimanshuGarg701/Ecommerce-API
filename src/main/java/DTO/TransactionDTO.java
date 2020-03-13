package DTO;

public class TransactionDTO implements DTO {
    public final int itemCode;
    public final int paymentMethod;
    public final long machineCode;

    public TransactionDTO(int machineCode, int itemCode, int paymentMethod) {
        this.machineCode = machineCode;
        this.itemCode = itemCode;
        this.paymentMethod = paymentMethod;
    }
}
