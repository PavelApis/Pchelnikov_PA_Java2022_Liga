package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.objects.Users;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;

@AllArgsConstructor
public class ClearCommand implements Function<String[], String> {

    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;

    @Override
    public String apply(String[] strings) {
        users.getUserMap().clear();
        tasks.getTaskMap().clear();
        try {
            PrintWriter writer =
                    new PrintWriter(
                            Tasks.pathToTasks.toFile());
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла users.csv не обнаружено в resources директории.");
        }
        try {
            PrintWriter writer =
                    new PrintWriter(
                            Users.pathToUsers.toFile());
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла tasks.csv не обнаружено в resources директории.");
        }
        return "Объекты и файлы успешно очищенны.";
    }
}