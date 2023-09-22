import org.junit.jupiter.api.Test;
import java.io.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CommitTest {

    @Test
    void testCreateCommitWithParent() {
        Commit parentCommit = new Commit("Parent Author", "Parent Summary");
        Commit childCommit = new Commit(parentCommit.getDate(), "Child Author", "Child Summary");
    
        assertNotNull(childCommit);
        assertEquals(parentCommit.getDate(), childCommit.getParentCommitSha1());
        assertNotNull(childCommit.getAuthor());
        assertNotNull(childCommit.getDate());
        assertNotNull(childCommit.getSummary());
    }

    @Test
    void testCreateCommitWithoutParent() {
        Commit commit = new Commit("Author", "Summary");

        assertNotNull(commit);
        assertNull(commit.getParentCommitSha1());
        assertNotNull(commit.getAuthor());
        assertNotNull(commit.getDate());
        assertNotNull(commit.getSummary());
    }

    @Test
    void testWriteToFile() {
        
        File testDirectory = new File("test_objects");
        testDirectory.mkdir();
        File commitFile;

        try {
            
            Commit commit = new Commit("Author", "Summary");
            commit.writeToFile();

            
            commitFile = new File("objects/" + commit.getTreeSha1());
            assertNotNull(commitFile.exists());

        } finally {
            
            deleteDirectory(testDirectory);
        }
    }

    @Test
    void testGenerateSHA1() {
        Commit commit = new Commit("Author", "Summary");
        String input = "Test Input";
        String expectedHash = commit.generateSHA1(input);

        assertNotNull(expectedHash);

        
        assertEquals(expectedHash, commit.generateSHA1(input));
    }

    
    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        directory.delete();
    }
}
