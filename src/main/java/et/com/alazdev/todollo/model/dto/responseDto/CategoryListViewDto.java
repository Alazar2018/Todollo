package et.com.alazdev.todollo.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryListViewDto {
    private Long id;
    private String title;
    private String label;
}
