package accountBook_Javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static accountBook_Javafx.UserController.user;

public class EditTransactionController implements Initializable{
    @FXML private BorderPane addIncomeBorderPane;
    @FXML private Button incomeButton;
    @FXML private Button paidButton;
    @FXML private TextField amount;
    @FXML private DatePicker date;
    @FXML private TextField memo;
    @FXML private ImageView historyButton;
    @FXML private ImageView homeButton;
    @FXML private ChoiceBox<String> categoryChoices;
    @FXML private Button editTransaction;
    private Transaction transaction ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void handleButtonEditTransaction(ActionEvent event) throws IOException {

        transaction.setAmount(Double.parseDouble(amount.getText()));
        transaction.setAmountFormat();
        transaction.setMemory(memo.getText());
        transaction.setDate(date.getEditor().getText());
        transaction.setCategory(categoryChoices.getSelectionModel().getSelectedItem());

        user.getTransactionManager().update(transaction);

        //when edit finished close page
        Stage stage = (Stage) editTransaction.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleButtonIncome(ActionEvent event) {

        transaction.setType("income");
        incomeButton.setStyle("-fx-background-color: #F2D550"); //SELECT BUTTON
        paidButton.setStyle("-fx-background-color: #F2D58F");   //NON_SELECT BUTTON

        ObservableList<String> categoryList = FXCollections.observableArrayList("Salary", "Refunds","Sale","Other");
        categoryChoices.setValue("Salary");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.getItems().setAll(categoryList);
    }

    @FXML
    void handleButtonPaid(ActionEvent event) {

        transaction.setType("expense");
        paidButton.setStyle("-fx-background-color: #F2D550");       //SELECT BUTTON
        incomeButton.setStyle("-fx-background-color: #F2D58F");   //NON_SELECT BUTTON

        ObservableList<String> categoryList = FXCollections.observableArrayList("Food", "Transportation", "Shopping", "Health", "Other");
        categoryChoices.setValue("Food");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.getItems().setAll(categoryList);
    }

    public void setTransaction(Transaction transaction) {

        this.transaction = transaction;
        setUpTransaction();
    }

    public void setUpTransaction(){

        if(transaction.getType().equals("expense")){
            paidButton.setStyle("-fx-background-color: #F2D550");       //SELECT BUTTON
            incomeButton.setStyle("-fx-background-color: #F2D58F");   //NON_SELECT BUTTON

            ObservableList<String> categoryList = FXCollections.observableArrayList("Food", "Transportation","Shopping","Health","Other");
            categoryChoices.getSelectionModel().selectFirst();
            categoryChoices.setValue(transaction.getCategory());
            categoryChoices.getItems().setAll(categoryList);
        }
        else {
            incomeButton.setStyle("-fx-background-color: #F2D550");       //SELECT BUTTON
            paidButton.setStyle("-fx-background-color: #F2D58F");   //NON_SELECT BUTTON

            ObservableList<String> categoryList = FXCollections.observableArrayList("Salary", "Refunds","Sale","Other");
            categoryChoices.getSelectionModel().selectFirst();
            categoryChoices.setValue(transaction.getCategory());
            categoryChoices.getItems().setAll(categoryList);
        }

        amount.setText(String.valueOf(transaction.getAmount()));
        String[] dateCut = transaction.getDate().split("/");
        date.setValue(LocalDate.of(Integer.valueOf(dateCut[2]),Integer.valueOf(dateCut[0]),Integer.valueOf(dateCut[1])));
        memo.setText(transaction.getMemory());
    }
}
