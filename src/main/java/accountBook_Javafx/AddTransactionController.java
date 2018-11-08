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
import java.util.ResourceBundle;

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
    FileManageable fileManageable;

    @FXML
    void handleButtonAddTransaction(ActionEvent event) throws IOException {

        transaction.setAmount(Double.parseDouble(amount.getText()));
        transaction.setMemory(memo.getText());
        transaction.setDate(date.getEditor().getText());
        transaction.setCategory(categoryChoices.getSelectionModel().getSelectedItem());
        fileManageable = new DatabaseFile(transaction);
        fileManageable.save();


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
        incomeButton.getStyleClass().add("select_button");
        paidButton.getStyleClass().add("nonSelect_button");
        transaction.setType("income");

        ObservableList<String> categoryList = FXCollections.observableArrayList("Salary", "Refunds","Sale","Other");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.setValue("Salary");
        categoryChoices.getItems().setAll(categoryList);
    }

    @FXML
    void handleButtonPaid(ActionEvent event){

        paidButton.getStyleClass().add("select_button");
        incomeButton.getStyleClass().add("nonSelect_button");
        transaction.setType("expense");

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

        ObservableList<String> categoryList = FXCollections.observableArrayList("Food", "Transportation","Shopping","Health","Other");
        categoryChoices.getSelectionModel().selectFirst();
        categoryChoices.setValue("Food");
        categoryChoices.getItems().setAll(categoryList);
        transaction.setType("expense");


    }
}
