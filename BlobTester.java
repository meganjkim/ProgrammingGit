import java.io.IOException;

public class BlobTester {
    public static void main(String[] args) throws IOException {
        Blob b = new Blob();
        System.out.println(b.readFromFile("README.md"));
        System.out.println(b.encryptPassword(b.readFromFile("README.md")));
    }
}
