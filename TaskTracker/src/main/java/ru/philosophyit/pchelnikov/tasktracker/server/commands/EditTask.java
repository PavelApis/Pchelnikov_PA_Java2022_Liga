package ru.philosophyit.pchelnikov.tasktracker.server.commands;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.objects.TaskBuilder;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.StringConstants;

@NoArgsConstructor
@Component
public class EditTask implements Strategy {
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
                    StringConstants.REQUEST_MAPPING + StrategyEnum.EDIT_TASK + ",[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }
    }
}