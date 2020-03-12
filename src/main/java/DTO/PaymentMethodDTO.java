package DTO;

public class PaymentMethodDTO implements DTO {
    public final String name;
    public final int machineCode;

    public PaymentMethodDTO(int machineCode, String name) {
        this.name = name;
        this.machineCode = machineCode;
    }
}
