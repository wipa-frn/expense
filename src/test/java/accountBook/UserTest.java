package accountBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;
    double initialBalance = 500;

    @BeforeEach
    void setUp() {
        user = new User(initialBalance);
    }

    @Test
    public void testIncomeTotalBalance() {

        user.income(200);
        user.expense(100);
        user.expense(50);
        assertEquals(550, user.getTotalBalance());
    }

    @Test
    void testExpenseTotalBalance() {
        user.expense(200);
        user.expense(200);
        user.income(300);
        assertEquals(400, user.getTotalBalance());

    }

    @Test
    void testStatementTotalBalance() {
        user.income(200);
        user.income(100);
        user.expense(300);
        user.expense(300);

        assertEquals(300,user.getTotalIncome());
        assertEquals(600,user.getTotalExpense());
        assertEquals(200, user.getTotalBalance());

    }

    @Test
    void getTotalIncomeTest() {
        user.income(200);
        user.income(100);
        assertEquals(300,user.getTotalIncome());
    }

    @Test
    void getTotalExpense() {
        user.expense(400);
        user.expense(150);
        assertEquals(550,user.getTotalExpense());
    }

    @Test
    void getTotalBalance() {
        user.income(200);
        user.income(100);
        user.expense(400);
        user.expense(150);
        assertEquals(250,user.getTotalBalance());
    }

}