package ca.wendyliu.spring5recipeapp.repository;

import ca.wendyliu.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
