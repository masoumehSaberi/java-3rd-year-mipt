package DocumentParser.Document;

public class Resume extends Document {
    private final String name;

    public Resume(String id, String documentType, String name) {
        super(id, documentType);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
