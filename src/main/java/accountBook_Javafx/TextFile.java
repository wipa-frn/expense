package accountBook_Javafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static accountBook_Javafx.UserController.user;

public class TextFile implements SaveFile {
    ArrayList transactionList;
    public TextFile(ArrayList tstList) {
        this.transactionList = tstList;
    }

    @Override
    public void save() {
        //save file to .txt
        try {
            FileWriter fw = new FileWriter("historyFile.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Transaction t: user.getTransactionData()) {
                fw.write("DATE: "+t.getDate()+", ");
                fw.write("CATEGORY: "+t.getCategory()+", ");
                fw.write(String.valueOf("AMOUNT: "+t.getAmountFormat()+", "));
                fw.write("MEM: "+t.getMemory()+" //");
            }
            System.out.println("Write on file success.");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR");
        }
    }
}
