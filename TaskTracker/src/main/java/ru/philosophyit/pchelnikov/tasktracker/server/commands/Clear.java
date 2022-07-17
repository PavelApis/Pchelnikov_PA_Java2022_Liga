package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@NoArgsConstructor
@Component
public class Clear implements Strategy {

    @Override
    public String apply(String[] arguments, Users users, Tasks tasks) {
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