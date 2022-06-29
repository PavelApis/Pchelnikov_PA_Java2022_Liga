package server.commands;

import objects.Task;
import objects.User;
import parser.TasksParser;
import parser.UsersParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.function.Consumer;

public class Clear implements Consumer<String[]> {
    Map<Integer, User> users;
    Map<Integer, Task> tasks;
    public Clear( Map<Integer, User> users, Map<Integer, Task> tasks){
        this.users = users;
        this.tasks = tasks;
    }
    @Override
    public void accept(String[] strings) {
        users.clear();
        tasks.clear();
        try {
            PrintWriter writer =
                    new PrintWriter(
                            TasksParser.pathToTasks.toFile());
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла users.csv не обнаружено в resources директории.");
        }
        try {
            PrintWriter writer =
                    new PrintWriter(
                            UsersParser.pathToUsers.toFile());
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла tasks.csv не обнаружено в resources директории.");
        }
    }
}
