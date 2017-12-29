package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.domain.Recipe;

import java.util.Set;

/**
 * Returns a list of recipes in database.
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();
}
