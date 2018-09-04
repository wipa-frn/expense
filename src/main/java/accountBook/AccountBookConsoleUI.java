package accountBook;

import java.util.Scanner;

/**
 * Created by 708 on 8/31/2018.
 */
public class AccountBookConsoleUI {
    public void start(){
        Scanner in = new Scanner(System.in);
        User user = new User();
        System.out.println("Welcome to Account Book...");
        System.out.println("Please press i = income , e = expense ,s = show your statement ,q = quit");
        String command;

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
                user.statement();
            }
            System.out.println("Please press i = income , e = expense ,s = show your statement ,q = quit");
            command = in.next();
        }
        if (command.equalsIgnoreCase("q")) {
            System.out.print("Account Book close...");
            return;
        }
    }

}
