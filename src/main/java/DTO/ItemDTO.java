package DTO;

public class ItemDTO implements DTO {
    public final String name;
    public final double price;
    public final long machineCode;

    public ItemDTO(int machineCode, String name, double price) {
        this.name = name;
        this.price = price;
        this.machineCode = machineCode;
    }

}
