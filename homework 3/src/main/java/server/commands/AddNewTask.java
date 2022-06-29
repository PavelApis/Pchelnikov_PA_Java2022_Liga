package server.commands;

import objects.Status;
import objects.Task;
import objects.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class AddNewTask implements Consumer<String[]> {
    HashMap<Integer, Task> tasks;
    HashMap<Integer, User> users;
    public AddNewTask(HashMap<Integer, User> users, HashMap<Integer, Task> tasks){
        this.users = users;
        this.tasks = tasks;
    }
    @Override
    public void accept(String[] command) {
        checkCommandSize(command);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id добавляемого задания.");
        int id = Readers.readId(scanner.nextLine());
        System.out.println("Введите title добавляемого задания.");
        String title =scanner.nextLine();
        System.out.println("Введите description добавляемого задания.");
        String description = scanner.nextLine();
        System.out.println("Введите user_id добавляемого задания.");
        int userId =  Readers.readId(scanner.nextLine());
        System.out.println("Введите deadline добавляемого задания.");
        Date deadline = Readers.readDeadline(scanner.nextLine());
        System.out.println("Введите status добавляемого задания.");
        Status status = Readers.readStatus(scanner.nextLine());

        if (tasks.containsKey(id)) {
            throw new RuntimeException("В комманде указан повторяющийся id задания.");
        } else {
            Task newTask = new Task(id, title, description, userId, deadline, status);
            try {
                users.get(userId).getTasks().add(newTask);
            } catch (NullPointerException e) {
                throw new RuntimeException("Пользователя с userId не существует.");
            }
            tasks.put(id, newTask);
        }
    }

    private void checkCommandSize(String[] command) {
        if (command.length != 1) {
            throw new RuntimeException("Неверный формат команды добавления задачи команда должна иметь формат: add-task.");
        }
    }
}
