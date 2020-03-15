package DAO;

import DTO.DTO;
import DTO.ItemDTO;
import DTO.PaymentMethodDTO;

import java.util.ArrayList;

public class ItemDAO {
    public ArrayList<DTO> itemList = new ArrayList<>();
    private static ItemDAO instanceItemDAO = new ItemDAO();

    private ItemDAO() {
    }

    public static ItemDAO getInstance() {
        if (instanceItemDAO == null) {
            instanceItemDAO = new ItemDAO();
        }
        return instanceItemDAO;
    }

    public void addItem(ItemDTO item) {
        itemList.add(item);
    }

    public ArrayList<DTO> getAllItems() {
        return itemList;
    }

    public int getIndex() {
        return itemList.size();
    }


}
