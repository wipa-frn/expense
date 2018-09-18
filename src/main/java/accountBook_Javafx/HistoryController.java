package accountBook_Javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static accountBook_Javafx.UserController.user;

public class HistoryController implements Initializable{
    @FXML
    private BorderPane historyBorderPane;
    @FXML
    private TableView<Transaction> historyTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, String> amountColumn;

    @FXML
    private ImageView historyButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private ImageView addTransactionButton;
    @FXML
    private Label incomeLabel;

    @FXML
    private Label paidLabel;

    @FXML
    private Label showPaid;

    @FXML
    private Label showIncome;

    private ObservableList<Transaction> observableListTransaction ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showTableView();

    }

    void showTableView(){
        observableListTransaction = FXCollections.observableArrayList(user.getTransactionData());

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amountFormat"));

        dateColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());
        categoryColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());

        historyTable.setItems(observableListTransaction);
        historyTable.setEditable(true);

    }
    @FXML
    void onEditAmount(TableColumn.CellEditEvent<Transaction,String> editAmountEvent) {

        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();
        double edit = Double.parseDouble(editAmountEvent.getNewValue());
        user.getTransactionData().get(indexEdit).setAmountFormat(String.format("%.02f", edit));
        user.getTransactionData().get(indexEdit).setAmount(edit);

    }

    @FXML
    void onEditCategory(TableColumn.CellEditEvent<Transaction,String> editCategoryEvent) {
        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();
        String edit = editCategoryEvent.getNewValue();
        user.getTransactionData().get(indexEdit).setCategory(edit);

    }

    @FXML
    void onEditDate(TableColumn.CellEditEvent<Transaction,String> editDateEvent) {
        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();
        String edit = editDateEvent.getNewValue();
        user.getTransactionData().get(indexEdit).setDate(edit);
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
