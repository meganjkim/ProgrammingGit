
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class Tree {
    private ArrayList<String> blobTree;

    public Tree() {
        blobTree = new ArrayList();
    }

    public void add(String indexLine) {

        if (!(blobTree.contains(indexLine)))
        {
            blobTree.add(indexLine);
        }
    }

    public void remove(String indexLine) {
        remove (indexLine);
    }
    public static String hashFromString(String str) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public void save() throws IOException {
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < blobTree.size (); i++)
        {
            sb.append (blobTree.get(i));
        }
        String hash = hashFromString (sb.toString());
        File tempFile = new File("./objects/" + hash);
        tempFile.createNewFile ();
        FileWriter fw = new FileWriter (tempFile);
        fw.write (sb.toString());
        fw.flush ();
        fw.close ();

    }
    public String getSha1()
    {
        String treeData = blobTree.toString();
        return hashFromString(treeData);
    }
}