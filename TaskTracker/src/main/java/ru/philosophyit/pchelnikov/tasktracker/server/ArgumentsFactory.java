package ru.philosophyit.pchelnikov.tasktracker.server;

import com.opencsv.CSVReader;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@UtilityClass
public class ArgumentsFactory {
    public static String[] makeCommandArguments(String commandLine){
        try (CSVReader reader = new CSVReader(new StringReader(commandLine), ',', '"')) {
            List<String[]> readedLines = reader.readAll();
            if (readedLines.size() != 1){
                throw new RuntimeException("Неверный формат arguments.");
            }
            return readedLines.get(0);
        } catch (IOException e) {
            throw new RuntimeException("IOException во время чтения аргумента command.");
        }
    }
}
