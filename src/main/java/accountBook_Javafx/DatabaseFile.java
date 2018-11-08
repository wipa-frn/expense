package accountBook_Javafx;

import databaseConnection.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFile implements FileManageable {
    Transaction transaction = null ;
    Connection connection = DbConnect.getConnection();


    public DatabaseFile(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void save() {
        //Auto save file to SQLite
        PreparedStatement pst ;
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

    @Override
    public void update() {
        //edit data in SQLite
        PreparedStatement pst ;
        String sql = "Update transaction_data set date = ?,category = ? , memory = ? ,amount = ?,type = ?)";
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete() {
        PreparedStatement pst ;
        String sql = "Delete from transaction_data where order_id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,transaction.getOrder());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                System.out.println("Close database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
