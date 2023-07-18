package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Task;
import java.util.List;

public interface ITaskService {
    List<Task> getTasksByStageId(Long stageId);
    Task createTask(Long stageId, Task task);
    Task updateTask(Long taskId, Task taskRequest);
    void deleteTask(Long taskId);
    Task getTaskById(Long taskId);
}
