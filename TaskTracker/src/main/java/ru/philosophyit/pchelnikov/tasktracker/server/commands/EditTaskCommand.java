package ru.philosophyit.pchelnikov.tasktracker.server.commands;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.*;

import java.util.Date;
import java.util.Scanner;
import java.util.function.Function;

@AllArgsConstructor
public class EditTaskCommand implements Function<String[], String> {
    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;


    @Override
    public String apply(String[] command) {
        checkCommandSize(command);
        int id = Readers.readId(command[1]);
        tasks.checkTaskId(id);
        Task task = tasks.get(id);
        String oldTask = task.toString();
        User oldUser = users.get(task.getUserId());

        String title = command[2];
        String description = command[3];
        int userId = Readers.readId(command[4]);
        users.checkUserId(userId);
        Date deadline = Readers.readDeadline(command[5]);
        Status status = Readers.readStatus(command[6]);

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDeadline(deadline);
        task.setUserId(userId);

        oldUser.getTasks().remove(task);
        users.get(userId).getTasks().add(task);
        return "Задание: {" + oldTask + "} успешно изменено на: {" + task + "}.";
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 7) {
            throw new RuntimeException("Неверный формат команды редактирования задачи, ожидаеммый формат: " +
                    "/task-tracker/edit-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS]");
        }
    }
}