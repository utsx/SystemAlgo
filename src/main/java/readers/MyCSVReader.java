package readers;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyCSVReader {

    CSVReader csvReader;



    public List<String[]> readCSV(Path path){
        List<String[]> ans = new ArrayList<>();
        try {
            csvReader = new CSVReader(new FileReader(path.toFile()), ';', '"', 1);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String[] nextLine;
        while (true) {
            try {
                if ((nextLine = csvReader.readNext()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ans.add(nextLine);
        }
        return ans;
    }
}
