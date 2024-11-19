package DocumentParser.Document;

public class Contract extends Document {
    private final int cost;
    private final String date;

    public Contract(String id, String documentType, int cost, String date) {
        super(id, documentType);
        this.cost = cost;
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }
}
