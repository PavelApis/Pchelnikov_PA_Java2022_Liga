import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TasksParser {
    private final int CSV_LINE_SIZE = 5;
    private final HashMap<Integer, User> users;
    private final Path pathToTasks = FileSystems.getDefault().getPath("src", "main", "resources", "tasks.csv");

    public TasksParser(HashMap<Integer, User> users) {
        this.users = users;
    }

    public HashMap<Integer, Task> parseTasksFile() {
        var tasks = new HashMap<Integer, Task>();
        List<String[]> csvLines = ParsingCSVUtils.readCSV(pathToTasks);
        for (int i = 0; i < csvLines.size(); i++) {
            String[] line = csvLines.get(i);
            ParsingCSVUtils.checkCSVLineSize(i + 1, line, CSV_LINE_SIZE);
            int id = ParsingCSVUtils.readTaskIdFromCSVLine(i + 1, line[0]);
            String title = line[1];
            String description = line[2];
            int userId = ParsingCSVUtils.readUserIdFormCSVLine(i + 1, line[3]);
            Date deadline = ParsingCSVUtils.readDeadlineDateFromCSVLine(i + 1, line[4]);

            if (tasks.containsKey(id)) {
                throw new RuntimeException("В строке номер " + (i + 1) + " указан повторяющийся id задания.");
            } else {
                Task newTask = new Task(id, title, description, userId, deadline);
                tasks.put(id, newTask);
                try {
                    users.get(userId).getTasks().add(newTask);
                } catch (NullPointerException e) {
                    throw new RuntimeException("Пользователя с userId строки номер " + (i + 1) + " не существует.");
                }
            }
        }
        return tasks;
    }
}
