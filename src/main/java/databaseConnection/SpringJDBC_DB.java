package databaseConnection;

import accountBook_Javafx.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpringJDBC_DB implements FileManageable {
    private JdbcTemplate jdbcTemplate;

    public SpringJDBC_DB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Transaction transaction) {
        String query = "insert into transaction_data (date ,category , memory ,amount ,type) values (?,?,?,?,?)";
        Object[] data = new Object[]
                {transaction.getDate(), transaction.getCategory(), transaction.getMemory(), transaction.getAmount(), transaction.getType()};
        jdbcTemplate.update(query, data);
    }

    @Override
    public void update(Transaction transaction) {
        String updateQuery = "update transaction_data set date = ?, category = ? ,memory = ? ,amount = ? ,type = ? where order_id = ?";
        Object[] data = new Object[]
                {transaction.getDate(), transaction.getCategory() ,transaction.getMemory() ,transaction.getAmount() ,transaction.getType(),transaction.getOrder()};

        jdbcTemplate.update(updateQuery,data);
    }

    @Override
    public void delete(Transaction transaction) {
        String deleteQuery = "Delete From transaction_data Where order_id = ?";
        jdbcTemplate.update(deleteQuery, transaction.getOrder());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        String query = "select * from transaction_data";

        List<Transaction> transactionList = jdbcTemplate.query(query,new TransactionRowMapper());
        return transactionList;
    }

    class TransactionRowMapper implements RowMapper<Transaction> {
        public Transaction mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Transaction transaction = new Transaction(
                    rs.getInt("order_id"),
                    rs.getString("date"),
                    rs.getString("category"),
                    rs.getString("memory"),
                    rs.getDouble("amount"),
                    rs.getString("type"));
            return transaction;
        }
//    Transaction transaction = null ;
//    Connection connection = DbConnect.getConnection();
//
//
//    public SpringJDBC_DB(Transaction transaction) {
//        this.transaction = transaction;
//    }
//
//    @Override
//    public void save() {
//        //Auto save file to SQLite
//        PreparedStatement pst ;
//        String sql = "insert into transaction_data (date ,category , memory ,amount ,type) values(?,?,?,?,?)";
//
//            String dateStr = transaction.getDate();
//            String categoryStr = transaction.getCategory();
//            String memoryStr = transaction.getMemory();
//            Double amount = transaction.getAmount();
//            String typeStr = transaction.getType();
//
//
//            try{
//                pst = connection.prepareStatement(sql);
//
//                pst.setString(1,dateStr);
//                pst.setString(2,categoryStr);
//                pst.setString(3,memoryStr);
//                pst.setDouble(4,amount);
//                pst.setString(5,typeStr);
//
//                pst.executeUpdate();
//                System.out.println(" Data insert successfully! ");
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    connection.close();
//                    System.out.println("Close database");
//            }   catch (SQLException e) {
//                    e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void update() {
//        //edit data in SQLite
//        PreparedStatement pst ;
//        String sql = "Update transaction_data set date = ?,category = ? , memory = ? ,amount = ?,type = ?)";
//        String dateStr = transaction.getDate();
//        String categoryStr = transaction.getCategory();
//        String memoryStr = transaction.getMemory();
//        Double amount = transaction.getAmount();
//        String typeStr = transaction.getType();
//
//        try{
//            pst = connection.prepareStatement(sql);
//            pst.setString(1,dateStr);
//            pst.setString(2,categoryStr);
//            pst.setString(3,memoryStr);
//            pst.setDouble(4,amount);
//            pst.setString(5,typeStr);
//            pst.executeUpdate();
//            System.out.println(" Data insert successfully! ");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                connection.close();
//                System.out.println("Close database");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void delete() {
//        PreparedStatement pst ;
//        String sql = "Delete from transaction_data where order_id = ?";
//        try {
//            pst = connection.prepareStatement(sql);
//            pst.setInt(1,transaction.getOrder());
//            pst.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                connection.close();
//                System.out.println("Close database");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    }
}
