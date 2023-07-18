package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Task;
import tn.esprit.healthcloud.services.ITaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {
    private final ITaskService taskService;

    @PostMapping("/stage/{stageId}/task")
    public Task createTask(@PathVariable Long stageId, @RequestBody Task task) {
        return taskService.createTask(stageId, task);
    }

    @GetMapping("/task/{taskId}")
    public Task getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/stage/{stageId}/tasks")
    public List<Task> getTasksByStageId(@PathVariable Long stageId) {
        return taskService.getTasksByStageId(stageId);
    }

    @PutMapping("/task/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task taskRequest) {
        return taskService.updateTask(taskId, taskRequest);
    }

    @DeleteMapping("/task/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
