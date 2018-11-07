package accountBook_Javafx;

import databaseConnection.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseFile implements SaveFile{
    Transaction transaction = null ;

    public DatabaseFile(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void save() {
        //Auto save file to SQLite
        Connection connection = DbConnect.getConnection();
        PreparedStatement pst = null;

        String sql = "insert into transaction_data (date ,category , memory ,amount ,type) values(?,?,?,?,?)";

            String dateStr = transaction.getDate();
            String categoryStr = transaction.getCategory();
            String memoryStr = transaction.getMemory();
            Double amount = transaction.getAmount();
            String typeStr = transaction.getType();


            try{
                pst = connection.prepareStatement(sql);

                pst.setString(1,dateStr);
                pst.setString(2,categoryStr);
                pst.setString(3,memoryStr);
                pst.setDouble(4,amount);
                pst.setString(5,typeStr);

                pst.executeUpdate();
                System.out.println(" Data insert successfully! ");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                    System.out.println("Close database");
            }   catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
}
