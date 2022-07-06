package ru.philosophyit.pchelnikov.tasktracker.server.commands;

public class InfoCommand extends Command {
    @Override
    public String apply(String[] strings) {
        return " <pre> Сервер отвечает на GET запросы пользователя.\n" +
                "                   Запросы могут быть следующие:  \n" +
                "                   -- Вида /task-tracker?command=[COMMAND], где [COMMAND] может соответствовать следуюшим командам:\n" +
                "                                -- info - возвращает информацию о доступных запросах.\n" +
                "                       \n" +
                "                                -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе. \n" +
                "                                \n" +
                "                                -- out-user-tasks [USER_ID] - возвращает все задания пользователя, в случае указания фильтра:\n" +
                "                                   out-user-tasks -filter [USER_ID] [new, in_work, done], будут возвращены задания с указанным статусом.\n" +
                "                \n" +
                "                                -- remove-task [TASK_ID] - удаляет задание с указаным id.\n" +
                "                \n" +
                "                                -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.\\n\" +\n" +
                "            \n" +
                "                                -- clear - полностью очищает users, tasks, файлы users.csv и tasks.csv.\\n\" +\n" +
                "                   -- Вида /task-tracker/add-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS] -\n" +
                "                                Создает новое задание с указанными параметрами.\n" +
                "                                \n" +
                "                   -- Вида /task-tracker/edit-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS] -\n" +
                "                                Редактирует задание с указанным id по переданным параметрам. <\\pre";
    }
}
