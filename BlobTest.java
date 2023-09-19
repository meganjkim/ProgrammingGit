import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
        String input = "Please Work";
        String expectedHash = "c3aae8d56c6a440fd2937a7e9998bdc7382d0476";
        String actualHash = Blob.hashFromString(input);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    void testReadFromFile() throws IOException {
        // Create a temporary test file with content
        String content = "This is a test file content.";
        File tempFile = File.createTempFile("test", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.write(content);
        }

        // Test the readFromFile method
        Blob blob = new Blob(tempFile.getPath());
        String fileContent = blob.readFromFile(tempFile.getPath());

        // Clean up: Delete the temporary file
        tempFile.delete();

        assertEquals(content, fileContent);
    }

    @Test
    void testWriteToFile() throws IOException {
        // Create a temporary test file with content
        String content = "This is a test file content.";
        File tempFile = File.createTempFile("test", ".txt");

        // Create a Blob and write content to a file using writeToFile
        Blob blob = new Blob(tempFile.getPath());
        blob.writeToFile(content, tempFile.getPath());

        // Read the content from the file
        String fileContent;
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.getPath()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fileContent = stringBuilder.toString();
        }

        // Clean up: Delete the temporary file
        tempFile.delete();

        assertEquals(content, fileContent);
    }

    @Test
    void testByteToHex() {
        // Test the byteToHex method
        byte[] inputBytes = new byte[]{10, 20, 30, 40, 50};
        String expectedHex = "0a141e2832";
        String actualHex = Blob.byteToHex(inputBytes);
        assertEquals(expectedHex, actualHex);
    }
}
