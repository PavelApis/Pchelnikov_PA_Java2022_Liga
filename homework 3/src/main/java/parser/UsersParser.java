package parser;

import au.com.bytecode.opencsv.CSVReader;
import objects.User;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class UsersParser {
    public static final Path pathToUsers = FileSystems.getDefault().getPath("src", "main", "resources", "users.csv");

    public HashMap<Integer, User> parseUsersFile() {
        var users = new HashMap<Integer, User>();
        List<String[]> csvLines = ParsingCSVUtils.readCSV(pathToUsers);
        for (int i = 0; i < csvLines.size(); i++) {
            String[] line = csvLines.get(i);
            ParsingCSVUtils.checkUserCSVLineSize(i + 1, line);
            int id = ParsingCSVUtils.readUserIdFromCSVLine(i + 1, line[0]);
            String name = line[1];
            User newUser = new User(id, name);
            users.put(id, newUser);
        }
        return users;
    }
}
