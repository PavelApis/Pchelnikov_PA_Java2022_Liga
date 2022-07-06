package ru.philosophyit.pchelnikov.tasktracker.server;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Executor {
    @Autowired
    private final Commander commander;
    public String execute(String argumentsLine){
        return commander.acceptCommand(CommandArgumentsFactory.makeCommandArguments(argumentsLine));
    }
}
