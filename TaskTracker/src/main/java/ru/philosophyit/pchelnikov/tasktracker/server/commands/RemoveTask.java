package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

@NoArgsConstructor
@Component
public class RemoveTask implements Strategy {
    @Override
    public String apply(String[] command, Users users, Tasks tasks) {
        checkCommandSize(command);
        int id = ReadersUtils.readId(command[1]);
        tasks.checkTaskId(id);
        Task task = tasks.get(id);
        tasks.getTaskMap().remove(id);
        users.get(task.getUserId()).getTasks().remove(task);
        return "Задание: {" + task + "} успешно удалено.";
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 2) {
            throw new RuntimeException("Неверный формат запроса удаления задания, ожидаемый формат:" +
                    "/task-tracker?strategy="+StrategyEnum.OUT_USER_TASKS+",[TASK_ID]");
        }
    }
}