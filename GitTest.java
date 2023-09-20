import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GitTest {
    private Git git;

    @BeforeEach
    public void setUp() {
        git = new Git();
    }

    @Test
    void testInit() throws Exception {
        git.init();

        File file = new File("index");
        Path path = Paths.get("objects");

        assertTrue("Index does not exist", file.exists());
        assertTrue("Objects folder does not exist", Files.exists(path));
    }

    @Test
    void testAdd() throws Exception {
        git.init();

        File testFile = new File("test.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.print("This is a test.");
        }

        try {
            git.add("test.txt");
        } catch (Exception e) {
            fail("Adding the file to Git failed: " + e.getMessage());
        }

        assertTrue("TestAdd works", new File("./objects/afa6c8b3a2fae95785dc7d9685a57835d703ac88").exists());

        testFile.delete();
    }

    @Test
    void testRemove() throws Exception {
        git.init();

        File testFile = new File("test.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("This is a test.");
        }

        try {
            git.add("test.txt");
        } catch (Exception e) {
            fail("Adding the file to Git failed: " + e.getMessage());
        }

        try {
            git.remove("test.txt");
        } catch (Exception e) {
            fail("Removing the file from Git failed: " + e.getMessage());
        }

        assertFalse("testRemove worked", new File("./objects/test.txt").exists());

        testFile.delete();
    }

    @Test
    void testRewrite() throws Exception {
        git.init();

        File tempTestFile = new File ("test.txt");
        try (PrintWriter writer = new PrintWriter(tempTestFile)) {
            writer.println("This is a test.");
        }

        try {
            git.add(tempTestFile.getPath());
        } catch (Exception e) {
            fail("Adding the file to Git failed: " + e.getMessage());
        }

        git.rewrite();

        File indexFile = new File("index");
        assertTrue(indexFile.exists(), "index file does not exist");

        String indexContents = new String(Files.readAllBytes(Paths.get("index")), StandardCharsets.UTF_8);

        assertTrue(indexContents.contains(tempTestFile.getName()), "index file does not contain 'test.txt'");

        tempTestFile.delete();
    }

    @AfterEach
    void cleanup() {
        // Delete the index file
        File indexFile = new File("index");
        if (indexFile.exists()) {
            indexFile.delete();
        }
    }
}