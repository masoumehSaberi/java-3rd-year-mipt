package DocumentParser.Reader;

import DocumentParser.Document.*;
import DocumentParser.Exceptions.*;
import DocumentParser.Parser.DocumentParser;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentReader {

    public static List<Document> readDocumentsFromDirectory(String directoryPath) throws IOException {
        List<Document> documents = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.json")) {
            for (Path filePath : stream) {
                try {
                    documents.add(DocumentParser.parseDocument(filePath.toString()));
                } catch (DocumentParseException e) {
                    System.err.println("Failed to parse file: " + filePath + " - " + e.getMessage());
                }
            }
        }
        return documents;
    }

    public static List<Document> readDocumentsFromFiles(List<String> fileNames) {
        List<Document> documents = new ArrayList<>();
        for (String fileName : fileNames) {
            try {
                documents.add(DocumentParser.parseDocument(fileName));
            } catch (DocumentParseException e) {
                System.err.println("Failed to parse file: " + fileName + " - " + e.getMessage());
            }
        }
        return documents;
    }
}

