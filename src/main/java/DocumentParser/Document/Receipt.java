package DocumentParser.Document;

public class Receipt extends Document {
    private final int moneyAmount;

    public Receipt(String id, String documentType, int moneyAmount) {
        super(id, documentType);
        this.moneyAmount = moneyAmount;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }
}
