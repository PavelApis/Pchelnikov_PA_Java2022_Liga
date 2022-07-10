package ru.philosophyit.pchelnikov.tasktracker.objects;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.philosophyit.pchelnikov.tasktracker.utils.ReadersUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskBuilderTest {
    @Test
    void taskBuilder_CorrectArguments_Test() {
        TaskBuilder taskBuilder = new TaskBuilder();
        Task buildedTask = taskBuilder.id("1")
                .title("Выполнить ДЗ")
                .description("Придумать и написать кучу кода")
                .userId("2")
                .deadline("27.06.3022")
                .status("new").build();
        assertThat(buildedTask.getId()).isEqualTo(1);
        assertThat(buildedTask.getTitle()).isEqualTo("Выполнить ДЗ");
        assertThat(buildedTask.getDescription()).isEqualTo("Придумать и написать кучу кода");
        assertThat(buildedTask.getUserId()).isEqualTo(2);
        assertThat(buildedTask.getDeadline()).isEqualTo(ReadersUtils.readDeadline("27.06.3022"));
        assertThat(buildedTask.getStatus()).isEqualTo(Status.NEW);
    }

    @Nested
    class IllegalArguments_Tests {
        @Test
        void taskBuilder_IllegalId_Test() {
            assertThatThrownBy(() -> new TaskBuilder().id("id?")).isInstanceOf(NumberFormatException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void taskBuilder_duplictatedId_Test() {
            assertThatThrownBy(() -> new TaskBuilder().id("1").id("2")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан id.");
        }

        @Test
        void taskBuilder_IllegaUserlId_Test() {
            assertThatThrownBy(() -> new TaskBuilder().userId("id?")).isInstanceOf(NumberFormatException.class)
                    .hasMessage("Неверный формат id.");
        }

        @Test
        void taskBuilder_duplictatedUserId_Test() {
            assertThatThrownBy(() -> new TaskBuilder().userId("1").userId("2")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан userId.");
        }

        @Test
        void taskBuilder_duplicatedTitle_Test() {
            assertThatThrownBy(() -> new TaskBuilder().title("test 1").title("test 2")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан title.");
        }

        @Test
        void taskBuilder_duplicatedDescription_Test() {
            assertThatThrownBy(() -> new TaskBuilder().description("test 1").description("test 2")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан description.");
        }

        @Test
        void taskBuilder_IllegalDeadline_Test() {
            assertThatThrownBy(() -> new TaskBuilder().deadline("deadline?")).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат даты, дата должна иметь формат дд.мм.гггг.");
        }

        @Test
        void taskBuilder_duplictatedDedline_Test() {
            assertThatThrownBy(() -> new TaskBuilder().deadline("27.06.3022").deadline("17.11.2023")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан deadline.");
        }

        @Test
        void taskBuilder_IllegalStatus_Test(){
            assertThatThrownBy(() -> new TaskBuilder().status("status?")).isInstanceOf(RuntimeException.class)
                    .hasMessage("Неверный формат статуса, статус может быть лишь: new, in_work, done.");
        }

        @Test
        void taskBuilder_duplictatedStatus_Test() {
            assertThatThrownBy(() -> new TaskBuilder().status("new").status("done")).isInstanceOf(IllegalStateException.class)
                    .hasMessage("В TaskBuilder уже задан status.");
        }
    }

}
