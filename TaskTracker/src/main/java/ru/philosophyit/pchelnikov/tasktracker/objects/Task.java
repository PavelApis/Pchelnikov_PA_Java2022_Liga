package ru.philosophyit.pchelnikov.tasktracker.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Task {
    private int id;
    private String title;
    private String description;
    private int userId;
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private Date deadline;
    private Status status;

    public Task(int id, String title, String definition, int userId, Date deadline) {
        this.id = id;
        this.title = title;
        this.description = definition;
        this.deadline = deadline;
        this.userId = userId;
        this.status = Status.NEW;
    }

    @Override
    public String toString() {
        return id + ",\"" + title + "\",\"" + description + "\"," + userId + ",\"" + formatter.format(deadline) + "\"," + status;
    }

}
