package et.com.alazdev.todollo.model.dto.responseDto;

import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.enums.Priorites;
import et.com.alazdev.todollo.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskListResponseDto {
    private Long id;
    private String title;
    private Priorites priorites;
    private Status status;
    private String categories;
    private String labelColor;
    private Date taskEndDate;
}
