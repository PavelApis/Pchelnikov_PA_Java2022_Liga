package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

@AllArgsConstructor
public class RemoveTask extends Strategy {
    @Autowired
    private final Users users;
    @Autowired
    private final Tasks tasks;

    @Override
    public String apply(String[] command) {
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
            throw new RuntimeException("Неверный формат команды удаления задания, ожидаемый формат: remove-task [TASK_ID]");
        }
    }
}