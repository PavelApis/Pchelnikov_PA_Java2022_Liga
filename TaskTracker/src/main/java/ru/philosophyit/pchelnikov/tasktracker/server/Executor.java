package ru.philosophyit.pchelnikov.tasktracker.server;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.server.commands.StrategyEnum;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.StringConstants;

@AllArgsConstructor
@Component
public class Executor {
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

    public String execute(String argumentsLine) {
        try {
            String[] arguments = ArgumentsFactory.makeCommandArguments(argumentsLine);
            return StrategyEnum.valueOf(arguments[0]).apply(arguments, users, tasks);
        } catch (NullPointerException e){
            return StringConstants.WRONG_REQUEST;
        } catch (Exception e) {
            return e.getMessage() + StringConstants.REQUEST_FAILURE;
        }
    }
}
