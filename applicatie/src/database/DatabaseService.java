package database;

import model.Category;
import model.ListItem;
import model.Question;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseService {
    private DatabaseContext databaseContext;
    private PropertiesDatabase propertiesDatabase;
    private String strategy;

    public DatabaseService() throws FileNotFoundException {
        propertiesDatabase = new PropertiesDatabase(System.getProperty("user.dir") + "\\src\\testDatabase\\evaluation.properties");
            setStrategy(propertiesDatabase.getDatabaseType());
        setDatabaseContext();

    }

    private void setStrategy(String strategy) {
        if (DatabaseStrategies.containsClassname(strategy)) {
            throw new IllegalArgumentException("can not set strategy: strategy not found");
        } else {
            this.strategy = strategy;
        }
    }

    private void setDatabaseContext() {
        this.databaseContext = new DatabaseContext();
    }


    public List<Category> readCategories() {
        Iterator it = databaseContext.loadCategories(strategy).iterator();
        List<Category> out = new ArrayList<>();
        while (it.hasNext()) {
            ListItem temp = (ListItem) it.next();
            if (temp instanceof Category) {
                out.add((Category) temp);
            }
        }
        return out;
    }

    public List<Question> readQuestions() {
        Iterator it = databaseContext.loadQuestions(strategy).iterator();
        List<Question> out = new ArrayList<>();
        while (it.hasNext()) {
            ListItem temp = (ListItem) it.next();
            if (temp instanceof Question) {
                out.add((Question) temp);
            }
        }
        return out;
    }

    public void writeCategories(List<Category> categories) {
        if (categories.isEmpty()) {
            throw new DatabaseException("can not set categories: list is empty");
        } else {
            List<ListItem> items = new ArrayList<>();
            for (ListItem l : categories) {
                items.add(l);
            }
            databaseContext.writeCategories(items, strategy);
        }

    }

    public void writeQuestions(List<Question> questions) {
        if (questions.isEmpty()) {
            throw new DatabaseException("can not set questions: list is empty");
        } else {
            List<ListItem> items = new ArrayList<>();
            for (ListItem l : questions) {
                items.add(l);
            }
            databaseContext.writeQuestions(items, strategy);
        }

    }

    public List<String> getCategoryDescriptions() {
        List<String> desc = new ArrayList<>();
        for (ListItem l : this.readCategories()) {
            Category temp = (Category) l;
            desc.add(temp.getDescription());
            System.out.println("added description by 1");
        }
        return desc;
    }

    public List<String> getCategoryNames() {
        List<String> names = new ArrayList<>();
        Iterator it = databaseContext.loadCategories(strategy).iterator();
        while (it.hasNext()) {
            names.add(((Category) it.next()).getName());
        }
        return names;
    }

    public List<String> getQuestionStatements(Question q) {
        List<String> stat = new ArrayList<>();
        Iterator it = databaseContext.loadQuestions(strategy).iterator();
        while (it.hasNext()) {
            Question question;
            question = (Question) it.next();
            if (question.equals(q)) {
                stat = question.getStatements();
                break;
            }
        }
        return stat;
    }

    public ArrayList<String> getCategoryNamesWithoutDuplicates() {
        ArrayList<String> newList = new ArrayList<>();
        Iterator it = databaseContext.loadCategories(strategy).iterator();
        while (it.hasNext()) {
            Category c = (Category) it.next();
            if (!newList.contains(c.getName())) {
                newList.add(c.getName());
            }
        }
        return newList;
    }

    public Category getCategoryByDescription(String desc) {
        Category out = null;
        for (ListItem observable : readCategories()) {
            if (observable instanceof Category) {
                Category category = (Category) observable;
                if (category.getDescription().equalsIgnoreCase(desc)) {
                    out = category;
                }
            } else {
                throw new DatabaseException("wrong type of observable in list");
            }
            if (out != null) break;
        }
        return null;
    }

    public String getEvaluationType() {
        return propertiesDatabase.getEvaluationType();
    }

    public Boolean testIsCompleted() {
        return propertiesDatabase.isTestIsCompleted();
    }

    public void setTestIsCompleted(Boolean isCompleted) throws IOException {
        propertiesDatabase.setIsCompleted(isCompleted);
    }

    public void setLastTestScores(String scores) throws IOException {
        propertiesDatabase.setLastTestScores(scores);
    }

    public String getLastTestScores() {
        return propertiesDatabase.getLastTestScores();
    }
}



