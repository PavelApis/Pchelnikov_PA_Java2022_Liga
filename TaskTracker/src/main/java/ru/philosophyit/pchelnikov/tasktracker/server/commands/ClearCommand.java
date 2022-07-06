package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@AllArgsConstructor
public class ClearCommand extends Command {

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