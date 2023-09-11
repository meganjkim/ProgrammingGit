import java.io.IOException;

public class BlobTester {
    public static void main(String[] args) throws Exception {
        Blob b = new Blob("milo.txt");
        // System.out.println(b.readFromFile("README.md"));
        // System.out.println(b.hashFromString(b.readFromFile("README.md")));

        Git g = new Git();
        g.init();
        g.add("milo.txt");

    }
}
