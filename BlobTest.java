import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class BlobTest {
    @Test
    void testBlobCreation() throws IOException {
        // Create a Blob and test if it was created successfully
        Blob blob = new Blob("test.txt");
        assertNotNull(blob);
    }

    @Test
    void testHashFromString() {
        // Test the hashFromString method
        String input = "Hello, World!";
        String expectedHash = "2ef7bde608ce5404e97d5f042f95f89f1c61f6f09";
        String actualHash = Blob.hashFromString(input);
        assertEquals(expectedHash, actualHash);
    }
}
