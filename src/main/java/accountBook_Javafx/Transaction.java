package accountBook_Javafx;

public class Transaction {
    private String date;
    private String category;
    private String memory;
    private Double amount;
    private String amountFormat;


    public Transaction() {
        this.category = "Other";
        this.memory = "";
        this.amount = 0.00;
        this.amountFormat = "0.00";
        this.date = "--/--/--";

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }

    public String getAmountFormat() { return amountFormat; }

    public void setAmountFormat(String amountFormat) { this.amountFormat = amountFormat; }

}