package DocumentParser.Parser;

import DocumentParser.Document.*;
import DocumentParser.Exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocumentParser {

    public static Document parseDocument(String fileName) throws DocumentParseException {
        String content;
        try {
            content = readFileContent(fileName);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        Map<String, String> map = parseJson(content);

        String id = map.get("id");
        String documentType = map.get("document_type");

        if (id == null || documentType == null) {
            throw new MissingFieldException("Missing mandatory fields: id or document_type");
        }

        switch (documentType) {
            case "CONTRACT":
                return new Contract(
                        id,
                        documentType,
                        Integer.parseInt(map.getOrDefault("cost", "0")),
                        map.getOrDefault("date", "N/A")
                );
            case "RECEIPT":
                return new Receipt(id, documentType, Integer.parseInt(map.getOrDefault("money_amount", "0")));
            case "RESUME":
                return new Resume(id, documentType, map.getOrDefault("name", "N/A"));
            default:
                throw new UnsupportedValueTypeException("Unsupported document_type: " + documentType);
        }
    }

    private static String readFileContent(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static Map<String, String> parseJson(String json) throws InvalidJsonFormatException {
        Map<String, String> map = new HashMap<>();
        json = json.trim();


        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new InvalidJsonFormatException("Invalid JSON format: Missing enclosing braces");
        }

        json = json.substring(1, json.length() - 1);
        if (!json.isEmpty()) {
            String[] pairs = json.split(",");

            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length != 2) {
                    throw new InvalidJsonFormatException("Invalid JSON format: Key-value pair not properly formed");
                }
                map.put(keyValue[0].trim().replace("\"", ""), keyValue[1].trim().replace("\"", ""));
            }
        }

        return map;
    }

}
