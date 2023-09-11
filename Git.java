import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class Git {
    private HashMap<String, String> hash;

    public Git() {
        hash = new HashMap<String, String>();
    }

    public void init() throws Exception {
        try {
            File objects = new File("./objects");
            objects.mkdirs();
            rewrite();
        } catch (Exception e) {
            throw e;
        }
    }

    private void rewrite() throws Exception {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("index")));
            for (String s : hash.keySet()) {

                String hash = hash.get(s);

                pw.println(s + " : " + hash);
            }
            pw.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
