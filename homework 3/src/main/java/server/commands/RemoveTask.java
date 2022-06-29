package server.commands;

import objects.Task;
import objects.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class RemoveTask implements Consumer<String[]> {
    private final Map<Integer, User> users;
    private final Map<Integer, Task> tasks;

    public RemoveTask(Map<Integer, User> users,  Map<Integer, Task> tasks) {
        this.users = users;
        this.tasks = tasks;
    }

    @Override
    public void accept(String[] command) {
        checkCommandSize(command);
        int id = Readers.readId(command[1]);
        if(!tasks.containsKey(id)){
            System.out.println("Задания с таким id не существует.");
            return;
        }
        Task task = tasks.get(id);
        tasks.remove(id);
        users.get(task.getUserId()).getTasks().remove(task);
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 2) {
            throw new RuntimeException("Неверный формат команды удаления задания, ожидаемый формат: remove-task [TASK_ID]");
        }
    }
}
