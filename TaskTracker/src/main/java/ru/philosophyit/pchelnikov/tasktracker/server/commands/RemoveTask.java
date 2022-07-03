package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.objects.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.objects.Users;

import java.util.function.Function;

@AllArgsConstructor
public class RemoveTask implements Function<String[], String> {
    private final Users users;
    private final Tasks tasks;

    @Override
    public String apply(String[] command) {
        checkCommandSize(command);
        int id = Readers.readId(command[1]);
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