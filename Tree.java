import java.nio.file.Paths;
import java.util.HashMap;

public class Tree {
    private HashMap<String, String> blobMap;
    private HashMap<String, String> treeMap;
    public Tree(){
        blobMap = new HashMap<String, String>();
        treeMap = new HashMap<String, String>();
    }

    public void add(String str) throws Exception{
        String[] arr = str.split(" : ");
        if (arr.length != 3){
            throw new Exception ("Invalid string input format.");
        }
        String typeOfFile = arr[0];
        String shaOfFile = arr[1];
        String fileName = arr[2];

        if (typeOfFile.equals("blob")){
            if (blobMap.containsKey(fileName)){
                throw new Exception("Already contains.");
            }

            if (!blobMap.containsValue(shaOfFile)){
                blobMap.put(fileName, shaOfFile);
            }
        }
        else if (typeOfFile.equals("tree")){
            if (treeMap.containsKey(fileName)){
                throw new Exception("Already contains.");
            }

            if (!treeMap.containsValue(shaOfFile)){
                treeMap.put(fileName, shaOfFile);
            }
        }
        else{
            throw new Exception ("Input file was neither a blob nor a tree.");
        }
    }

    public boolean remove(String fileName){
        if (blobMap.containsKey(fileName)){
            blobMap.remove(fileName);
            return true;
        }
        else if (treeMap.containsKey(fileName)){
            treeMap.remove(fileName);
            return true;
        }
        else{
            return false;
        }
    }

    public void writeToObjects() throws Exception{
        StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<String, String> entry : blobMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append("blob : " + value + " : " + key + "\n");
        }

        for (HashMap.Entry<String, String> entry : treeMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append("tree : " + value + " : " + key + "\n");
        }

        if (sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }

        Utils.writeToFile(sb.toString(), "objects/" +  Blob.hashFromString(sb.toString()));
    }


}
