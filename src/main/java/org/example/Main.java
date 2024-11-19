package org.example;


import DocumentParser.Document.*;
import DocumentParser.Reader.DocumentReader;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //it reads json documents in the specified directory
        String directoryPath = "path/to/documents";

        try {
            List<Document> documents = DocumentReader.readDocumentsFromDirectory(directoryPath);

            for (Document doc : documents) {
                System.out.println("Parsed Document: " + doc.getClass().getSimpleName() + " - ID: " + doc.getId());
            }
        } catch (IOException e) {
            System.err.println("Error accessing directory: " + e.getMessage());
        }
    }
}