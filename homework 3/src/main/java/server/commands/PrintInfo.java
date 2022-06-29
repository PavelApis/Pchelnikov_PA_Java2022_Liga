package server.commands;

import java.util.function.Consumer;

public class PrintInfo implements Consumer<String[]> {
    public void printInfo() {
        accept(new String[]{"info"});
    }

    @Override
    public void accept(String[] command) {
        System.out.println(" Сервер отвечает на запросы пользователя в консоли.\n" +
                "    Запросы могут быть следующие:\n" +
                "        -- info - выводит информацию о доступных запросах.\n" +
                "\n" +
                "        -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.\n" +
                "\n" +
                "        -- sout-user-tasks [USER_ID] - выводит все задания пользователя, в случае указания фильтра:\n" +
                "           sout-user-tasks -filter [USER_ID] [new, in_work, done], будут выведены задания с указанным статусом.\n" +
                "\n" +
                "        -- add-task - открывает интерфейс построения новой команды, последовательно будет запрошен ввод \n" +
                "                        полей [TASK_ID], [TITLE], [DESCRIPTION], [USER_ID], [DEADLINE], [STATUS].\n" +
                "                        \n" +
                "        -- edit-task [TASK_ID] - открывает интерфейст редактирования команды, последовательно будет запрошен ввод\n" +
                "                        полей  [TITLE], [DESCRIPTION], [USER_ID], [DEADLINE], [STATUS].\n" +
                "                        \n" +
                "        -- remove-task [TASK_ID] - удаляет задание с указаным id.\n" +
                "        \n" +
                "        -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.\n" +
                "        \n" +
                "        -- cleat - полностью очищает users, tasks, файлы users.csv и tasks.csv.\n" +
                "        \n" +
                "        -- close - заканчивает работу сервера.");
    }
}
