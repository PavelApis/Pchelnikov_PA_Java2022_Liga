package ru.philosophyit.pchelnikov.tasktracker.services;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.User;
import ru.philosophyit.pchelnikov.tasktracker.utils.ParsingCSVUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Users {
    public static final Path pathToUsers = FileSystems.getDefault().getPath("src", "main", "resources", "users.csv");
    @Getter
    Map<Integer, User> userMap;

    public Users() {
        var users = new HashMap<Integer, User>();
        List<String[]> csvLines = ParsingCSVUtils.readCSV(pathToUsers.toString());
        for (int i = 0; i < csvLines.size(); i++) {
            String[] line = csvLines.get(i);
            ParsingCSVUtils.checkUserCSVLineSize(i + 1, line);
            int id = ParsingCSVUtils.readUserIdFromCSVLine(i + 1, line[0]);
            String name = line[1];
            User newUser = new User(id, name);
            users.put(id, newUser);
        }
        userMap = users;
    }

    public User get(int id) {
        return userMap.get(id);
    }

    public void checkUserId(int userId) {
        if (!userMap.containsKey(userId)) {
            throw new RuntimeException("Пользователя с таким user_id не существует.");
        }
    }
}
