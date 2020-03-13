package DTO;

public class PaymentMethodDTO implements DTO {
    public final String name;
    public final long machineCode;

    public PaymentMethodDTO(long machineCode, String name) {
        this.name = name;
        this.machineCode = machineCode;
    }
}
