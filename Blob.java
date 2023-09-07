import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Blob {
    public String readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str = "";
        while (br.ready()) {
            str += (char) br.read();
        }
        br.close();
        return str;
    }

}