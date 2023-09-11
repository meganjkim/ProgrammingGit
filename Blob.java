import java.util.*;
import java.io.*;
import java.security.*;

public class Blob {
    private String hash;

    public Blob(String fileName) throws IOException {
        File obj = new File("./objects");
        obj.mkdirs();

        String fileContent = readFromFile(fileName);

        hash = hashFromString(fileContent);
        File newFile = new File("./objects/" + hash);
        newFile.createNewFile();

        writeToFile(fileContent, "./objects/" + hash);
    }

    public String readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder str = new StringBuilder();
        while (br.ready()) {
            str.append((char) br.read());
        }
        br.close();
        return str.toString();
    }

    public void writeToFile(String str, String fileName) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        pw.print(str);
        pw.close();
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

}