package ru.philosophyit.pchelnikov.tasktracker.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.server.commands.*;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

import java.util.Map;

@Component
public class Commander {
    Map<Strategies, Strategy> strategyMap;
    @Autowired
    Users users;
    @Autowired
    Tasks tasks;

    public Commander(Users users, Tasks tasks) {
        this.users = users;
        this.tasks = tasks;

        strategyMap = Map.of(
                Strategies.change_status, new ChangeTaskStatus(tasks),
                Strategies.out_user_tasks, new OutUserTasks(users),
                Strategies.add_task, new AddTask(users, tasks),
                Strategies.edit_task, new EditTask(users, tasks),
                Strategies.remove_task, new RemoveTask(users, tasks),
                Strategies.save, new Save(users, tasks),
                Strategies.clear, new Clear(users, tasks),
                Strategies.info, new Info()
        );
    }

    public String acceptCommand(String[] command) {
        try {
            Strategies strategy = Strategies.valueOf(command[0]);
            if (strategyMap.containsKey(strategy)) {
                return strategyMap.get(strategy).apply(command);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Неопознанная команда. Введите команду info, чтобы узнать какие команды доступны.");
        }
    }
}
