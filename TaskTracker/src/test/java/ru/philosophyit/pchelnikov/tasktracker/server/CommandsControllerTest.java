package ru.philosophyit.pchelnikov.tasktracker.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.philosophyit.pchelnikov.tasktracker.objects.Status;
import ru.philosophyit.pchelnikov.tasktracker.objects.Task;
import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommandsControllerTest {
    @Autowired
    CommandsController controller;
    @Autowired
    Tasks tasks;
    @Autowired
    Users users;

    @Test
    void addTaskCommand_Test() {
        assertThat(controller.acceptCommand("add_task,7,\"test title\",\"test description\",2,10.07.2022,new"))
                .isEqualTo("Задание: {7,\"test title\",\"test description\",2,\"10.07.2022\",NEW} успешно добавлено.");
        assertThat(tasks.getTaskMap().containsKey(7)).isTrue();
        Task addedTask = tasks.get(7);
        assertThat(addedTask.getTitle()).isEqualTo("test title");
        assertThat(addedTask.getDescription()).isEqualTo("test description");
        assertThat(addedTask.getDeadline()).isEqualTo(ReadersUtils.readDeadline("10.07.2022"));
        assertThat(addedTask.getStatus()).isEqualTo(Status.NEW);

        assertThat(users.getUserMap().get(2).getTasks().contains(addedTask)).isTrue();
    }

    @Test
    void removeTaskCommand_Test() {
        controller.acceptCommand("add_task,7,\"test title\",\"test description\",2,10.07.2022,new");

        Task removedTask = tasks.get(7);
        assertThat(controller.acceptCommand("remove_task,7"))
                .isEqualTo("Задание: {7,\"test title\",\"test description\",2,\"10.07.2022\",NEW} успешно удалено.");

        assertThat(tasks.getTaskMap().containsKey(7)).isFalse();
        assertThat(users.getUserMap().get(2).getTasks().contains(removedTask)).isFalse();
    }

    @Test
    void changeTaskStatusTest() {
        Task changedTask = tasks.get(6);
        assertThat(changedTask.getStatus()).isEqualTo(Status.NEW);
        assertThat(controller.acceptCommand("change_status,6,done"))
                .isEqualTo("Статус задания с id: 6 успешно изменен на DONE.");
        assertThat(changedTask.getStatus()).isEqualTo(Status.DONE);
    }

    @Test
    void editTaskCommand_Test() {
        assertThat(controller.acceptCommand("edit_task,6,\"test title\",\"test description\",2,10.07.2022,new"))
                .isEqualTo("Задание: " +
                        "{6,\"Посмотреть погоду\",\"Узнай погоду на выходные, чтобы спланировать дни\",3,\"07.07.2022\",NEW}" +
                        " успешно изменено на: {6,\"test title\",\"test description\",2,\"10.07.2022\",NEW}.");
        assertThat(tasks.getTaskMap().containsKey(6)).isTrue();
        Task editedTask = tasks.get(6);
        assertThat(editedTask.getTitle()).isEqualTo("test title");
        assertThat(editedTask.getDescription()).isEqualTo("test description");
        assertThat(editedTask.getDeadline()).isEqualTo(ReadersUtils.readDeadline("10.07.2022"));
        assertThat(editedTask.getStatus()).isEqualTo(Status.NEW);
    }

    @Test
    void infoCommand_Test() {
        assertThat(controller.acceptCommand("info")).isEqualTo(" <pre> Сервер отвечает на GET запросы пользователя.\n" +
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
                "                                   по переданным параметрам. <\\pre");
    }

    @Test
    public void outUserTasks_Test() {
        assertThat(controller.acceptCommand("out_user_tasks,2")).isEqualTo(
                "<pre>1,\"Выполнить ДЗ\",\"Придумать и написать кучу кода\",2,\"27.06.3022\",NEW\n" +
                        "5,\"Сериализация\",\"Посмотри Доктора Хауса, Клинику и Во все тяжкие\",2,\"25.06.2022\",NEW\n" +
                        "4,\"Сходить в Магазин\",\"Купи картошки, селедки, молока и огурцов\",2,\"28.06.2022\",NEW</pre>"
        );
    }

    @Test
    void outFilteredUserTasks_Test() {
        assertThat(controller.acceptCommand("out_user_tasks,filter,2,done")).isEqualTo(
                "<pre></pre>"
        );
    }
}
