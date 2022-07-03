package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.objects.Users;

import java.util.function.Function;


@AllArgsConstructor
public class ChangeTaskStatus implements Function<String[], String> {
    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;

    @Override
    public String apply(String[] command) {
        wrongChangeStatusCommandSize(command);
        int id;
        Status newStatus;
        id = Readers.readId(command[1]);
        newStatus = Readers.readStatus(command[2]);
        tasks.checkTaskId(id);
        tasks.get(id).setStatus(newStatus);
        return "Статус задания с id: " + id +  " успешно изменен на " + newStatus + ".";
    }

    private void wrongChangeStatusCommandSize(String[] command) {
        if (command.length != 3) {
            throw new RuntimeException("Неверный формат запроса изменения статуса, команда должна иметь формат: /task-tracker?command=change-status [TASK_ID] [STATUS]");
        }
    }
}