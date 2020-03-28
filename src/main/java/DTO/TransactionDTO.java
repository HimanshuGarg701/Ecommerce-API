package DTO;

public class TransactionDTO implements DTO {
    public final long itemCode;
    public final long paymentMethod;
    public final long machineCode;

    public TransactionDTO(long machineCode, long itemCode, long paymentMethod) {
        this.machineCode = machineCode;
        this.itemCode = itemCode;
        this.paymentMethod = paymentMethod;
    }
}
