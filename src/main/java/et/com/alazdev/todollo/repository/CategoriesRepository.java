package et.com.alazdev.todollo.repository;

import et.com.alazdev.todollo.model.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    Categories findCategoriesByTitle(String title);
}
