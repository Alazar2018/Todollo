package et.com.alazdev.todollo.model.dto.responseDto;

import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.enums.Priorites;
import et.com.alazdev.todollo.model.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priorites priorites;
    private Categories categories;
    private Date createdOn;
    private Date updatedOn;
    private Date taskEndDate;
}
