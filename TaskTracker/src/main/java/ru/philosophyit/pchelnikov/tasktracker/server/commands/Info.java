package ru.philosophyit.pchelnikov.tasktracker.server.commands;

public class Info extends Strategy {
    @Override
    public String apply(String[] strings) {
        return " <pre> Сервер отвечает на GET запросы пользователя.\n" +
                "                   Запросы могут быть следующие:  \n" +
                "                   -- Вида /task-tracker?command=[COMMAND], где [COMMAND] - аргумент в CSV формате, может соответствовать следуюшим командам:\n" +
                "                                -- info - возвращает информацию о доступных запросах.\n" +
                "                       \n" +
                "                                -- change_status,[TASK_ID],[new, in_work, done] - меняет статус задания с заданным id на указанный в запросе. \n" +
                "                                \n" +
                "                                -- out_user_tasks,[USER_ID] - возвращает все задания пользователя, в случае указания фильтра:\n" +
                "                                   out_user_tasks,filter,[USER_ID],[new, in_work, done] - будут возвращены задания с указанным статусом.\n" +
                "                \n" +
                "                                -- remove_task,[TASK_ID] - удаляет задание с указаным id.\n" +
                "                \n" +
                "                                -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.\\n\" +\n" +
                "            \n" +
                "                                -- clear - полностью очищает users, tasks, файлы users.csv и tasks.csv.\\n\" +\n" +
                "                                -- add_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Создает новое задание с указанными параметрами.\n" +
                "                                \n" +
                "                                -- edit_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Редактирует задание с указанным id \n" +
                "                                   по переданным параметрам. <\\pre";
    }
}
