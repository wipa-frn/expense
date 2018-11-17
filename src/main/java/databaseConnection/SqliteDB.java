package databaseConnection;

import accountBook_Javafx.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteDB implements FileManageable{

    @Override
    public void save(Transaction transaction) {
        Connection connection = DbConnect.getConnection();
        String query = "insert into transaction_data (date ,category , memory ,amount ,type) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,transaction.getDate());
            pst.setString(2,transaction.getCategory());
            pst.setString(3,transaction.getMemory());
            pst.setDouble(4,transaction.getAmount());
            pst.setString(5,transaction.getType());
            pst.executeUpdate();
            System.out.println("Insert transaction success.");
            pst.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't insert transaction.");
        }


    }

    @Override
    public void update(Transaction transaction) {
        Connection connection = DbConnect.getConnection();
        String updateQuery = "update transaction_data set date = ?, category = ? ,memory = ? ,amount = ? ,type = ? where order_id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(updateQuery);
            pst.setString(1,transaction.getDate());
            pst.setString(2,transaction.getCategory());
            pst.setString(3,transaction.getMemory());
            pst.setDouble(4,transaction.getAmount());
            pst.setString(5,transaction.getType());
            pst.setInt(6,transaction.getOrder());
            pst.executeUpdate();
            pst.close();
            connection.close();
            System.out.println("Update transaction success.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't update transaction.");
        }

    }

    @Override
    public void delete(Transaction transaction)  {
        Connection connection = DbConnect.getConnection();
        String deleteQuery = "Delete from transaction_data where order_id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(deleteQuery);
            pst.setInt(1,transaction.getOrder());
            pst.executeUpdate();
            pst.close();
            connection.close();
            System.out.println("Delete transaction success.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't delete transaction.");
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        Connection connection = DbConnect.getConnection();
        String query = "select * from transaction_data";
        List<Transaction> transactionList = new ArrayList<Transaction>();

        try {
            ResultSet rs = connection.createStatement().executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    transactionList.add(new Transaction(rs.getInt("order_id"), rs.getString("date"), rs.getString("category"), rs.getString("memory"), rs.getDouble("amount"), rs.getString("type")));
                }
                rs.close();
                connection.close();
                System.out.println("Return all data success.");
            }



        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't return all data.");
        }


        return transactionList;
    }
}
