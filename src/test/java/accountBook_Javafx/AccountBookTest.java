package accountBook_Javafx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountBookTest {
    static ApplicationContext context = new ClassPathXmlApplicationContext("configTest.xml");

    AccountBook user = context.getBean("user", AccountBook.class);
    List<Transaction> allTransaction ;

    @BeforeEach
    void setUp() throws SQLException {
        allTransaction = user.getTransactionManager().getAllTransactions();
    }

    @Test
    void getUsername() {
        assertEquals("Wipawadee",user.getUsername());
    }

    @Test
    void getTotalIncome() throws SQLException {
        assertEquals(3500,user.getTotalIncome());
    }

    @Test
    void getTotalExpense() throws SQLException {
        assertEquals(430,user.getTotalExpense());
    }

    @Test
    void getTotalBalance() throws SQLException {
        assertEquals(3070,user.getTotalBalance());
    }

    @Test
    void getDateOrderSevenInAccountBook(){
        assertEquals("11/17/2018",allTransaction.get(6).getDate());
    }

    @Test
    void getCategoryOrderSevenInAccountBook(){
        assertEquals("Food",allTransaction.get(6).getCategory());
    }

    @Test
    void getMemoryOrderSevenInAccountBook(){
        assertEquals("eat",allTransaction.get(6).getMemory());
    }

    @Test
    void getAmountOrderSevenInAccountBook(){
        assertEquals(Double.valueOf(50.0),allTransaction.get(6).getAmount());
    }

    @Test
    void getTypeOrderSevenInAccountBook(){
        assertEquals("expense",allTransaction.get(6).getType());
    }


}