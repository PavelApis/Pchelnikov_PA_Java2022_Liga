package ru.philosophyit.pchelnikov.tasktracker.server;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    Сервер отвечает на GET запросы пользователя.
                   Запросы могут быть следующие:
                   -- Вида /task-tracker?command=[COMMAND], где [COMMAND] может соответствовать следуюшим командам:
                                -- info - возвращает информацию о доступных запросах.

                                -- change-status [TASK_ID] [new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.

                                -- out-user-tasks [USER_ID] - возвращает все задания пользователя, в случае указания фильтра:
                                   out-user-tasks -filter [USER_ID] [new, in_work, done], будут возвращены задания с указанным статусом.

                                -- remove-task [TASK_ID] - удаляет задание с указаным id.

                                -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.\n" +

                                -- clear - полностью очищает users, tasks, файлы users.csv и tasks.csv.\n" +
                   -- Вида /task-tracker/add-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS] -
                                Создает новое задание с указанными параметрами.

                   -- Вида /task-tracker/edit-task?id=[TASK_ID]&title=[TITLE]&description=[DESCRIPTION]&user_id=[USER_ID]&deadline=[DEADLINE]&status=[STATUS] -
                                Редактирует задание с указанным id по переданным параметрам.
 */

@Controller
@AllArgsConstructor
@RequestMapping("/task-tracker")
public class CommandsController {
    @Autowired
    private final Commander commander;

    @GetMapping
    public @ResponseBody String acceptCommand(@RequestParam("command") String commandLine) {
        String[] command = commandLine.split(" ");
        try {
            return commander.acceptCommand(command);
        } catch (Exception e) {
            return e.getMessage() + " Команда не выполнена";
        }
    }

    @GetMapping("/add-task")
    public @ResponseBody String addTask(@RequestParam("id") String id, @RequestParam("title") String title, @RequestParam("description") String description,
                                        @RequestParam("user_id") String user_id, @RequestParam("deadline") String deadline, @RequestParam("status") String status) {
        String[] command = new String[]{"add-task", id, title, description, user_id, deadline, status};
        try {
            return commander.acceptCommand(command);
        } catch (Exception e) {
            return e.getMessage() + " Команда не выполнена";
        }
    }

    @GetMapping("/edit-task")
    public @ResponseBody String editTask(@RequestParam("id") String id, @RequestParam("title") String title, @RequestParam("description") String description,
                                         @RequestParam("user_id") String user_id, @RequestParam("deadline") String deadline, @RequestParam("status") String status) {
        String[] command = new String[]{"edit-task", id, title, description, user_id, deadline, status};
        try {
            return commander.acceptCommand(command);
        } catch (Exception e) {
            return e.getMessage() + " Команда не выполнена";
        }
    }
}
