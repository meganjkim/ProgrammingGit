import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree();
        // Create the "./objects/" directory if it doesn't exist
        File objectsDir = new File("./objects");
        objectsDir.mkdirs();
    }

    @Test
    void testAdd() {
        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.add("Entry 2");

        ArrayList<String> blobTree = tree.getBlobTree();
        assertEquals(2, blobTree.size(), "adding 2 entries -> size = 2");

        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        assertEquals(2, blobTree.size(), "adding duplicate entry doesn't have an effect");
    }

    @Test
    void testRemove() {
        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.add("Entry 2");

        ArrayList<String> blobTree = tree.getBlobTree();
        tree.remove("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");

        assertEquals(1, blobTree.size(), "Removing entry resulted in size of 1, correct");

        tree.remove("Entry 3");
        assertEquals(1, blobTree.size(), "Removing undefined entry had no effect");
    }

    @Test
    void testHashFromString() {
        String input = "Test String";
        String expectedHash = "a5103f9c0b7d5ff69ddc38607c74e53d4ac120f2";

        String hash = Tree.hashFromString(input);
        assertEquals("testhashFromString worked", expectedHash, hash);
    }

    @Test
    void testSave() {
        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.add("Entry 2");

        try {
            tree.save();
            ArrayList<String> blobTree = tree.getBlobTree();

            String hash = Tree.hashFromString("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b\nEntry 2");
            File tempFile = new File("./objects/" + hash);
            assertTrue(tempFile.exists(), "testSave worked");

            tempFile.delete();
        } catch (IOException e) {
            fail("IOException occurred during save: " + e.getMessage());
        }
    }

    @Test
    void testByteToHex() {
        byte[] inputBytes = new byte[] { 10, 20, 30, 40, 50 };
        String expectedHex = "0a141e2832";

        String hex = tree.byteToHex(inputBytes);
        assertEquals("testByteToHex worked", expectedHex, hex);
    }
}
