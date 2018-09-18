package accountBook_Javafx;

import java.util.ArrayList;
import java.util.HashSet;

public class AccountBook {

    private String username;
    private ArrayList<Transaction> transactionData = new ArrayList<Transaction>();
    private double totalIncome;
    private double totalExpense;
    private double totalBalance;

    public AccountBook(String username) {
        this.username = username;
        this.totalIncome = 0.0;
        this.totalExpense = 0.0;
        this.totalBalance = 0.0;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public ArrayList<Transaction> getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(ArrayList<Transaction> transactionData) {
        this.transactionData = transactionData;
    }

    public void addTransactionData(Transaction tran){ transactionData.add(tran); }

    public void removeTransactionData(Transaction tran){ transactionData.remove(tran); }

    public double getTotalIncome() {
        totalIncome = 0;
        for (Transaction tran:transactionData) {
            if(tran.getAmount() >= 0)
                totalIncome += tran.getAmount();
        }
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        totalExpense = 0;
        for (Transaction tran:transactionData) {
            if(tran.getAmount() <= 0)
                totalExpense += tran.getAmount();

        }
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getTotalBalance() {
        totalBalance = getTotalIncome()+getTotalExpense();
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public ArrayList<String> getDateList(){
        HashSet<String> dateSet = new HashSet<String>();
        for (Transaction d: transactionData){
            dateSet.add(d.getDate());
        }
        ArrayList<String> dateList = new ArrayList<String>(dateSet);
        return dateList;
    }

}
