package accountBook;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;

public class stepDefUser {
    User user;

    @Before
    public void setup(){
        user = new User(500);
    }

    @Given("a user have balance (.*) baht in account book")
    public void a_user_have_balance_in_account_book(double balance){
        user = new User(500);
    }

    @When("user record income (.*) baht")
    public void user_record_income(double income){ user.income(income);}

    @When("user record expense (.*) baht")
    public void user_record_expense(double expense){ user.expense(expense);}

    @Then("user account book balance is (.*) baht and total income is (.*) baht")
    public void user_account_book_balance_and_total_income_is(){
        user.getTotalBalance();
        user.getTotalIncome();
    }
    @Then("user account book balance is (.*) baht and total expense is (.*) baht")
    public void user_account_book_balance_and_total_expense_is(){
        user.getTotalBalance();
        user.getTotalExpense();
    }

}
