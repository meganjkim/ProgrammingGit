import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BlobTest {
    @Test
    void testHashFromString() {
        String input = "Please Work";
        String expectedHash = "c3aae8d56c6a440fd2937a7e9998bdc7382d0476";
        String actualHash = Blob.hashFromString(input);
        assertEquals(expectedHash, actualHash, "Test hashFromString works");
    }

    @Test
    void testReadFromFile() throws IOException {
        String content = "This is a test file content.";
        File tempFile = File.createTempFile("test", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.write(content);
        }

        Blob blob = new Blob(tempFile.getPath());
        String fileContent = blob.readFromFile(tempFile.getPath());

        tempFile.delete();

        assertEquals(content, fileContent, "Test read from file works");
    }

    @Test
    void testWriteToFile() throws IOException {
        // Create a temporary test file with content
        String content = "This is a test file content.";
        File tempFile = File.createTempFile("test", ".txt");

        Blob blob = new Blob(tempFile.getPath());
        blob.writeToFile(content, tempFile.getPath());

        String fileContent;
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.getPath()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fileContent = stringBuilder.toString();
        }

        tempFile.delete();

        assertEquals(content, fileContent, "Test write to file works");
    }

    @Test //good
    void testByteToHex() {
        byte[] inputBytes = new byte[]{10, 20, 30, 40, 50};
        String expectedHex = "0a141e2832";
        String actualHex = Blob.byteToHex(inputBytes);
        assertEquals(expectedHex, actualHex, "Byte to hex works");
    }
}
