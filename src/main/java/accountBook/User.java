package accountBook;

import java.util.ArrayList;

/**
 * Created by 708 on 8/31/2018.
 */
public class User {
    private ArrayList<Double> incomeList = new ArrayList<Double>() ;
    private ArrayList<Double> expenseList = new ArrayList<Double>();
    private ArrayList<Double> balanceList = new ArrayList<Double>();
    private double totalIncome ;
    private double totalExpense ;
    private double totalBalance ;

    public User() {
        this.expenseList.add(0,0.0);
        this.incomeList.add(0,0.0);
        this.balanceList.add(0,0.0);
        totalIncome = 0 ;
        totalExpense = 0 ;
        totalBalance = 0;
    }

    public User(double initialBalance) {
        this.expenseList.add(0,0.0);
        this.incomeList.add(0,0.0);
        this.balanceList.add(0,initialBalance);
        totalIncome = 0 ;
        totalExpense = 0 ;
        totalBalance = initialBalance;
    }

    public void income(double amount){

            totalBalance += amount;
            totalIncome += amount;
            incomeList.add(amount);
            expenseList.add(0.0);
            balanceList.add(totalBalance);

    }

    public void expense(double amount){

            totalExpense += amount;
            totalBalance -= amount;
            expenseList.add(-amount);
            incomeList.add(0.0);
            balanceList.add(totalBalance);

    }
    public void statement(){
        System.out.println("               <<Statement of you account book >>");
        System.out.println("_______________________________________________________________________");
        System.out.println(String.format("|%20s|%20s|%20s|\n","Income","Expense","Balance"));
        for (int i = 0 ; i < incomeList.size() ; i++ ){
            System.out.println(String.format("|%20.2f|%20.2f|%20.2f|",incomeList.get(i),-expenseList.get(i),balanceList.get(i)));
        }
        System.out.println("_______________________________________________________________________");
        System.out.println(String.format("|%20.2f|%20.2f|%20.2f| Total ",totalIncome,-totalExpense,totalBalance));
        System.out.println("=======================================================================\n");

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
}
