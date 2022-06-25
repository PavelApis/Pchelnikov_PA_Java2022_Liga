import java.util.HashMap;
import java.util.Scanner;


/*
    Сервер отвечает на запросы пользователя в консоли.
    Запросы могут быть следующие:
        -- info - выводит информацию о доступных запросах.

        -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.

        -- sout-user-tasks [USER_ID] - выводит все задания пользователя, в случае указания фильтра:
           sout-user-tasks -filter [USER_ID] [new, in_work, done], будут выведены задания с указанным статусом.

        -- close - заканчивает работу сервера.
 */

public class Server implements Runnable {

    private final HashMap<Integer, User> users;
    private final HashMap<Integer, Task> tasks;

    public Server() {
        users = new UsersParser().parseUsersFile();
        tasks = new TasksParser(users).parseTasksFile();
    }

    @Override
    public void run() {
        welcome();
        Scanner in = new Scanner(System.in);
        while (true) {
            String[] command = in.nextLine().split(" ");
            switch (command[0]) {
                case "change-status" -> {
                    changeTask(command);
                }
                case "sout-user-tasks" -> {
                    soutUserTasks(command);
                }
                case "close" -> {
                    return;
                }
                case "info" -> {
                    printInfo();
                }
                default -> {
                    System.out.println("Неопознанная команда. Введите команду info, чтобы узнать какие команды доступны.");
                }
            }
        }
    }

    private void soutUserTasks(String[] command) {
        if (command.length == 2) {
            soutAllUserTasks(command);
        } else if (command.length == 4) {
            soutFilteredUserTasks(command);
        } else {
            System.out.println("Неверный формат команды sout-user-tasks");
            System.out.println("Команда не выполнена.");
        }
    }

    private void soutFilteredUserTasks(String[] command) {
        int id;
        Status status;
        try {
            checkFlag(command[1]);
            id = readId(command[2]);
            status = readStatus(command[3]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Команда не выполнена.");
            return;
        }
        if (users.containsKey(id)) {
            users.get(id).getTasks().stream().filter(task -> task.getStatus().equals(status)).forEach(System.out::println);
        } else {
            System.out.println("Пользователя с таким id не существует.\nКоманда не выполнена");
        }
    }

    private void soutAllUserTasks(String[] command) {
        int id = readId(command[1]);
        if (users.containsKey(id)) {
            users.get(id).getTasks().forEach(System.out::println);
        } else {
            System.out.println("Пользователя с таким id не существует.\nКоманда не выполнена");
        }
    }

    private void changeTask(String[] command) {
        if (wrongChangeStatusCommandSize(command)) {
            return;
        }
        int id;
        Status newStatus;
        try {
            id = readId(command[1]);
            newStatus = readStatus(command[2]);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Команда не выполнена.");
            return;
        }
        if (tasks.containsKey(id)) {
            tasks.get(id).setStatus(newStatus);
        } else {
            System.out.println("Задания с таким id не существует.\nКоманда не выполнена");
        }

    }

    private int readId(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат id.");
        }
    }

    private boolean wrongChangeStatusCommandSize(String[] command) {
        if (command.length != 3) {
            System.out.println("Неверный формат команды изменения статуса, команда должна иметь формат: change-status [TASK_ID] [STATUS]");
            System.out.println("Команда не выполнена.");
            return true;
        }
        return false;
    }

    private void checkFlag(String flag) {
        if (!flag.equals("-filter")) {
            throw new RuntimeException("Неверный формат флага, флаг должен иметь вид: -filter");
        }
    }

    private Status readStatus(String data) {
        switch (data) {
            case "new" -> {
                return Status.NEW;
            }
            case "in_work" -> {
                return Status.IN_WORK;
            }
            case "done" -> {
                return Status.DONE;
            }
            default -> {
                throw new RuntimeException("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
            }
        }
    }

    private void welcome(){
        System.out.println("Добро пожаловать на сервер мониторинга задач!");
        printInfo();
    }

    private void printInfo(){
        System.out.println(" Сервер отвечает на запросы пользователя в консоли.\n" +
                "    Запросы могут быть следующие:\n" +
                "        -- info - выводит информацию о доступных запросах.\n" +
                "        \n" +
                "        -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.\n" +
                "\n" +
                "        -- sout-user-tasks [USER_ID] - выводит все задания пользователя, в случае указания фильтра:\n" +
                "           sout-user-tasks -filter [USER_ID] [new, in_work, done], будут выведены задания с указанным статусом.\n" +
                "\n" +
                "        -- close - заканчивает работу сервера.");
    }

}
