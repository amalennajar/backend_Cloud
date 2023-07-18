package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Stage;
import tn.esprit.healthcloud.entities.Task;
import tn.esprit.healthcloud.repositories.StageRepository;
import tn.esprit.healthcloud.repositories.TaskRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor

public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;
    private final StageRepository stageRepository;


    public List<Task> getTasksByStageId(Long stageId) {
        return taskRepository.findByStageId(stageId);
    }

    public Task getTaskById(Long taskId){
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task createTask(Long stageId, Task task) {
        Stage stage = stageRepository.findById(stageId).orElseThrow(() -> new EntityNotFoundException("Stage not found with id: " + stageId));
        task.setStage(stage);
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.getCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        taskRepository.delete(task);
    }

}
