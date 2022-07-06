package ru.philosophyit.pchelnikov.tasktracker.server.commands;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.*;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

import java.util.Date;

@AllArgsConstructor
public class EditTaskCommand extends Command {
    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;


    @Override
    public String apply(String[] command) {
        checkCommandSize(command);

        Task newTask = TaskBuilder.builder()
                .id(command[1])
                .title(command[2])
                .description(command[3])
                .userId(command[4])
                .deadline(command[5])
                .status(command[6])
                .build();

        tasks.checkTaskId(newTask.getId());
        Task oldTask = tasks.get(newTask.getId());
        tasks.getTaskMap().remove(oldTask.getId());
        tasks.getTaskMap().put(newTask.getId(), newTask);

        users.getUserMap().get(oldTask.getUserId()).getTasks().remove(oldTask);
        users.getUserMap().get(newTask.getUserId()).getTasks().add(newTask);

        return "Задание: {" + oldTask + "} успешно изменено на: {" + newTask + "}.";
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 7) {
            throw new RuntimeException("Неверный формат команды редактирования задачи, ожидаеммый формат: " +
                    "/task-tracker/edit-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS]");
        }
    }
}