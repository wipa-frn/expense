package databaseConnection;

import accountBook_Javafx.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static accountBook_Javafx.UserController.user;

public class TextFile implements FileManageable {

    public void saveAllTransaction(List<Transaction> TransactionList) {
        //save file to .txt

        try {
            FileWriter fw = new FileWriter("historyFile.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Transaction t : TransactionList) {
                fw.write("Order: " + t.getOrder() + ", ");
                fw.write("Date: " + t.getDate() + ", ");
                fw.write("Category: " + t.getCategory() + ", ");
                fw.write("Memory: " + t.getMemory() + ", " );
                fw.write(String.valueOf("Amount: " + t.getAmountFormat() + ", "));
                fw.write("Type: " + t.getType() + "\n");

            }
            System.out.println("Write on file success.");
            fw.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("Write on file not success.");
        }

    }


    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Transaction transaction) {

    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }


}

