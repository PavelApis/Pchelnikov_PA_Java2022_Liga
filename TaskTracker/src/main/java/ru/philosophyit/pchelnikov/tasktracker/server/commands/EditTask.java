package ru.philosophyit.pchelnikov.tasktracker.server.commands;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.*;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

@AllArgsConstructor
@Component
public class EditTask extends Strategy {
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

        try {
            users.getUserMap().get(oldTask.getUserId()).getTasks().remove(oldTask);
            users.getUserMap().get(newTask.getUserId()).getTasks().add(newTask);
            return "Задание: {" + oldTask + "} успешно изменено на: {" + newTask + "}.";
        } catch (NullPointerException e) {
            throw new RuntimeException("Пользователя с userId не существует.");
        }
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 7) {
            throw new RuntimeException("Неверный формат запроса редактирования задачи, ожидаеммый формат: " +
                    "/task-tracker?command=edit_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }
    }
}