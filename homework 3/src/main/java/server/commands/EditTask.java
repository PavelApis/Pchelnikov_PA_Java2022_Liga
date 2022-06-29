package server.commands;

import objects.Status;
import objects.Task;
import objects.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class EditTask implements Consumer<String[]> {
    HashMap<Integer, User> users;
    HashMap<Integer, Task> tasks;


    public EditTask(HashMap<Integer, User> users, HashMap<Integer, Task> tasks) {
        this.users = users;
        this.tasks = tasks;
    }


    @Override
    public void accept(String[] command) {
        checkCommandSize(command);
        int id = Readers.readId(command[1]);
        Commander.checkTaskId(tasks, id);
        Task task = tasks.get(id);
        User oldUser = users.get(task.getUserId());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите новый title задания.");
        String title = scanner.nextLine();

        System.out.println("Введите новый description задания.");
        String description = scanner.nextLine();

        System.out.println("Введите новый user_id задания.");
        int userId = Readers.readId(scanner.nextLine());
        Commander.checkUserId(users, userId);

        System.out.println("Введите новый deadline задания.");
        Date deadline = Readers.readDeadline(scanner.nextLine());

        System.out.println("Введите новый status задания.");
        Status status = Readers.readStatus(scanner.nextLine());

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDeadline(deadline);
        task.setUserId(userId);

        oldUser.getTasks().remove(task);
        users.get(userId).getTasks().add(task);
    }

    public void checkCommandSize(String[] command) {
        if (command.length != 2) {
            throw new RuntimeException("Неверный формат команды редактирования задачи, ожидаеммый формат: edit-task [TASK_ID].");
        }
    }
}
