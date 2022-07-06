package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.objects.TaskBuilder;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;


@AllArgsConstructor
@Component
public class AddTaskCommand extends Command {
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

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


        if (tasks.getTaskMap().containsKey(newTask.getId())) {
            throw new RuntimeException("В комманде указан повторяющийся id задания.");
        } else {
            try {
                users.get(newTask.getUserId()).getTasks().add(newTask);
            } catch (NullPointerException e) {
                throw new RuntimeException("Пользователя с userId не существует.");
            }
            tasks.getTaskMap().put(newTask.getId(), newTask);
            return "Задание: {" + newTask + "} успешно добавлено.";
        }
    }

    private void checkCommandSize(String[] command) {
        if (command.length != 7) {
            throw new RuntimeException("Неверный формат запроса добавления задачи, ожидаеммый формат: " +
                    "/task-tracker/add-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS].");
        }
    }
}