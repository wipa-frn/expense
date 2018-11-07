package accountBook_Javafx;

import databaseConnection.DbConnect;
import javafx.beans.binding.When;
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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    @FXML private ImageView historyButton;
    @FXML private ImageView homeButton;
    @FXML private ImageView addTransactionButton;
    @FXML private Label incomeLabel;
    @FXML private Label paidLabel;
    @FXML private Label showTotalPaid;
    @FXML private Label showTotalIncome;
    @FXML private ImageView saveFile;
    @FXML private ImageView deleteButton;
    private ObservableList<Transaction> observableListTransaction = FXCollections.observableArrayList() ;

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
                listTransaction.add(new Transaction(rs.getInt("order"),rs.getString("date"),rs.getString("category"),rs.getString("memory"),amount,rs.getString("type")));

                if(rs.getString("type").equals("expense")) {
                    amount = -amount;
                }
                observableListTransaction.add(new Transaction(rs.getString("date"),rs.getString("category"),String.valueOf(amount)+" ฿"));

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
        historyTable.setEditable(true);
        showTotalIncome.setText(String.format("%.2f ฿",user.getTotalIncome()));
        showTotalPaid.setText(String.format("- %.2f ฿",user.getTotalExpense()));


    }
    @FXML
    void onEditAmount(TableColumn.CellEditEvent<Transaction,String> editAmountEvent) {
        //edit in list transaction
        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();

        double edit = Double.parseDouble(editAmountEvent.getNewValue());
        user.getTransactionData().get(indexEdit).setAmountFormat(String.format("%.02f", edit));
        user.getTransactionData().get(indexEdit).setAmount(edit);
        showTotalIncome.setText(String.format("%.2f ฿",user.getTotalIncome()));
        showTotalPaid.setText(String.format("%.2f ฿",user.getTotalExpense()));

        editDatabase(indexEdit);

    }

    @FXML
    void onEditCategory(TableColumn.CellEditEvent<Transaction,String> editCategoryEvent) {
        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();
        String edit = editCategoryEvent.getNewValue();
        user.getTransactionData().get(indexEdit).setCategory(edit);

        editDatabase(indexEdit);

    }

    @FXML
    void onEditDate(TableColumn.CellEditEvent<Transaction,String> editDateEvent) {
        int indexEdit = historyTable.getSelectionModel().getFocusedIndex();
        String edit = editDateEvent.getNewValue();
        user.getTransactionData().get(indexEdit).setDate(edit);

        editDatabase(indexEdit);
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

    @FXML
    void handleSaveFileButton(MouseEvent event) {

        //save file to .txt
        try {
            FileWriter fw = new FileWriter("historyFile.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Transaction t: user.getTransactionData()) {
                fw.write("DATE: "+t.getDate()+", ");
                fw.write("CATEGORY: "+t.getCategory()+", ");
                fw.write(String.valueOf("AMOUNT: "+t.getAmountFormat()+", "));
                fw.write("MEM: "+t.getMemory()+" //");
            }
            System.out.println("Write on file success.");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR");
        }


    }
    @FXML
    void handleClickDeleteButton(MouseEvent event) {
        int index = historyTable.getSelectionModel().getFocusedIndex();
        user.getTransactionData().remove(index);
        observableListTransaction = FXCollections.observableArrayList(user.getTransactionData());
        historyTable.setItems(observableListTransaction);
        showTotalIncome.setText(String.format("%.2f ฿",user.getTotalIncome()));
        showTotalPaid.setText(String.format("- %.2f ฿",user.getTotalExpense()));
    }

    public void editDatabase(int indexEdit){
        //edit data in SQLite
        Connection connection = DbConnect.getConnection();
        PreparedStatement pst ;

        String sql = "Update transaction_database set date = ?,category = ? , memory = ? ,amount = ?,type = ?)";
        String dateStr = user.getTransactionData().get(indexEdit).getDate();
        String categoryStr = user.getTransactionData().get(indexEdit).getCategory();
        String memoryStr = user.getTransactionData().get(indexEdit).getMemory();
        Double amount = user.getTransactionData().get(indexEdit).getAmount();
        String typeStr = user.getTransactionData().get(indexEdit).getType();

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,dateStr);
            pst.setString(2,categoryStr);
            pst.setString(3,memoryStr);
            pst.setDouble(4,amount);
            pst.setString(5,typeStr);
            pst.executeUpdate();
            System.out.println(" Data insert successfully! ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                System.out.println("Close database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
