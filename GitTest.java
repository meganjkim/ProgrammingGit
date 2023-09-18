import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.PrintWriter;

public class GitTest {
    private Git git;

    @BeforeEach
    public void setUp() {
        git = new Git();
    }

    @Test
    void testInit() throws Exception {
        git.init();
        assertTrue(new File("./objects").exists());
    }

    @Test
    void testAdd() throws Exception {
        git.init();
    
        // Create the test.txt file with some content
        File testFile = new File("test.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("This is a test.");
        }
        
        // Add the file to Git
        try {
            git.add("test.txt");
        } catch (Exception e) {
            fail("Adding the file to Git failed: " + e.getMessage());
        }
        
        // Ensure that the file was added (you can check if the file exists in the 'hash' directory)
        assertTrue(new File("./objects/test.txt").exists());
        
        // Clean up: Remove the test.txt file
        testFile.delete();
    }

    @Test
    void testRemove() throws Exception {
        git.init();
    
        // Create the test.txt file with some content
        File testFile = new File("test.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("This is a test.");
        }
        
        // Add the file to Git
        try {
            git.add("test.txt");
        } catch (Exception e) {
            fail("Adding the file to Git failed: " + e.getMessage());
        }
        
        // Remove the file from Git
        try {
            git.remove("test.txt");
        } catch (Exception e) {
            fail("Removing the file from Git failed: " + e.getMessage());
        }
        
        // Ensure that the file was removed (you can check if the file does not exist in the 'hash' directory)
        assertFalse(new File("./objects/test.txt").exists());
        
        // Clean up: Remove the test.txt file
        testFile.delete();
    }

    @Test
    void testRewrite() throws Exception {
        git.init();

        // Add a file
        git.add("test.txt");

        // Rewrite the index
        git.rewrite();

        // Add assertions to check if rewriting the index was successful
        // For example, you can check if the 'index' file exists
        assertTrue(new File("index").exists());
    }
}