package accountBook;

import java.util.Scanner;

/**
 * Created by 708 on 8/31/2018.
 */
public class AccountBookConsoleUI {

    public void start(){
        String command;

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Account Book...");
        System.out.print("Enter your total balance in account book : ");
        command=in.next();

        User user = new User(Double.parseDouble(command));

        System.out.println("Please press i = income , e = expense ,s = show your statement ,q = quit");

        command= in.next();

        while (!command.equalsIgnoreCase("q")) {

            if(!command.equalsIgnoreCase("i")&&!command.equalsIgnoreCase("e")
                    &&!command.equalsIgnoreCase("s")&&!command.equalsIgnoreCase("q")){
                System.out.println("Input incorrect please try again.");
                System.out.println("Please press i = income , e = expense ,s = show your statement ,q = quit");
                command = in.next();
                continue;
            }
            else if (command.equalsIgnoreCase("i")) {
                System.out.print("Amount income: ");
                double amount = in.nextDouble();
                while(amount <= 0){
                    System.out.println("Amount incorrect please try again.");
                    System.out.print("Amount income: ");
                    amount = in.nextDouble();
                }
                if (amount > 0)
                    user.income(amount);

            } else if (command.equalsIgnoreCase("e")) {
                System.out.print("Amount expense: ");
                double amount = in.nextDouble();
                while(amount <= 0){
                    System.out.println("Amount incorrect please try again.");
                    System.out.print("Amount expense: ");
                    amount = in.nextDouble();
                }
                if(amount > 0)
                    user.expense(amount);

            } else if (command.equalsIgnoreCase("s")) {
                String statementStr;
                statementStr = "                    <<Statement of you account book >>\n"
                        + "____________________________________________________________________________\n" +
                        String.format("|%5s|%20s|%20s|%20s|\n\n","Order", "Income", "Expense", "Balance");
                for (int i = 0; i < user.getIncomeList().size(); i++) {
                    statementStr += String.format("|%5s|%20.2f|%20.2f|%20.2f|\n", i,user.getIncomeList().get(i), user.getExpenseList().get(i), user.getBalanceList().get(i));
                }
                statementStr += "____________________________________________________________________________\n" +
                        String.format("       %20.2f|%20.2f|%20.2f| Total \n", user.getTotalIncome(), -user.getTotalExpense(), user.getTotalBalance()) +
                        "============================================================================\n";
                System.out.println(statementStr);

            }
            System.out.println("Please press i = income , e = expense ,s = show your statement ,q = quit");
            command = in.next();
        }
        if (command.equalsIgnoreCase("q")) {
            System.out.print("Account Book closed.");
            return;
        }


    }
}
