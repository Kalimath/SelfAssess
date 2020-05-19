package database;
import model.Category;
import model.ListItem;
import model.Question;

import java.io.*;
import java.util.*;

public class TxtDatabaseQuestion extends TxtDatabaseStrategy{

    public TxtDatabaseQuestion() {
        super(System.getProperty("user.dir") + "\\src\\testDatabase\\questions.txt");
    }

    @Override
    public List<ListItem> load() {
        List<ListItem> out = new ArrayList<>();
        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.path);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                List<String> stringList = new ArrayList<>();
                stringList.addAll(Arrays.asList(line.split(";")));
                List<String> subList = stringList.subList(3,stringList.size());
                out.add(new Question(stringList.get(0),stringList.get(1),subList,stringList.get(2)));
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file: question.txt");
        }
        catch(IOException ex) {
            System.out.println("Error reading file: question.txt");
            // Or we could just do this:
            // ex.printStackTrace();
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
            items.addAll(this.load());
            // Note that write() does not automatically
            // append a newline character.
            for(ListItem l : items){
                Question temp = (Question) l;
                bufferedWriter.write(temp.getName()+";"+temp.getCategory()+";"+temp.getFeedback());
                for(String statement : temp.getStatements())bufferedWriter.write(";"+statement);
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
