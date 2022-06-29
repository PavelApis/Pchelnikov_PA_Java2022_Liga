package server.commands;

import objects.Status;
import objects.Task;

import java.util.HashMap;
import java.util.function.Consumer;

public class ChangeTaskStatus implements Consumer<String[]> {
    private final HashMap<Integer, Task> tasks;

    public ChangeTaskStatus(HashMap<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void accept(String[] command) {
        wrongChangeStatusCommandSize(command);
        int id;
        Status newStatus;
        id = Readers.readId(command[1]);
        newStatus = Readers.readStatus(command[2]);
        Commander.checkTaskId(tasks, id);
        tasks.get(id).setStatus(newStatus);
    }

    private void wrongChangeStatusCommandSize(String[] command) {
        if (command.length != 3) {
            throw new RuntimeException("Неверный формат команды изменения статуса, команда должна иметь формат: change-status [TASK_ID] [STATUS]");
        }
    }
}
