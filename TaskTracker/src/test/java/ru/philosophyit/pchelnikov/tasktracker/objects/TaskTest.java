package ru.philosophyit.pchelnikov.tasktracker.objects;


import org.junit.jupiter.api.Test;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    @Test
    void taskToString_Test() {
        Task task = new Task(
                1, "Выполнить ДЗ", "Придумать и написать кучу кода",
                2, ReadersUtils.readDeadline("27.06.3022"), Status.NEW
        );
        assertThat(task).hasToString("1,\"Выполнить ДЗ\",\"Придумать и написать кучу кода\",2,\"27.06.3022\",NEW");
    }
}
