package accountBook;

import java.util.ArrayList;

/**
 * Created by 708 on 8/31/2018.
 */
public class User {
    private ArrayList<Double> incomeList = new ArrayList<Double>();
    private ArrayList<Double> expenseList = new ArrayList<Double>();
    private ArrayList<Double> balanceList = new ArrayList<Double>();
    private double totalIncome;
    private double totalExpense;
    private double totalBalance;

    public User() {
        this.expenseList.add(0, 0.0);
        this.incomeList.add(0, 0.0);
        this.balanceList.add(0, 0.0);
        totalIncome = 0;
        totalExpense = 0;
        totalBalance = 0;
    }

    public User(double initialBalance) {
        this.expenseList.add(0, 0.0);
        this.incomeList.add(0, 0.0);
        this.balanceList.add(0, initialBalance);
        totalIncome = 0;
        totalExpense = 0;
        totalBalance = initialBalance;
    }

    public void income(double amount) {

        totalBalance += amount;
        totalIncome += amount;
        incomeList.add(amount);
        expenseList.add(0.0);
        balanceList.add(totalBalance);

    }

    public void expense(double amount) {

        totalExpense += amount;
        totalBalance -= amount;
        expenseList.add(amount);
        incomeList.add(0.0);
        balanceList.add(totalBalance);

    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public ArrayList<Double> getIncomeList() {
        return incomeList;
    }

    public ArrayList<Double> getExpenseList() {
        return expenseList;
    }

    public ArrayList<Double> getBalanceList() {
        return balanceList;
    }
}
