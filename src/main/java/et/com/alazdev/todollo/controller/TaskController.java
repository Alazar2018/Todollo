package et.com.alazdev.todollo.controller;

import et.com.alazdev.todollo.model.dto.requestDto.TaskCreationRequestDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskDetailResponseDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskListResponseDto;
import et.com.alazdev.todollo.model.entity.Task;
import et.com.alazdev.todollo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @PostMapping("/createNewTask/{categoryId}")
    public Task createNewTask(@RequestBody  TaskCreationRequestDto taskCreationRequestDto, @PathVariable(required = false) Long categoryId) {
        if(categoryId != null){
            return taskService.taskCreation(taskCreationRequestDto, categoryId);

        }
        throw new RuntimeException("eroorrr");
    }

    @GetMapping("/getAll")
    public List<TaskListResponseDto> GetAllListTask(){
        return taskService.getAllTask();
    }
    @GetMapping("/{taskID}")
    public TaskDetailResponseDto GetAllListTask(@PathVariable Long taskID){
        return taskService.getTaskByID(taskID);
    }
    @PutMapping("/{taskId}/updateStatus/{statusText}")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long taskId,
            @PathVariable String statusText) {

        try {
            taskService.updateTaskStatus(taskId, statusText);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/remove/{taskId}")
    public void removeTask(@PathVariable Long taskId){
        taskService.removeTask(taskId);
    }
}
