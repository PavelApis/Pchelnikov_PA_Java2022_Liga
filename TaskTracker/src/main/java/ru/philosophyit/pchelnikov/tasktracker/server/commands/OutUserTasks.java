package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

@AllArgsConstructor
public class OutUserTasks extends Strategy {

    @Autowired
    private final Users users;

    private String filteredUserTasks(String[] command) {
        int id;
        Status status;
        checkFlag(command[1]);
        id = ReadersUtils.readId(command[2]);
        status = ReadersUtils.readStatus(command[3]);
        users.checkUserId(id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<pre>");
        users.get(id).getTasks().stream().filter(task -> task.getStatus().equals(status)).forEach(task -> stringBuilder.append(task).append("\n"));
        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

    private String allUserTasks(String[] command) {
        int id = ReadersUtils.readId(command[1]);
        StringBuilder stringBuilder = new StringBuilder();
        users.checkUserId(id);
        stringBuilder.append("<pre>");
        users.get(id).getTasks().forEach(task -> stringBuilder.append(task).append("\n"));
        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

    private void checkFlag(String flag) {
        if (!flag.equals("filter")) {
            throw new RuntimeException("Неверный формат флага, флаг должен иметь вид: filter");
        }
    }

    @Override
    public String apply(String[] command) {
        if (command.length == 2) {
            return allUserTasks(command);
        } else if (command.length == 4) {
            return filteredUserTasks(command);
        } else {
            return "Неверный формат команды sout-user-tasks";
        }
    }
}
