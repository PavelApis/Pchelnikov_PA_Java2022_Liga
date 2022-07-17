package ru.philosophyit.pchelnikov.tasktracker.utils;

import ru.philosophyit.pchelnikov.tasktracker.server.commands.StrategyEnum;

public class StringConstants {
    public static final String REQUEST_MAPPING = "/task-tracker?arguments";

    public static final String WRONG_REQUEST = "Неопознанный запрос. Выполните запрос " + REQUEST_MAPPING + "=" + StrategyEnum.INFO +
            ", чтобы узнать какие запросы доступны.";

    public static final String REQUEST_FAILURE = "Запрос не выполнен.";
    public static final String INFO = " <pre> Сервер отвечает на GET запросы пользователя. \n" +
            "                   Запросы могут быть следующие:  \n" +
            "                   -- Вида " + REQUEST_MAPPING + "=[STRATEGY], где [STRATEGY] - аргумент в CSV формате, может означать выполнение следующих операций:\n" +
            "                                -- " + StrategyEnum.INFO + " - возвращает информацию о доступных запросах.\n" +
            "                       \n" +
            "                                -- " + StrategyEnum.CHANGE_STATUS + ",[TASK_ID],[new, in_work, done] - меняет статус задания с заданным id на указанный в запросе. \n" +
            "                                \n" +
            "                                -- " + StrategyEnum.OUT_USER_TASKS + ",[USER_ID] - возвращает все задания пользователя, в случае указания фильтра:\n" +
            "                                   " + StrategyEnum.OUT_USER_TASKS + ",[USER_ID],[new, in_work, done] - будут возвращены задания с указанным статусом.\n" +
            "                \n" +
            "                                -- " + StrategyEnum.REMOVE_TASK + ",[TASK_ID] - удаляет задание с указаным id.\n" +
            "                \n" +
            "                                -- " + StrategyEnum.SAVE + " - сохраняет текущие значения users и tasks в users.csv и tasks.csv.\\n\" +\n" +
            "            \n" +
            "                                -- " + StrategyEnum.CLEAR + " - полностью очищает users, tasks, файлы users.csv и tasks.csv.\\n\" +\n" +
            "                                -- " + StrategyEnum.ADD_TASK + ",[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Создает новое задание с указанными параметрами.\n" +
            "                                \n" +
            "                                -- " + StrategyEnum.EDIT_TASK + ",[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Редактирует задание с указанным id \n" +
            "                                   по переданным параметрам. <\\pre";
}
