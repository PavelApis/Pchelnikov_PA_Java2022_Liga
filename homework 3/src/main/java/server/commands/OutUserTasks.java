package server.commands;

import objects.Status;
import objects.Task;
import objects.User;

import java.util.HashMap;
import java.util.function.Consumer;

public class OutUserTasks implements Consumer<String[]> {

    private final HashMap<Integer, User> users;

    public OutUserTasks(HashMap<Integer, User> users) {
        this.users = users;
    }

    @Override
    public void accept(String[] command) {
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
            id = Readers.readId(command[2]);
            status = Readers.readStatus(command[3]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Команда не выполнена.");
            return;
        }
        Commander.checkUserId(users, id);
        users.get(id).getTasks().stream().filter(task -> task.getStatus().equals(status)).forEach(System.out::println);
    }

    private void soutAllUserTasks(String[] command) {
        int id = Readers.readId(command[1]);
        Commander.checkUserId(users, id);
        users.get(id).getTasks().forEach(System.out::println);
    }

    private void checkFlag(String flag) {
        if (!flag.equals("-filter")) {
            throw new RuntimeException("Неверный формат флага, флаг должен иметь вид: -filter");
        }
    }
}
