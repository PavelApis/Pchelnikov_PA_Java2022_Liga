package ru.philosophyit.pchelnikov.tasktracker.objects;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class User {
    @Getter
    private final int id;
    @Getter
    private final String name;
    @Getter
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
