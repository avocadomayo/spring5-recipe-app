package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
}
