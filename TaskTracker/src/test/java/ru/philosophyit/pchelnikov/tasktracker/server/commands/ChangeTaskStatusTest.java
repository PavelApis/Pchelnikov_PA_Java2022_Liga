package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class ChangeTaskStatusTest {
    @Autowired
    ChangeTaskStatus changeTaskStatus;

    @Nested
    class IllegalArguments_Tests {
        @Test
        void wrongCommand_Test() {
            assertThatThrownBy(() -> changeTaskStatus.apply(new String[]{"command?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат запроса изменения статуса, команда должна иметь формат:" +
                            " /task_tracker?command=change_status,[TASK_ID],[STATUS]");
        }
        @Test
        void nonExistingTask_Test(){
            assertThatThrownBy(() -> changeTaskStatus.apply(new String[]{"change_status","100","new"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Задания с таким task_id не существует.");
        }
        @Test
        void wrongTaskStatus_Test(){
            assertThatThrownBy(() -> changeTaskStatus.apply(new String[]{"change_status","5","status?"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
        }
    }
}
