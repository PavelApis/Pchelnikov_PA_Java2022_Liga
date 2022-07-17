package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

public interface Strategy{
    public String apply(String[] arguments, Users users, Tasks tasks);
}
