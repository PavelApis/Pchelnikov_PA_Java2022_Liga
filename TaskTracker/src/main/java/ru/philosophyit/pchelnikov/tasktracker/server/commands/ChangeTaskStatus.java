package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;


@AllArgsConstructor
public class ChangeTaskStatus extends Strategy {
    @Autowired
    private final Tasks tasks;

    @Override
    public String apply(String[] command) {
        wrongChangeStatusCommandSize(command);
        int id;
        Status newStatus;
        id = ReadersUtils.readId(command[1]);
        newStatus = ReadersUtils.readStatus(command[2]);
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