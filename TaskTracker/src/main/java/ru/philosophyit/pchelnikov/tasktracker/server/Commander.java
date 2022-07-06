package ru.philosophyit.pchelnikov.tasktracker.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.server.commands.*;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

import java.util.Map;

@Component
public class Commander {
    Map<String, Command> commands;
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

    public Commander(Users users, Tasks tasks) {
        this.users = users;
        this.tasks = tasks;

        commands = Map.of(
                "change-status", new ChangeTaskStatus(tasks),
                "out-user-tasks", new OutUserTasksCommand(users),
                "add-task", new AddTaskCommand(users, tasks),
                "edit-task", new EditTaskCommand(users, tasks),
                "remove-task", new RemoveTask(users, tasks),
                "save", new SaveCommand(users, tasks),
                "clear", new ClearCommand(users, tasks),
                "info", new InfoCommand()
        );
    }

    public String acceptCommand(String[] command) {
        if (commands.containsKey(command[0])) {
            return commands.get(command[0]).apply(command);
        } else {
            return "Неопознанная команда. Введите команду info, чтобы узнать какие команды доступны.";
        }
    }
}
