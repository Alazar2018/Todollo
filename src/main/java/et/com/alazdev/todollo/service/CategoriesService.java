package et.com.alazdev.todollo.service;

import et.com.alazdev.todollo.model.dto.requestDto.CategoryCreationDto;
import et.com.alazdev.todollo.model.dto.responseDto.CategoryListViewDto;
import et.com.alazdev.todollo.model.dto.responseDto.TaskListResponseDto;
import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.entity.Task;
import et.com.alazdev.todollo.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public Categories createNewCategory(CategoryCreationDto categoryCreationDto) {
        String categoryTitle = categoryCreationDto.getTitle();
        if (categoriesRepository.findCategoriesByTitle(categoryTitle)!=null) {
            throw new IllegalArgumentException("Category with title '" + categoryTitle + "' already exists");
        }

        Categories newCategory = Categories.builder()
                .title(categoryTitle)
                .description(categoryCreationDto.getDescription())
                .labelColor(categoryCreationDto.getLabel())
                .build();
        return categoriesRepository.save(newCategory);
    }

    public List<Categories> createNewListCategory(List<CategoryCreationDto> categoryCreationDtoList) {
        List<Categories> newCategories = categoryCreationDtoList.stream()
                .map(this::createNewCategory)
                .collect(Collectors.toList());
        return categoriesRepository.saveAll(newCategories);
    }

    public List<CategoryListViewDto> getAllCategories() {
        return categoriesRepository.findAll().stream()
                .map(categories -> CategoryListViewDto.builder()
                        .id(categories.getId())
                        .title(categories.getTitle())
                        .label(categories.getLabelColor())
                        .build())
                .collect(Collectors.toList());
    }

    public CategoryListViewDto getCategoryById(Long id) {
        Categories categories = getCategoriesById(id);
        return CategoryListViewDto.builder()
                .title(categories.getTitle())
                .label(categories.getLabelColor())
                .build();
    }

    public Categories getCategoriesById(Long id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " not found"));
    }
}
