package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.objects.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.objects.Users;

import java.util.Date;
import java.util.function.Function;

@AllArgsConstructor
@Component
public class AddTaskCommand implements Function<String[], String> {
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

    @Override
    public String apply(String[] command) {
        checkCommandSize(command);

        int id = Readers.readId(command[1]);
        String title = command[2];
        String description = command[3];
        int userId = Readers.readId(command[4]);
        Date deadline = Readers.readDeadline(command[5]);
        Status status = Readers.readStatus(command[6]);

        if (tasks.getTaskMap().containsKey(id)) {
            throw new RuntimeException("В комманде указан повторяющийся id задания.");
        } else {
            Task newTask = new Task(id, title, description, userId, deadline, status);
            try {
                users.get(userId).getTasks().add(newTask);
            } catch (NullPointerException e) {
                throw new RuntimeException("Пользователя с userId не существует.");
            }
            tasks.getTaskMap().put(id, newTask);
            return "Задание: {" +newTask+  "} успешно добавлено.";
        }
    }

    private void checkCommandSize(String[] command) {
        if (command.length != 7) {
            throw new RuntimeException("Неверный формат запроса добавления задачи, ожидаеммый формат: " +
                    "/task-tracker/add-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS].");
        }
    }
}