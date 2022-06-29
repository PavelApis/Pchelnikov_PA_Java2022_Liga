package server;

import objects.Task;
import objects.User;
import parser.TasksParser;
import parser.UsersParser;
import server.commands.Commander;
import server.commands.PrintInfo;

import java.util.HashMap;
import java.util.Scanner;


/*
    Сервер отвечает на запросы пользователя в консоли.
    Запросы могут быть следующие:
        -- info - выводит информацию о доступных запросах.

        -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.

        -- sout-user-tasks [USER_ID] - выводит все задания пользователя, в случае указания фильтра:
           sout-user-tasks -filter [USER_ID] [new, in_work, done], будут выведены задания с указанным статусом.

        -- add-task - открывает интерфейс построения новой команды, последовательно будет запрошен ввод
                        полей [TASK_ID], [TITLE], [DESCRIPTION], [USER_ID], [DEADLINE], [STATUS].

        -- edit-task [TASK_ID] - открывает интерфейст редактирования команды, последовательно будет запрошен ввод
                        полей  [TITLE], [DESCRIPTION], [USER_ID], [DEADLINE], [STATUS].

        -- remove-task [TASK_ID] - удаляет задание с указаным id.

        -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.

        -- cleat - полностью очищает users, tasks, файлы users.csv и tasks.csv.

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
        Commander commander = new Commander(users, tasks);
        while (true) {
            String[] command = in.nextLine().split(" ");
            try {
                commander.acceptCommand(command);
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Команда не выполнена.");
            }
        }
    }


    private void addNewTask(String[] command) {

    }

    private void welcome() {
        System.out.println("Добро пожаловать на сервер мониторинга задач!");
        new PrintInfo().printInfo();
    }

}
