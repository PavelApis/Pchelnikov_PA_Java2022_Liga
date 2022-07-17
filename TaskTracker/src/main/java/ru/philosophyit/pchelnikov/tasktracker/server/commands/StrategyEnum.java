package ru.philosophyit.pchelnikov.tasktracker.server.commands;

import ru.philosophyit.pchelnikov.tasktracker.services.Tasks;
import ru.philosophyit.pchelnikov.tasktracker.services.Users;

public enum StrategyEnum {
    ADD_TASK {
        private final Strategy strategy = new AddTask();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    CHANGE_STATUS {
        private final Strategy strategy = new ChangeTaskStatus();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    OUT_USER_TASKS {
        private final Strategy strategy = new OutUserTasks();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    EDIT_TASK {
        private final Strategy strategy = new EditTask();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    REMOVE_TASK {
        private final Strategy strategy = new RemoveTask();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    SAVE {
        private final Strategy strategy = new Save();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    CLEAR {
        private final Strategy strategy = new Clear();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    },
    INFO {
        private final Strategy strategy = new Info();

        @Override
        public String apply(String[] arguments, Users users, Tasks tasks) {
            return strategy.apply(arguments, users, tasks);
        }
    };

    public abstract String apply(String[] arguments, Users users, Tasks tasks);
}
