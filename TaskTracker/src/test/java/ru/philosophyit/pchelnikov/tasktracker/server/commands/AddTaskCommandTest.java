package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class AddTaskCommandTest {
    @Autowired
    AddTask addTask;

    @Nested
    class IllegalArguments_Tests {
        @Test
        void wrongCommand_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"command?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат запроса добавления задачи, ожидаеммый формат: " +
                            "/task-tracker?command=add_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }

        @Test
        void wrongCommandArgumentsFromat_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task",",",",",",",",",",",",",","}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат запроса добавления задачи, ожидаеммый формат: " +
                            "/task-tracker?command=add_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }

        @Test
        void wrongIdArgumentFormat_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","id?","test title","test description","2","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void wrongUserIdArgumentFormat_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","10","test title","test description","id?","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void wrongDeadlineArgumentFormat_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","10","test title","test description","10","deadline?","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат даты, дата должна иметь формат дд.мм.гггг.");
        }

        @Test
        void wrongStatusArgumentFormat_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","10","test title","test description","10","10.07.2022","status?"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
        }

        @Test
        void existingTaskAdding_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","5","test title","test description","10","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class).hasMessage("В комманде указан повторяющийся id задания.");
        }

        @Test
        void nonExistingUserIdAdding_Test() {
            assertThatThrownBy(() -> addTask.apply(new String[]{"add_task","10","test title","test description","100","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class).hasMessage("Пользователя с userId не существует.");
        }
    }
}
