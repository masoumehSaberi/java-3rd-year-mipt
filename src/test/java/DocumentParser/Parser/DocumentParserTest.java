package DocumentParser.Parser;

import DocumentParser.Document.*;
import DocumentParser.Exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentParserTest {

    private final String testDirectory = "test_files";

    public DocumentParserTest() {
        // Create a directory for test files
        File directory = new File(testDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @AfterEach
    public void cleanup() {
        // Delete all test files after each test
        File directory = new File(testDirectory);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private String createTestFile(String fileName, String content) throws IOException {
        File file = new File(testDirectory, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        return file.getAbsolutePath();
    }

    @Test
    public void testParseContract() throws Exception {
        String filePath = createTestFile("contract.json",
                "{\"cost\": 5, \"date\": \"1-1-2023\", \"id\": \"A1\", \"document_type\": \"CONTRACT\"}");
        Document document = DocumentParser.parseDocument(filePath);
        assertTrue(document instanceof Contract);
        Contract contract = (Contract) document;
        assertEquals("A1", contract.getId());
        assertEquals(5, contract.getCost());
        assertEquals("1-1-2023", contract.getDate());
    }

    @Test
    public void testParseReceipt() throws Exception {
        String filePath = createTestFile("receipt.json",
                "{\"money_amount\": 100, \"id\": \"A1\", \"document_type\": \"RECEIPT\"}");
        Document document = DocumentParser.parseDocument(filePath);
        assertTrue(document instanceof Receipt);
        Receipt receipt = (Receipt) document;
        assertEquals("A1", receipt.getId());
        assertEquals(100, receipt.getMoneyAmount());
    }

    @Test
    public void testParseResume() throws Exception {
        String filePath = createTestFile("resume.json",
                "{\"name\": \"Vasya\", \"id\": \"A1\", \"document_type\": \"RESUME\"}");
        Document document = DocumentParser.parseDocument(filePath);
        assertTrue(document instanceof Resume);
        Resume resume = (Resume) document;
        assertEquals("A1", resume.getId());
        assertEquals("Vasya", resume.getName());
    }

    @Test
    public void testParseMissingField() throws Exception {
        String filePath = createTestFile("invalid.json", "{\"id\": \"A1\"}");
        assertThrows(MissingFieldException.class, () -> DocumentParser.parseDocument(filePath));
    }

    @Test
    public void testParseUnsupportedDocumentType() throws Exception {
        String filePath = createTestFile("unsupported.json",
                "{\"id\": \"A1\", \"document_type\": \"UNKNOWN_TYPE\"}");
        assertThrows(UnsupportedValueTypeException.class, () -> DocumentParser.parseDocument(filePath));
    }

    @Test
    public void testFileNotFound() {
        String nonExistentFile = "nonexistent.json";
        assertThrows(FileNotFoundException.class, () -> DocumentParser.parseDocument(nonExistentFile));
    }

    @Test
    public void testInvalidJsonFormatException() throws Exception {
        String filePath = createTestFile("invalidJson.json", "{invalid_json_content}");
        assertThrows(InvalidJsonFormatException.class, () -> DocumentParser.parseDocument(filePath));
    }

    @Test
    public void testMissingDocumentTypeField() throws Exception {
        String filePath = createTestFile("missingDocumentType.json", "{\"id\":\"12345\"}");
        assertThrows(MissingFieldException.class, () -> DocumentParser.parseDocument(filePath));
    }

}
