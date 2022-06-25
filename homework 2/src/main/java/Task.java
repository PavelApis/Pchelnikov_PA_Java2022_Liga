import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final int userId;

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private final Date deadline;
    private Status status;

    public Task(int id, String title, String definition, int userId, Date deadline) {
        this.id = id;
        this.title = title;
        this.description = definition;
        this.deadline = deadline;
        this.userId = userId;
        this.status = Status.NEW;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return id + ", \"" + title + "\", \"" + description + "\", \"" + formatter.format(deadline) + "\", " + status;
    }
}
