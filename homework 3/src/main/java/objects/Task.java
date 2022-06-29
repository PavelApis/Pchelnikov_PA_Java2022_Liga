package objects;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private int userId;

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    @Getter
    @Setter
    private Date deadline;
    @Getter
    @Setter
    private Status status;

    public Task(int id, String title, String definition, int userId, Date deadline) {
        this.id = id;
        this.title = title;
        this.description = definition;
        this.deadline = deadline;
        this.userId = userId;
        this.status = Status.NEW;
    }

    public Task(int id, String title, String definition, int userId, Date deadline, Status status) {
        this.id = id;
        this.title = title;
        this.description = definition;
        this.deadline = deadline;
        this.userId = userId;
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ",\"" + title + "\",\"" + description + "\"," + userId + ",\"" + formatter.format(deadline) + "\"," + status;
    }
}
