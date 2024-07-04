package et.com.alazdev.todollo.model.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import et.com.alazdev.todollo.model.entity.Categories;
import et.com.alazdev.todollo.model.enums.Priorites;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskCreationRequestDto {
    @NonNull
    private String title;
    private String description;
    @NonNull
    private Priorites priorites;
    @NonNull
    private Categories categories;
    @NonNull
    private Date taskEndDate;
}
