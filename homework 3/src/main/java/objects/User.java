package objects;

import objects.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private final int id;
    private final String name;
    private final Set<Task> tasks;


    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }
    @Override
    public String toString() {
        return id + "," + name;
    }
}
