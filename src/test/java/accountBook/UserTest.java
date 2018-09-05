package accountBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user,user2;
    double initialBalance = 500;

    @BeforeEach
    void setUp() {
        user = new User(initialBalance);
        user2 = new User();
    }

    @Test
    public void setUpInitialBalanceTest(){
        assertEquals(500,user.getTotalBalance());
    }
    @Test
    public void nonSetUpInitialBalanceTest(){
        assertEquals(0,user2.getTotalBalance());
    }

    @Test
    public void incomeTotalBalanceTest() {

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
    void statementTotalBalanceTest() {
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
    void getTotalExpenseTest() {
        user.expense(400);
        user.expense(150);
        assertEquals(550,user.getTotalExpense());
    }

    @Test
    void getTotalBalanceTest() {
        user.income(200);
        user.income(100);
        user.expense(400);
        user.expense(150);
        assertEquals(250,user.getTotalBalance());
    }


    @Test
    void getTotalBalanceAtThirdTransaction(){

        user.income(200);
        user.income(50);
        user.expense(400);
        user.expense(150);
        assertEquals(350,Double.parseDouble(String.valueOf(user.getBalanceList().get(3))));
    }

    @Test
    void getAmountIncomeAtThirdTransaction(){

        user.expense(400);
        user.expense(150);
        user.income(200);
        user.income(50);

        assertEquals(200,Double.parseDouble(String.valueOf(user.getIncomeList().get(3))));
    }

    @Test
    void getAmountExpenseAtThirdTransaction(){

        user.expense(400);
        user.expense(150);
        user.income(200);
        user.income(50);

        assertEquals(0,Double.parseDouble(String.valueOf(user.getExpenseList().get(3))));
    }


}