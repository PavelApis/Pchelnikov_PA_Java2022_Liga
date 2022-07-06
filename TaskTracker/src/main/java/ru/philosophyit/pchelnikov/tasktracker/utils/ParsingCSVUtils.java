package ru.philosophyit.pchelnikov.tasktracker.utils;

import com.opencsv.CSVReader;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ParsingCSVUtils {
    public static List<String[]> readCSV(String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"')) {
            return reader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("CSV файл не обнаружен в требуемой директории: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int readTaskIdFromCSVLine(int numberOfLine, String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("id задания в строке номер " + numberOfLine + " имеет неправильный формат.");
        }
    }

    public static int readUserIdFromCSVLine(int numberOfLine, String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("id пользователя в строке номер " + numberOfLine + " имеет неправильный формат.");
        }
    }

    public static Date readDeadlineDateFromCSVLine(int numberOfLine, String data) {
        try {
            return Task.formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException("Дата дедлайна задачи в строке номер " + numberOfLine + " имеет неправильный формат.");
        }
    }

    public static Status readStatus(int numberOfLine, String data) {
        switch (data.toLowerCase()) {
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
                throw new RuntimeException("Неверный формат статуса в строке номер " + numberOfLine +
                        ", статус может быть лишь: new, in_work, done.");
            }
        }
    }


    public static void checkTaskCSVLineSize(int number, String[] line) {
        if (line.length != 5 && line.length != 6) {
            throw new RuntimeException("Данные в строке номер " + number + " в файле задач имеют неправильный формат.");
        }
    }

    public static void checkUserCSVLineSize(int number, String[] line){
        if (line.length != 2) {
            throw new RuntimeException("Данные в строке номер " + number + " в файле задач имеют неправильный формат.");
        }
    }
}
