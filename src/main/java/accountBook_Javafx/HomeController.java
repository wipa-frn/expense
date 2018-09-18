package accountBook_Javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static accountBook_Javafx.UserController.user;

public class HomeController implements Initializable{

    @FXML
    private BorderPane homeBorderPane ;

    @FXML
    private Button history;

    @FXML
    private Button addTransaction;

    @FXML
    private Button exit;

    @FXML
    private Label showTotalBalance;

    @FXML
    private Label showTotalIncome;

    @FXML
    private Label showTotalPaid;

    @FXML
    private Label showDate;
    @FXML
    private Label showUsername;

    @FXML
    void handleButtonAddTransaction(ActionEvent event) throws IOException {

        BorderPane panePaid = FXMLLoader.load(getClass().getResource("/addPaid.fxml"));
        homeBorderPane.getChildren().setAll(panePaid);


    }

    @FXML
    void handleButtonHistory(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        history.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/history.fxml"));
        Scene scene = new Scene(root);
        homeWindow.setScene(scene);
        homeWindow.show();

    }

    @FXML
    void handleButtonExit(ActionEvent event) {
        Stage stage = (Stage) history.getScene().getWindow();
        stage.close();
    }


    public void initialize(URL location, ResourceBundle resources) {
        Date date = new Date ();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        showDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" +calendar.get(Calendar.YEAR));
        showUsername.setText(user.getUsername());
        showTotalBalance.setText(String.format("%.2f ฿",user.getTotalBalance()));
        showTotalIncome.setText(String.format("%.2f ฿",user.getTotalIncome()));
        showTotalPaid.setText(String.format("-%.2f ฿",user.getTotalExpense()));

    }
}
