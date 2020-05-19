package database;
import model.ListItem;
import java.util.List;

public class DatabaseContext {

    public DatabaseContext() {

    }

    public List<ListItem> loadCategories(String strategy) {
        return SimpleDatabaseFactorySingleton.getInstance().loadCategories(strategy);
    }

    public List<ListItem> loadQuestions(String strategy) {
        return SimpleDatabaseFactorySingleton.getInstance().loadQuestions(strategy);
    }

    public void writeCategories(List<ListItem> items, String strategy) {
        SimpleDatabaseFactorySingleton.getInstance().writeCategories(items, strategy);
    }

    public void writeQuestions(List<ListItem> items, String strategy) {
        SimpleDatabaseFactorySingleton.getInstance().writeQuestions(items, strategy);
    }
}
