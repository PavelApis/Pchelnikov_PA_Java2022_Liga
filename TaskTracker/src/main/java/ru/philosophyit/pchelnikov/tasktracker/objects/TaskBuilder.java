package ru.philosophyit.pchelnikov.tasktracker.objects;

import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

import java.util.Date;

public class TaskBuilder {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
    private Date deadline;
    private Status status;

    TaskBuilder(){

    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public TaskBuilder id(String idString){
        if(this.id != null){
            throw new IllegalStateException("В TaskBuilder уже задан id");
        }
        int id = ReadersUtils.readId(idString);
        this.id = id;
        return this;
    }

    public TaskBuilder title(String title){
        if(this.title != null){
            throw new IllegalStateException("В TaskBuilder уже задан title");
        }
        this.title = title;
        return this;
    }

    public TaskBuilder description(String description){
        if(this.description != null){
            throw new IllegalStateException("В TaskBuilder уже задан description");
        }
        this.description = description;
        return this;
    }

    public TaskBuilder userId(String userIdString){
        if(this.userId != null){
            throw new IllegalStateException("В TaskBuilder уже задан userId");
        }
        int userId = ReadersUtils.readId(userIdString);
        this.userId = userId;
        return this;
    }

    public TaskBuilder deadline(String deadlineString){
        this.deadline = ReadersUtils.readDeadline(deadlineString);
        return this;
    }

    public TaskBuilder status(String statusString){
        this.status = ReadersUtils.readStatus(statusString);
        return this;
    }

    public Task build(){
        return new Task(id, title, description, userId, deadline, status);
    }
}
