import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ParsingCSVUtils {
    public static List<String[]> readCSV(Path pathToCSV) {
        try {
            CSVReader reader = new CSVReader(new FileReader(pathToCSV.toString()), ',', '"');
            return reader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("CSV файл не обнаружен в требуемой директории: " + pathToCSV);
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

    public static int readUserIdFormCSVLine(int numberOfLine, String data) {
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


    public static void checkCSVLineSize(int number, String[] line, int size) {
        if (line.length != size) {
            throw new RuntimeException("Данные в строке номер " + number + " в файле задач имеют неправильный формат.");
        }
    }
}
