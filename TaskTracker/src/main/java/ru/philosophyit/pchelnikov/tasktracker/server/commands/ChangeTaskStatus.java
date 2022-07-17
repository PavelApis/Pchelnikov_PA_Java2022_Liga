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
public class ChangeTaskStatus implements Strategy {
    @Override
    public String apply(String[] arguments, Users users, Tasks tasks) {
        wrongChangeStatusCommandSize(arguments);
        int id;
        Status newStatus;
        id = ReadersUtils.readId(arguments[1]);
        newStatus = ReadersUtils.readStatus(arguments[2]);
        tasks.checkTaskId(id);
        tasks.get(id).setStatus(newStatus);
        return "Статус задания с id: " + id + " успешно изменен на " + newStatus + ".";
    }

    private void wrongChangeStatusCommandSize(String[] command) {
        if (command.length != 3) {
            throw new RuntimeException("Неверный формат запроса изменения статуса, команда должна иметь формат: " +
                    StringConstants.REQUEST_MAPPING + StrategyEnum.CHANGE_STATUS + ",[TASK_ID],[STATUS]");
        }
    }
}