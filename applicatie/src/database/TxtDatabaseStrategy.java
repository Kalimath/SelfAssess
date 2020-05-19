package database;
import model.ListItem;
import java.util.*;

public abstract class TxtDatabaseStrategy implements DatabaseStrategy {
    protected String path;

    public TxtDatabaseStrategy(String path) {
        this.path = path;
    }

    public abstract List<ListItem> load();

    public abstract void update(List<ListItem> items);
}
