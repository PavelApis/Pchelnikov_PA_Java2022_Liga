package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.StringConstants;

@NoArgsConstructor
@Component
public class Info implements Strategy {
    @Override
    public String apply(String[] strings, Users users, Tasks tasks) {
        return StringConstants.INFO;
    }
}
