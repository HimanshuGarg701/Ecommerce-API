package DAO;

import DTO.DTO;

import java.util.ArrayList;

public class TransactionsDAO {
    private static TransactionsDAO InstanceTransactionsDAO;

    private TransactionsDAO() {
        //Empty Constructor
    }

    private ArrayList<DTO> transactions = new ArrayList<>();

    public static TransactionsDAO getInstance() {
        if (InstanceTransactionsDAO == null) {
            InstanceTransactionsDAO = new TransactionsDAO();
        }
        return InstanceTransactionsDAO;
    }

    public void addTransaction(DTO transaction) {
        transactions.add(transaction);
    }

    public ArrayList<DTO> getAllTransactions() {
        return this.transactions;
    }
}
