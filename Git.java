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

    public void add(String file) throws Exception {
        try {
            Blob blob = new Blob(file);
            hash.put(file, blob.getHash());
            rewrite();
        } catch (Exception e) {
            throw e;
        }
    }

    public void remove(String file) throws Exception {
        try {
            if (!hash.containsKey(file))
                throw new Exception("Error: File does not exist.");
            hash.remove(file);
            rewrite();
        } catch (Exception e) {
            throw e;
        }
    }

    private void rewrite() throws Exception {
        try {
            //Switched print writer to string builder because the former cannot delete the last new empty line :)
            //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("index")));
            StringBuilder bob = new StringBuilder();
            for (String s : hash.keySet()) {
                String str = hash.get(s);
                bob.append(s + " : " + str + "\n");
            }
            if (bob.length() > 0){
                bob.deleteCharAt(bob.length() - 1);
            }
            Utils.writeToFile(bob.toString(), "index");
        } catch (Exception e) {
            throw e;
        }
    }
}
