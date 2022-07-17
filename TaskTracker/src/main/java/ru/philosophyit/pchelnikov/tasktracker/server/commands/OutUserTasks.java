package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;
import ru.philosophyit.pchelnikov.tasktracker.utils.StringConstants;

@NoArgsConstructor
@Component
public class OutUserTasks implements Strategy {

    private String filteredUserTasks(String[] command, Users users) {
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

    private String allUserTasks(String[] command, Users users) {
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
    public String apply(String[] command, Users users, Tasks tasks) {
        if (command.length == 2) {
            return allUserTasks(command, users);
        } else if (command.length == 4) {
            return filteredUserTasks(command, users);
        } else {
            return "Неверный формат запроса " + StringConstants.REQUEST_MAPPING + StrategyEnum.OUT_USER_TASKS;
        }
    }
}
