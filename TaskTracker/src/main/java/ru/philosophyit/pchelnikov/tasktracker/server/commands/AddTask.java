package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.objects.TaskBuilder;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.StringConstants;


@NoArgsConstructor
@Component
public class AddTask implements Strategy {
    @Override
    public String apply(String[] arguments, Users users, Tasks tasks) {
        checkCommandSize(arguments);

        Task newTask = TaskBuilder.builder()
                .id(arguments[1])
                .title(arguments[2])
                .description(arguments[3])
                .userId(arguments[4])
                .deadline(arguments[5])
                .status(arguments[6])
                .build();


        if (tasks.getTaskMap().containsKey(newTask.getId())) {
            throw new RuntimeException("Указан повторяющийся id задания.");
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
                    StringConstants.REQUEST_MAPPING + StrategyEnum.ADD_TASK + ",[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }
    }
}