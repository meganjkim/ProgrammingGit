import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

public class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree();
    }

    @Test
    void testAdd() {
        tree.add("Entry 1");
        tree.add("Entry 2");

        assertEquals(2, tree.getBlobTree().size());

        // Test adding a duplicate entry
        tree.add("Entry 1");
        assertEquals(2, tree.getBlobTree().size()); // Size should not change
    }

    @Test
    void testRemove() {
        tree.add("Entry 1");
        tree.add("Entry 2");

        tree.remove("Entry 1");
        assertEquals(1, tree.getBlobTree().size());

        // Test removing a non-existing entry
        tree.remove("Entry 3");
        assertEquals(1, tree.getBlobTree().size()); // Size should not change
    }

    @Test
    void testHashFromString() {
        String input = "Test String";
        String expectedHash = "532eaabd9574880dbf76b9b8cc00832c20a6ec113";

        String hash = Tree.hashFromString(input);
        assertEquals(expectedHash, hash);
    }

    @Test
    void testSave() throws IOException {
        // Create and add some entries
        tree.add("Entry 1");
        tree.add("Entry 2");

        // Save the tree
        tree.save();

        // Check if the file was created
        File tempFile = new File("./objects/" + tree.hashFromString("Entry 1\nEntry 2"));
        assertTrue(tempFile.exists());

        // Clean up: Delete the file
        tempFile.delete();
    }
}
