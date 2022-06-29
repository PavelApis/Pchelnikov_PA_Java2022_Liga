package server.commands;

import objects.Task;
import objects.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Commander {
    Map<String, Consumer<String[]>> commands;
    private final Map<Integer, User> users;
    private final Map<Integer, Task> tasks;

    public Commander(HashMap<Integer, User> users, HashMap<Integer, Task> tasks) {
        this.users = users;
        this.tasks = tasks;

        commands = Map.of(
                "change-status", new ChangeTaskStatus(tasks),
                "sout-user-tasks", new OutUserTasks(users),
                "add-task", new AddNewTask(users, tasks),
                "edit-task", new EditTask(users, tasks),
                "remove-task", new RemoveTask(users, tasks),
                "save", new Save(users, tasks),
                "clear", new Clear(users, tasks),
                "info", new PrintInfo()
        );
    }

    public void acceptCommand(String[] command) {
        if (commands.containsKey(command[0])) {
            commands.get(command[0]).accept(command);
        } else {
            System.out.println("Неопознанная команда. Введите команду info, чтобы узнать какие команды доступны.");
        }
    }

    public static void checkUserId(Map<Integer, User> users, int userId) {
        if (!users.containsKey(userId)) {
            throw new RuntimeException("Пользователя с таким user_id не существует.");
        }
    }

    public static void checkTaskId(Map<Integer, Task> tasks, int taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new RuntimeException("Задания с таким task_id не существует.");
        }
    }
}
