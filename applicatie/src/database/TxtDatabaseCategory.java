package database;

import model.Category;
import model.ListItem;

import java.io.*;
import java.util.*;

public class TxtDatabaseCategory extends TxtDatabaseStrategy {
    public TxtDatabaseCategory() {
        super(System.getProperty("user.dir") + "\\src\\testDatabase\\categories.txt");
    }




    @Override
    public List<ListItem> load() {
        List<ListItem> out = new ArrayList<>();
        // This will reference one line at a time
        String line = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] stringArray = line.split(";");
                out.add(new Category(stringArray[0],stringArray[1]));
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file: question.txt");
        }
        catch(IOException ex) {
            System.out.println("Error reading file: question.txt");
        }
        return out;
    }

    @Override
    public void update(List<ListItem> items) {
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(this.path);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            for(ListItem l : items){
                Category temp = (Category)l;
                bufferedWriter.write(temp.getName()+";"+temp.getDescription());
                bufferedWriter.newLine();
            }
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing categories: " + ex.getMessage());
        }
    }

}
