import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class Utils {
    public static void deleteFile(String File) {
        File f = new File(File);
        f.delete();
    }

    public static void deleteDirectory(String p) throws IOException {
        Path dir = Paths.get(p); // path to the directory
        Files
                .walk(dir) // Traverse the file tree in depth-first order
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path); // delete each file or directory
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}