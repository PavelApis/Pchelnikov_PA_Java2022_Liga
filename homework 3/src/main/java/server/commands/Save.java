package server.commands;

import objects.Task;
import objects.User;
import parser.TasksParser;
import parser.UsersParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Save implements Consumer<String[]> {
    Map<Integer, User> users;
    Map<Integer, Task> tasks;

    public Save( Map<Integer, User> users, Map<Integer, Task> tasks){
        this.users = users;
        this.tasks = tasks;
    }

    @Override
    public void accept(String[] strings) {
        try {
            PrintWriter writer =
                    new PrintWriter(
                            TasksParser.pathToTasks.toFile());
            tasks.values().forEach(user -> writer.println(user.toString()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла users.csv не обнаружено в resources директории.");
        }
        try {
            PrintWriter writer =
                    new PrintWriter(
                            UsersParser.pathToUsers.toFile());
            users.values().forEach(task -> writer.println(task.toString()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла tasks.csv не обнаружено в resources директории.");
        }
    }
}
