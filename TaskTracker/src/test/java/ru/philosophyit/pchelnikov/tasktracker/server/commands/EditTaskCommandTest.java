package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class EditTaskCommandTest {
    @Autowired
    EditTask editTask;

    @Nested
    class IllegalArguments_Tests {
        @Test
        void wrongCommand_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"command?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат запроса редактирования задачи, ожидаеммый формат: " +
                            "/task-tracker?command=edit_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }

        @Test
        void wrongCommandArgumentsFormat_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task",",",",",",",",",",",",",","}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат запроса редактирования задачи, ожидаеммый формат: " +
                            "/task-tracker?command=edit_task,[TASK_ID],[TITLE],[DESCRIPTION],[USER_ID],[DEADLINE],[STATUS].");
        }

        @Test
        void wrongIdArgumentFormat_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","id?","test title","test description","2","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void wrongUserIdArgumentFormat_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","5","test title","test description","id?","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void wrongDeadlineArgumentFormat_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","5","test title","test description","10","deadline?","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат даты, дата должна иметь формат дд.мм.гггг.");
        }

        @Test
        void wrongStatusArgumentFormat_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","5","test title","test description","10","10.07.2022","status?"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
        }

        @Test
        void nonExistingTaskEditing_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","10","test title","test description","10","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class).hasMessage("Задания с таким task_id не существует.");
        }

        @Test
        void nonExistingUserIdEditing_Test() {
            assertThatThrownBy(() -> editTask.apply(new String[]{"edit_task","5","test title","test description","100","10.07.2022","new"}))
                    .isInstanceOf(RuntimeException.class).hasMessage("Пользователя с userId не существует.");
        }
    }
}
