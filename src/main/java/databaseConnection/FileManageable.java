package databaseConnection;

import accountBook_Javafx.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface FileManageable {
    void save(Transaction transaction);
    void update(Transaction transaction);
    void delete(Transaction transaction);
    List<Transaction> getAllTransactions() throws SQLException;
}
