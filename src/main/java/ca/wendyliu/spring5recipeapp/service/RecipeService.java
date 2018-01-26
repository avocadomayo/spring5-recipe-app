package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.Recipe;

import java.util.Set;

/**
 * Returns a list of recipes in database.
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);
}
