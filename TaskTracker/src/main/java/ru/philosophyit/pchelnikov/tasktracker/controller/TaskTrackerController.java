package ru.philosophyit.pchelnikov.tasktracker.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.philosophyit.pchelnikov.tasktracker.server.Executor;

@Controller
@AllArgsConstructor
@RequestMapping("/task-tracker")
public class TaskTrackerController {
    @Autowired
    private Executor executor;

    @GetMapping
    public @ResponseBody String acceptCommand(@RequestParam("arguments") String arguments) {
        return executor.execute(arguments);
    }
}
