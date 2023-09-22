import java.io.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commit {
    private String treeSha1;
    private String parentCommitSha1;
    private String author;
    private String date;
    private String summary;

    public Commit(String author, String summary) {
        this.treeSha1 = createTreeAndSave();
        this.parentCommitSha1 = null; // No parent commit initially
        this.author = author;
        this.summary = summary;
        this.date = getCurrentDate();
    }

    public Commit(String parentCommitSha1, String author, String summary) {
        this.treeSha1 = createTreeAndSave();
        this.parentCommitSha1 = parentCommitSha1;
        this.author = author;
        this.summary = summary;
        this.date = getCurrentDate();
    }

    private String createTreeAndSave() {
        Tree tree = new Tree();
        
        try {
            tree.save();
            return tree.getSha1();
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public void writeToFile() {
        try {
            StringBuilder commitData = new StringBuilder();
            commitData.append(treeSha1).append("\n");
            if (parentCommitSha1 != null) {
                commitData.append(parentCommitSha1).append("\n");
            }
            commitData.append(author).append("\n");
            commitData.append(date).append("\n");
            commitData.append(summary).append("\n");

            // Generate SHA1 hash from commitData
            String commitSha1 = generateSHA1(commitData.toString());

            // Write commit data to a file in the 'objects' folder
            File commitFile = new File("objects/" + commitSha1);
            try (PrintWriter writer = new PrintWriter(new FileWriter(commitFile))) {
                writer.write(commitData.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDate() {
        return date;
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        return dateFormat.format(new Date());
    }

    private String generateSHA1(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
