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

import static accountBook_Javafx.UserController.user;

public class AddPaidController implements Initializable{

    @FXML
    private BorderPane addPaidBorderPane;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField date;

    @FXML
    private Button incomeButton;

    @FXML
    private Button paidButton;

    @FXML
    private TextField amount;

    @FXML
    private Button enter;

    @FXML
    private Button food;

    @FXML
    private Button transport;

    @FXML
    private Button cosmetic;

    @FXML
    private Button study;

    @FXML
    private Button social;

    @FXML
    private Button fashion;

    @FXML
    private Button other;

    @FXML
    private TextField memo;

    @FXML
    private ImageView historyButton;

    @FXML
    private ImageView homeButton;

    private int status = 0 ;        //if user click 'INCOME' status = 1 else status = 0
    private Transaction transaction = new Transaction();

    @FXML
    void handleButtonAddTransaction(ActionEvent event) throws IOException {
        transaction.setAmount(-Double.parseDouble(amount.getText()));
        transaction.setAmountFormat(String.format("-"+"%.2f",Double.parseDouble(amount.getText())));
        transaction.setMemory(memo.getText());
        transaction.setDate(date.getText());
        user.addTransactionData(transaction);

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
    void handleButtonCosmetic(ActionEvent event) {
        transaction.setCategory("Cosmetic");
    }

    @FXML
    void handleButtonDate(ActionEvent event) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate date = datePicker.getValue();
//        System.out.println(formatter.format(date));
//        transaction.setDate(String.valueOf(formatter.format(date)));
    }

    @FXML
    void handleButtonFashion(ActionEvent event) {
        transaction.setCategory("Fashion");
    }

    @FXML
    void handleButtonFood(ActionEvent event) {
        transaction.setCategory("Food");
    }

    @FXML
    void handleButtonIncome(ActionEvent event) throws IOException {

        BorderPane paneIncome = FXMLLoader.load(getClass().getResource("/addIncome.fxml"));
        addPaidBorderPane.getChildren().setAll(paneIncome);
    }

    @FXML
    void handleButtonPaid(ActionEvent event) {  }

    @FXML
    void handleButtonOther(ActionEvent event) {
        transaction.setCategory("Other");
    }

    @FXML
    void handleButtonSocial(ActionEvent event) {
        transaction.setCategory("Social");
    }

    @FXML
    void handleButtonStudy(ActionEvent event) {
        transaction.setCategory("Study");
    }

    @FXML
    void handleButtonTransport(ActionEvent event) { transaction.setCategory("Transport"); }

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
//        BorderPane paneHome = FXMLLoader.load(getClass().getResource("/home.fxml"));
//        transactionBorderPane.getChildren().setAll(paneHome);
    }

    private void setStatus(int s){
        status = s;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amount.setPromptText("0.00");
        date.setPromptText("day/mount/year");
        memo.setPromptText("Enter your memory");
    }
}
