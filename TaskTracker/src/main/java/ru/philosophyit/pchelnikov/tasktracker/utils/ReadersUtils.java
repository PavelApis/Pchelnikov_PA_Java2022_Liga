package ru.philosophyit.pchelnikov.tasktracker.utils;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;

import java.text.ParseException;
import java.util.Date;

public class ReadersUtils {
    public static int readId(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат id.");
        }
    }

    public static Status readStatus(String data) {
        switch (data) {
            case "new" -> {
                return Status.NEW;
            }
            case "in_work" -> {
                return Status.IN_WORK;
            }
            case "done" -> {
                return Status.DONE;
            }
            default -> {
                throw new RuntimeException("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
            }
        }
    }

    public static Date readDeadline(String data){
        try {
            return Task.formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException("Неверный формат даты, дата должна иметь формат дд.мм.гггг.");
        }
    }
}
