package ru.philosophyit.pchelnikov.tasktracker.objects;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;


class UserTest {
    @Test
    void userToString_Test(){
        User user = new User(1,"Иван");
        assertThat(user).hasToString("1,Иван");
    }
}
