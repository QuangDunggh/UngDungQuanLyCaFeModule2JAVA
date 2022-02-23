package Utill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static <T> void writeFile(String pathFile, List<T> listProduct) throws IOException {
        File file = new File(pathFile);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile));
        for (T item : listProduct) {
            bw.write(item.toString() + "\n");

        }
        bw.flush();
        bw.close();

    }

    public static List<String> readFile(String pathFile) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            String line;
            while ((line = bufferedReader.readLine()) != null && line.trim().length() > 0) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
