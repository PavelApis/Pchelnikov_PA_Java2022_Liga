import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String name;
    private final List<Task> tasks;


    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    @Override
    public String toString() {
        return id + ", " + name + ", " + "\"" + tasks.toString() + "\"";
    }
}
