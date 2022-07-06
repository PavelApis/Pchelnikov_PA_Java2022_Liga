package ru.philosophyit.pchelnikov.tasktracker.objects;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class User {
    private final int id;
    private final String name;
    private final Set<Task> tasks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new HashSet<>();
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}
