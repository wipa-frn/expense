package accountBook_Javafx;


import databaseConnection.TextFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import static accountBook_Javafx.UserController.user;

public class HistoryController implements Initializable{
    @FXML private BorderPane historyBorderPane;
    @FXML private TableView<Transaction> historyTable;
    @FXML private TableColumn<Transaction, String> dateColumn;
    @FXML private TableColumn<Transaction, String> categoryColumn;
    @FXML private TableColumn<Transaction, String> amountColumn;
    @FXML private ImageView homeButton;
    @FXML private ImageView addTransactionButton;
    @FXML private Label incomeLabel;
    @FXML private Label paidLabel;
    @FXML private Label showTotalPaid;
    @FXML private Label showTotalIncome;
    @FXML private ImageView saveFileImg;
    @FXML private ImageView deleteButton;
    @FXML private ImageView editButton;
    ObservableList<Transaction> observableListTransaction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            showTableView();
            showTotalIncome.setText(String.format("%,.2f ฿",user.getTotalIncome()));
            showTotalPaid.setText(String.format("-%,.2f ฿",user.getTotalExpense()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void showTableView() throws SQLException {

        List<Transaction> listTransaction = user.getTransactionManager().getAllTransactions();

        observableListTransaction = FXCollections.observableArrayList(listTransaction) ;

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountFormat"));

        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        historyTable.setItems(observableListTransaction);

    }


    @FXML
    void handleUpdateButton(MouseEvent event) throws SQLException {
        showTableView();
    }

    @FXML
    void handleClickEditButton(MouseEvent event) throws IOException {
        if(!historyTable.getSelectionModel().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editTransaction.fxml"));
                Parent parent = (Parent) loader.load();
                EditTransactionController editTransactionController = loader.getController();
                editTransactionController.setTransaction(historyTable.getSelectionModel().getSelectedItem());
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.show();
                stage.setScene(new Scene(parent));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
        Parent root = loader.load(getClass().getResource("/addTransaction.fxml"));
        Scene scene = new Scene(root);
        homeWindow.setScene(scene);
        homeWindow.show();
    }

    @FXML
    void handleSaveFileButton(MouseEvent event) throws SQLException {
        //save file to .txt
        TextFile textFile = new TextFile();
        textFile.saveAllTransaction(user.getTransactionManager().getAllTransactions());

    }
    @FXML
    void handleClickDeleteButton(MouseEvent event) throws IOException, SQLException {

        if(!historyTable.getSelectionModel().isEmpty()) {
            Transaction t = historyTable.getSelectionModel().getSelectedItem();
            user.getTransactionManager().delete(t);
            observableListTransaction.remove(t);
            historyTable.setItems(observableListTransaction);
        }
        showTotalIncome.setText(String.format("%.2f ฿", user.getTotalIncome()));
        showTotalPaid.setText(String.format("-%.2f ฿", user.getTotalExpense()));

    }


}
