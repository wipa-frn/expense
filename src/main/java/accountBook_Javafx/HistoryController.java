package accountBook_Javafx;

import databaseConnection.DbConnect;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private ObservableList<Transaction> observableListTransaction = FXCollections.observableArrayList() ;
    FileManageable fileManageable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showTableView();

    }

    void showTableView(){
        ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();
        //find data base for show on table view.
        try {
            Connection connection = DbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select * from transaction_data");
            while (rs.next()){

                Double amount = Double.valueOf(String.format("%.2f", rs.getDouble("amount")));
                listTransaction.add(new Transaction(rs.getInt("order_id"),rs.getString("date"),rs.getString("category"),rs.getString("memory"),amount,rs.getString("type")));

                if(rs.getString("type").equals("expense")) {
                    amount = -amount;
                }
                observableListTransaction.add(new Transaction(rs.getInt("order_id"),rs.getString("date"),rs.getString("category"),rs.getString("memory"),String.valueOf(amount),rs.getString("type")));

            }
            user.setTransactionData(listTransaction); //update transaction list


        } catch (SQLException e) {
            e.printStackTrace();
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction ,String>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amountFormat"));

        dateColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());
        categoryColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.<Transaction>forTableColumn());

        historyTable.setItems(observableListTransaction);
        showTotalIncome.setText(String.format("%.2f ฿",user.getTotalIncome()));
        showTotalPaid.setText(String.format("- %.2f ฿",user.getTotalExpense()));


    }

    @FXML
    void handleClickEditButton(MouseEvent event) throws IOException {
        if(!historyTable.getSelectionModel().isEmpty()) {
            FXMLLoader loader = new FXMLLoader();
            addTransactionButton.getScene().getWindow().hide();

            Transaction t = historyTable.getSelectionModel().getSelectedItem();
            EditTransactionController edit = (EditTransactionController) loader.getController();
            edit.setTransaction(t);


            Stage homeWindow = new Stage();
            Parent root = loader.load(getClass().getResource("/editTransaction.fxml"));

            Scene scene = new Scene(root);
            homeWindow.setScene(scene);
            homeWindow.show();

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
    void handleSaveFileButton(MouseEvent event) {
        //save file to .txt
        fileManageable = new TextFile(user.getTransactionData());
        fileManageable.save();

    }
    @FXML
    void handleClickDeleteButton(MouseEvent event) throws IOException {

        if(!historyTable.getSelectionModel().isEmpty()) {
            Transaction t = historyTable.getSelectionModel().getSelectedItem();
            ArrayList<Transaction> tran_remove = user.getTransactionData();

            if (tran_remove.get(t.getOrder() - 1).getOrder() == t.getOrder()) {
                tran_remove.remove(t.getOrder() - 1);
                observableListTransaction.remove(t.getOrder() - 1);
                user.setTransactionData(tran_remove);   //update array of transaction
                fileManageable = new DatabaseFile(t);
                fileManageable.delete();                //update database
            }

            historyTable.setItems(observableListTransaction);

            showTotalIncome.setText(String.format("%.2f ฿", user.getTotalIncome()));
            showTotalPaid.setText(String.format("- %.2f ฿", user.getTotalExpense()));
        }

    }


}
