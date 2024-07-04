package et.com.alazdev.todollo.service;

import et.com.alazdev.todollo.model.dto.requestDto.CategoryCreationDto;
import et.com.alazdev.todollo.model.dto.requestDto.TaskCreationRequestDto;
import et.com.alazdev.todollo.model.dto.responseDto.CategoryListViewDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskDetailResponseDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskListResponseDto;
import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.entity.Task;
import et.com.alazdev.todollo.model.enums.Status;
import et.com.alazdev.todollo.repository.CategoriesRepository;
import et.com.alazdev.todollo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoriesService categoriesService;
    private final CategoriesRepository categoriesRepository;

    public Task taskCreation(TaskCreationRequestDto taskCreationRequestDto, Long categoryId) {
        Categories categories = null;
        if (taskCreationRequestDto.getCategories() == null) {
            CategoryListViewDto categoryDto = categoriesService.getCategoryById(categoryId);
            categories = Categories.builder()
                    .title(categoryDto.getTitle())
                    .labelColor(categoryDto.getLabel())
                    .build();

        } else {
            // Check if the category already exists
            Categories existingCategory = categoriesRepository.findCategoriesByTitle(taskCreationRequestDto.getCategories().getTitle());
            if (existingCategory != null) {
                categories = existingCategory;
            } else {
                // Extract necessary information from Categories object and create CategoryCreationDto
                CategoryCreationDto categoryCreationDto = CategoryCreationDto.builder()
                        .title(taskCreationRequestDto.getCategories().getTitle())
                        .description(taskCreationRequestDto.getCategories().getDescription())
                        .label(taskCreationRequestDto.getCategories().getLabelColor())
                        .build();

                // Create a new category if it doesn't exist
                categories = categoriesService.createNewCategory(categoryCreationDto);
            }
        }

        Task newTask = Task.builder()
                .categories(categories)
                .status(Status.PENDING)
                .title(taskCreationRequestDto.getTitle())
                .description(taskCreationRequestDto.getDescription())
                .priorites(taskCreationRequestDto.getPriorites())
                .taskEndDate(taskCreationRequestDto.getTaskEndDate())
                .build();
        return taskRepository.save(newTask);
    }
    public List<TaskListResponseDto> getAllTask() {
        List<Task> taskList = taskRepository.findAll();
        return taskList.stream().map(task -> {
            String categoryTitle = task.getCategories() != null ? task.getCategories().getTitle() : null;

            return TaskListResponseDto.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .status(task.getStatus())
                    .categories(categoryTitle)
                    .labelColor(task.getCategories().getLabelColor())
                    .priorites(task.getPriorites())
                    .taskEndDate(task.getTaskEndDate())
                    .build();
        }).collect(Collectors.toList());
    }
    public List<Task> getAllTrial(){
        return taskRepository.findAll();
    }

    public void removeTask(Long taskId){
        Optional<Task> taskToRemove=taskRepository.findById(taskId);
        if(taskToRemove.isPresent()) {
            taskRepository.deleteById(taskId);
        }
    }
    public TaskDetailResponseDto getTaskByID(Long taskId){
        Optional<Task> task=taskRepository.findById(taskId);
        if(task.isPresent()){
            Task existingTask=task.get();
            return TaskDetailResponseDto.builder()
                    .id(existingTask.getId())
                    .status(existingTask.getStatus())
                    .taskEndDate(existingTask.getTaskEndDate())
                    .categories(existingTask.getCategories())
                    .description(existingTask.getDescription())
                    .title(existingTask.getTitle())
                    .build();
        }
        throw new RuntimeException("Task doesn't exist with this id: "+taskId);
    }
    @Transactional
    public Task updateTaskStatus(Long taskId, String statusText) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Status status = null;
            if ("Completed".equalsIgnoreCase(statusText)) {
                status = Status.COMPLETED;
            } else if ("OverDue".equalsIgnoreCase(statusText)) {
                status = Status.OVERDUE;
            } else {
                throw new IllegalArgumentException("Invalid status text: " + statusText);
            }

            task.setStatus(status);
            // Assuming you want to retain other fields and not nullify them
            taskRepository.save(task);

            return task;
        } else {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
    }
}
