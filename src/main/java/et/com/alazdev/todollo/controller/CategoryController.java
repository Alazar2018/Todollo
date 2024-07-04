package et.com.alazdev.todollo.controller;

import et.com.alazdev.todollo.model.dto.requestDto.CategoryCreationDto;
import et.com.alazdev.todollo.model.dto.requestDto.TaskCreationRequestDto;
import et.com.alazdev.todollo.model.dto.responseDto.CategoryListViewDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskListResponseDto;
import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.entity.Task;
import et.com.alazdev.todollo.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoriesService categoriesService;

    @PostMapping("/createCategory")
    public Categories createNewTask(@RequestBody CategoryCreationDto categoryCreationDto) {
        return categoriesService.createNewCategory(categoryCreationDto);
    }

    @GetMapping("/getAll")
    public List<CategoryListViewDto> GetAllListTask(){
        return categoriesService.getAllCategories();
    }
}
