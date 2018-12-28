package accountBook_Javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import static accountBook_Javafx.UserController.user;

public class AddTransactionController implements Initializable{

    @FXML private BorderPane addIncomeBorderPane;
    @FXML private Button incomeButton;
    @FXML private Button paidButton;
    @FXML private TextField amount;
    @FXML private TextField memo;
    @FXML private DatePicker date;
    @FXML private ImageView historyButton;
    @FXML private ImageView homeButton;
    @FXML private ChoiceBox<String> categoryChoices;
    Transaction transaction = new Transaction();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transaction.setType("expense");
        amount.setPromptText("0.00");
        date.setValue(LocalDate.now());
        ObservableList<String> categoryList = FXCollections.observableArrayList("Food", "Transportation","Shopping","Health","Other");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.setValue("Food");
        categoryChoices.getItems().setAll(categoryList);
        memo.setPromptText("Enter your memory");

    }
    @FXML
    void handleButtonAddTransaction(ActionEvent event) throws IOException {

        transaction.setAmount(Double.parseDouble(amount.getText()));
        transaction.setAmountFormat();
        transaction.setDate(date.getEditor().getText());
        transaction.setCategory(categoryChoices.getSelectionModel().getSelectedItem());
        transaction.setMemory(memo.getText());
        user.getTransactionManager().save(transaction);

        //After add finish go to history
        FXMLLoader loader = new FXMLLoader();
        historyButton.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/history.fxml"));
        Scene scene = new Scene(root);
        homeWindow.setScene(scene);
        homeWindow.show();

    }

    @FXML
    void handleButtonIncome(ActionEvent event) {

        transaction.setType("income");
        incomeButton.setStyle("-fx-background-color: #F2D550"); //SELECT BUTTON
        paidButton.setStyle("-fx-background-color: #F2D58F");   //NON_SELECT BUTTON

        ObservableList<String> categoryList = FXCollections.observableArrayList("Salary", "Refunds","Sale","Other");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.setValue("Salary");
        categoryChoices.getItems().setAll(categoryList);

    }

    @FXML
    void handleButtonPaid(ActionEvent event){
        transaction.setType("expense");
        paidButton.setStyle("-fx-background-color: #F2D550");   //SELECT BUTTON
        incomeButton.setStyle("-fx-background-color: #F2D58F"); //NON_SELECT BUTTON

        ObservableList<String> categoryList = FXCollections.observableArrayList("Food", "Transportation","Shopping","Health","Other");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.setValue("Food");
        categoryChoices.getItems().setAll(categoryList);

    }

    @FXML
    void handleClickHistoryButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        historyButton.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/history.fxml"));
        Scene scene = new Scene(root);
        homeWindow.centerOnScreen();
        homeWindow.setScene(scene);
        homeWindow.show();
    }

    @FXML
    void handleClickHomeButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        homeButton.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/home.fxml"));
        Scene scene = new Scene(root);
        homeWindow.setScene(scene);
        homeWindow.centerOnScreen();
        homeWindow.show();
    }
}
