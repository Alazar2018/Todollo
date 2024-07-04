package et.com.alazdev.todollo.model.dto.requestDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryCreationDto {
    @NonNull
        private String title;
        private String description;
        private String label;
}
