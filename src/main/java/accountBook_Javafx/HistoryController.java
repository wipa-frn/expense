package accountBook_Javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static accountBook_Javafx.UserController.user;

public class HistoryController implements Initializable{

    @FXML
    private TableView<Transaction> historyTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, String> amountColumn;
    @FXML
    private ListView<String> listDateView;

    @FXML
    private ImageView historyButton;

    @FXML
    private ImageView homeButton;
    @FXML
    private ImageView addTransactionButton;
    private ObservableList<Transaction> observableListTransaction ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        observableListTransaction = FXCollections.observableArrayList(user.getTransactionData());

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("category"));

        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amountFormat"));

        historyTable.setItems(observableListTransaction);


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
    @FXML
    void handleAddTransactionButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        addTransactionButton.getScene().getWindow().hide();
        Stage homeWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/addPaid.fxml"));
        Scene scene = new Scene(root);
        homeWindow.setScene(scene);
        homeWindow.show();
    }


}
