package ca.wendyliu.spring5recipeapp.repository;

import ca.wendyliu.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    /** Spring JDBC will auto-generate this method! */
    Optional<Category> findByDescription(String description);
}
