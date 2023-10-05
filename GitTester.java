import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GitTester {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        /*
         * Utils.writeStringToFile("junit_example_file_data.txt", "test file contents");
         * Utils.deleteFile("index");
         * Utils.deleteDirectory("objects");
         */
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        /*
         * Utils.deleteFile("junit_example_file_data.txt");
         * Utils.deleteFile("index");
         * Utils.deleteDirectory("objects");
         */
        Utils.deleteDirectory("objects");
    }

    @Test
    @DisplayName("Testing Initialize")
    public void testInit() throws Exception{
        Git git = new Git();
        git.init();
        assertTrue(Utils.exists("index"));
        assertTrue(Utils.exists("objects"));

        Utils.deleteFile("index");
        Utils.deleteDirectory("objects");
    }

    @Test
    @DisplayName("Testing Add Blob")
    public void testAddBlob() throws Exception{
        Git git = new Git();
        git.init();
        Utils.writeToFile("jcafjkdsljafjriouslxpouoqiupreu", "Test File");
        assertEquals(Utils.readFile("index"), "");
        git.add("Test File");
        assertEquals(Utils.readFile("index"), "Test File : a15613f8dd329213217ae87a9e66b95562ce09e6");

        Utils.deleteFile("index");
        Utils.deleteDirectory("objects");
    }

    @Test
    @DisplayName("Testing Remove Blob")
    public void testRemoveBlob() throws Exception{
        Git git = new Git();
        git.init();
        Utils.writeToFile("jcafjkdsljafjriouslxpouoqiupreu", "Test File");
        Utils.writeToFile("dasoipupwee8r9270u9owierlhs", "Test File 2");
        assertEquals(Utils.readFile("index"), "");
        git.add("Test File");
        git.add("Test File 2");
        assertEquals(Utils.readFile("index"), "Test File 2 : f448461c097c5818f254949744957d6bda773d7a\nTest File : a15613f8dd329213217ae87a9e66b95562ce09e6");

        git.remove("Test File");
        assertEquals(Utils.readFile("index"), "Test File 2 : f448461c097c5818f254949744957d6bda773d7a");

        Utils.deleteFile("index");
        Utils.deleteDirectory("objects");
    }
}
