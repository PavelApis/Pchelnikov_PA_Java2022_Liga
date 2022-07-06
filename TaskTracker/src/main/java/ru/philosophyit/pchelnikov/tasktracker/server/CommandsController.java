package ru.philosophyit.pchelnikov.tasktracker.server;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    Сервер отвечает на GET запросы пользователя.
        Запросы могут быть следующие:
         -- Вида /task-tracker?command=[COMMAND], где [COMMAND] - аргумент в CSV формате, может соответствовать следуюшим командам:
            -- info - возвращает информацию о доступных запросах.

            -- change_status,[TASK_ID],[new, in_work, done] - меняет статус задания с заданным id на указанный в запросе.

            -- out_user_tasks,[USER_ID] - возвращает все задания пользователя, в случае указания фильтра:
               out_user_tasks,filter,[USER_ID],[new, in_work, done] - будут возвращены задания с указанным статусом.

            -- remove_task,[TASK_ID] - удаляет задание с указаным id.

            -- save - сохраняет текущие значения users и tasks в users.csv и tasks.csv.

            -- clear - полностью очищает users, tasks, файлы users.csv и tasks.csv.
            -- add_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Создает новое задание с указанными параметрами.

            -- edit_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS] - Редактирует задание с указанным id
               по переданным параметрам.
 */

@Controller
@AllArgsConstructor
@RequestMapping("/task-tracker")
public class CommandsController {
    @Autowired
    private Executor executor;

    @GetMapping
    public @ResponseBody String acceptCommand(@RequestParam("command") String commandLine) {
        return executor.execute(commandLine);
    }
}
