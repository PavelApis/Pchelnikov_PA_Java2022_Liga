package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class RemoveTaskCommandTest {
    @Autowired
    private RemoveTask removeTask;
    @Nested
    class IllegalArguments_Tests {
        @Test
        void wrongCommand_Test() {
            assertThatThrownBy(() -> removeTask.apply(new String[]{"command?"})).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат команды удаления задания, ожидаемый формат: remove_task,[TASK_ID]");
        }

        @Test
        void wrongIdArgumentFormat_Test() {
            assertThatThrownBy(() -> removeTask.apply(new String[]{"edit_task","id?"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void nonExistingTaskRemoving_Test() {
            assertThatThrownBy(() -> removeTask.apply(new String[]{"remove_task","10"}))
                    .isInstanceOf(RuntimeException.class).hasMessage("Задания с таким task_id не существует.");
        }
    }
}
