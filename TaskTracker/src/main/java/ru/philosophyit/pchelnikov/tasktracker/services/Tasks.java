package ru.philosophyit.pchelnikov.tasktracker.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.utils.ParsingCSVUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Tasks {
    public static final Path pathToTasks = FileSystems.getDefault().getPath("src", "main", "resources", "tasks.csv");
    @Getter
    Map<Integer, Task> taskMap;
    private final Users users;

    public Tasks(@Autowired Users users) {
        this.users = users;
        var tasks = new HashMap<Integer, Task>();
        List<String[]> csvLines = ParsingCSVUtils.readCSV(pathToTasks.toString());
        for (int i = 0; i < csvLines.size(); i++) {
            String[] line = csvLines.get(i);
            addTaskFromLine(tasks, i, line);
        }
        taskMap = tasks;
    }

    public void addTaskFromLine(Map<Integer, Task> tasks, int i, String[] line) {
        ParsingCSVUtils.checkTaskCSVLineSize(i + 1, line);
        int id = ParsingCSVUtils.readTaskIdFromCSVLine(i + 1, line[0]);
        String title = line[1];
        String description = line[2];
        int userId = ParsingCSVUtils.readUserIdFromCSVLine(i + 1, line[3]);
        Date deadline = ParsingCSVUtils.readDeadlineDateFromCSVLine(i + 1, line[4]);
        if (tasks.containsKey(id)) {
            throw new RuntimeException("В строке номер " + (i + 1) + " указан повторяющийся id задания.");
        } else {
            Task newTask = new Task(id, title, description, userId, deadline);
            if (line.length == 6) {
                Status status = ParsingCSVUtils.readStatus(i + 1, line[5]);
                newTask.setStatus(status);
            }
            try {
                users.get(userId).getTasks().add(newTask);
            } catch (NullPointerException e) {
                throw new RuntimeException("Пользователя с userId строки номер " + (i + 1) + " не существует.");
            }
            tasks.put(id, newTask);
        }
    }

    public Task get(int id) {
        return taskMap.get(id);
    }

    public void checkTaskId(int taskId) {
        if (!taskMap.containsKey(taskId)) {
            throw new RuntimeException("Задания с таким task_id не существует.");
        }
    }
}
