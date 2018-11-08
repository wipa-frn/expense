package accountBook_Javafx;

public class Transaction {
    private String date;
    private String category;
    private String memory;
    private Double amount;
    private String amountFormat;
    private String type;
    private int order;


    public Transaction() {
        this.category = "Other";
        this.memory = "";
        this.amount = 0.00;
        this.amountFormat = "0.00";
        this.date = "--/--/--";

    }

    public Transaction(int order ,String date, String category, String memory, Double amount, String type) {
        this.order = order;
        this.date = date;
        this.category = category;
        this.memory = memory;
        this.amount = amount;
        this.type = type;

    }
    public Transaction(int order ,String date, String category, String memory, String amountFormat, String type) {
        this.order = order;
        this.date = date;
        this.category = category;
        this.memory = memory;
        this.amountFormat = amountFormat;
        this.type = type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}