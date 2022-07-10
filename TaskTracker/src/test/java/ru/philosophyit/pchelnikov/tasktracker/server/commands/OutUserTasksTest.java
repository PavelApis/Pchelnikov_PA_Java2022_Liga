package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class OutUserTasksTest {
    @Autowired
    OutUserTasks outUserTasks;

    @Nested
    class IllegalArguments_Tests {
        @Test
        void wrongCommand_Test() {
            assertThatThrownBy(() -> outUserTasks.apply(new String[]{"command?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат команды out_user_tasks.");
        }

        @Test
        void nonExistingUserId_Test() {
            assertThatThrownBy(() -> outUserTasks.apply(new String[]{"out_user_tasks", "100"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Пользователя с таким user_id не существует.");
        }

        @Test
        void wrongFilterFlag_Test() {
            assertThatThrownBy(() -> outUserTasks.apply(new String[]{"out_user_tasks", "filter?", "6","new"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат флага, флаг должен иметь вид: filter");
        }

        @Test
        void wrongStatusFromat_Test() {
            assertThatThrownBy(() -> outUserTasks.apply(new String[]{"out_user_tasks", "filter", "6","status?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
        }
    }
}
