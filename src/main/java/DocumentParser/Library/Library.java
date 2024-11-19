package DocumentParser.Library;

import java.util.HashMap;
import java.util.Map;

import DocumentParser.Document.*;


public class Library<T extends Document> {
    private final Map<String, T> documents = new HashMap<>();

    public void put(T document) {
        if (documents.containsKey(document.getId())) {
            throw new IllegalArgumentException("Document with ID " + document.getId() + " already exists");
        }
        documents.put(document.getId(), document);
    }

    public T get(String id) {
        if (!documents.containsKey(id)) {
            throw new IllegalArgumentException("No document found with ID " + id);
        }
        return documents.get(id);
    }

    public void remove(String id) {
        if (!documents.containsKey(id)) {
            throw new IllegalArgumentException("No document found with ID " + id);
        }
        documents.remove(id);
    }
}

