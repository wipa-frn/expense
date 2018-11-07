package accountBook_Javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddIncomeController implements Initializable{

    @FXML private BorderPane addIncomeBorderPane;
    @FXML private Button incomeButton;
    @FXML private Button paidButton;
    @FXML private TextField amount;
    @FXML private Button enter;
    @FXML private Button salary;
    @FXML private Button pocket_money;
    @FXML private Button refund;
    @FXML private Button other;
    @FXML private TextField memo;
    @FXML private DatePicker date;
    @FXML private ImageView historyButton;
    @FXML private ImageView homeButton;
    Transaction transaction = new Transaction();
    SaveFile saveFile;

    @FXML
    void handleButtonAddTransaction(ActionEvent event) throws IOException {

        transaction.setAmount(Double.parseDouble(amount.getText()));
        transaction.setMemory(memo.getText());
        transaction.setDate(date.getEditor().getText());
        transaction.setType("income");
        saveFile = new DatabaseFile(transaction);
        saveFile.save();


        //After add finish
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

    }

    @FXML
    void handleButtonOther(ActionEvent event) {
        transaction.setCategory("Other");
    }

    @FXML
    void handleButtonPaid(ActionEvent event) throws IOException {

        BorderPane panePaid = FXMLLoader.load(getClass().getResource("/addPaid.fxml"));
        addIncomeBorderPane.getChildren().setAll(panePaid);
    }

    @FXML
    void handleButtonPocketMoney(ActionEvent event) {
        transaction.setCategory("Pocket Money");
    }

    @FXML
    void handleButtonRefund(ActionEvent event) {
        transaction.setCategory("Refund");
    }

    @FXML
    void handleButtonSalary(ActionEvent event) {
        transaction.setCategory("Salary");
    }

    @FXML
    void handleClickHistoryButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        historyButton.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/history.fxml"));
        Scene scene = new Scene(root);
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
        homeWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amount.setPromptText("0.00");
        date.setPromptText("day/mount/year");
        memo.setPromptText("Enter your memory");
    }
}
