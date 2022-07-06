package ru.philosophyit.pchelnikov.tasktracker.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.objects.Users;
import ru.philosophyit.pchelnikov.tasktracker.server.commands.*;

import java.util.Map;
import java.util.function.Function;

@Component
public class Commander {
    Map<String, Function<String[], String>> commands;
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

    public Commander(Users users, Tasks tasks) {
        this.users = users;
        this.tasks = tasks;

        commands = Map.of(
                "change-status", new ChangeTaskStatus(users, tasks),
                "out-user-tasks", new OutUserTasksCommand(users, tasks),
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
