package database;
import model.ListItem;
import java.util.List;

public interface DatabaseStrategy {
    public List<ListItem> load();
    public void update(List<ListItem> o);
}
