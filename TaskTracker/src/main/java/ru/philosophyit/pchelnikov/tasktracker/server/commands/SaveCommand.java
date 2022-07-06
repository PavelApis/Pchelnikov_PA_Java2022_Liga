package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@AllArgsConstructor
public class SaveCommand extends Command {
    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;

    @Override
    public String apply(String[] strings) {
        try {
            PrintWriter writer =
                    new PrintWriter(
                            Tasks.pathToTasks.toFile());
            tasks.getTaskMap().values().forEach(user -> writer.println(user.toString()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла tasks.csv не обнаружено в resources директории.");
        }
        try {
            PrintWriter writer =
                    new PrintWriter(
                            Users.pathToUsers.toFile());
            users.getUserMap().values().forEach(task -> writer.println(task.toString()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файла users.csv не обнаружено в resources директории.");
        }
        return "Файлы успешно сохранены.";
    }
}
