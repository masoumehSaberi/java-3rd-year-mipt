package DocumentParser.Library;

import DocumentParser.Document.*;
import DocumentParser.Exceptions.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testPutAndGet() {
        Library<Contract> library = new Library<>();
        Contract contract = new Contract("A1", "CONTRACT", 5, "1-1-2023");
        library.put(contract);
        assertEquals(contract, library.get("A1"));
    }

    @Test
    public void testRemove() {
        Library<Contract> library = new Library<>();
        Contract contract = new Contract("A1", "CONTRACT", 5, "1-1-2023");
        library.put(contract);
        library.remove("A1");
        assertThrows(IllegalArgumentException.class, () -> library.get("A1"));
    }
}