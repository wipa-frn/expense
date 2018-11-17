package accountBook_Javafx;

import databaseConnection.FileManageable;

import java.sql.SQLException;
import java.util.List;

public class AccountBook {

    private String username;
    private double totalIncome;
    private double totalExpense;
    private double totalBalance;
    private List<Transaction> transactionList;
    private FileManageable transactionManager;

    public AccountBook(String username,FileManageable transactionManager) {
        this.username = username;
        this.totalIncome = 0.0;
        this.totalExpense = 0.0;
        this.totalBalance = 0.0;
        this.transactionManager = transactionManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalIncome() throws SQLException {
        calTotal();
        return totalIncome;
    }

    public double getTotalExpense() throws SQLException {
        calTotal();
        return totalExpense;
    }

    public double getTotalBalance() throws SQLException {
        calTotal();
        return totalBalance;
    }

    void calTotal() throws SQLException {
        totalBalance=0;
        totalIncome=0;
        totalExpense=0;
        transactionList = transactionManager.getAllTransactions();

        for (Transaction t:transactionList ) {
            if (t.getType().equals("income")){
                totalIncome += t.getAmount();
            }
            else if (t.getType().equals("expense"))
                totalExpense += t.getAmount();
        }

        totalBalance = totalIncome-totalExpense;
    }

    public FileManageable getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(FileManageable transactionManager) {
        this.transactionManager = transactionManager;
    }
}
