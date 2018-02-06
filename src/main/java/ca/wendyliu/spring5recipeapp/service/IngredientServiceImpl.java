package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.commands.IngredientCommand;
import ca.wendyliu.spring5recipeapp.converters.IngredientToIngredientCommand;
import ca.wendyliu.spring5recipeapp.domain.Ingredient;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import ca.wendyliu.spring5recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand converter;
    private final RecipeRepository recipeRepository;

    @Autowired
    public IngredientServiceImpl(IngredientToIngredientCommand converter, RecipeRepository recipeRepository) {
        this.converter = converter;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found. id: " + recipeId);

            // TODO: error handling
        }

        Optional<IngredientCommand> ingredientOptional =
                recipeOptional.get().getIngredients().stream()
                        .filter(i -> id.equals(i.getId()))
                        .map(converter::convert)
                        .findFirst();

        if (!ingredientOptional.isPresent()) {
            log.error("ingredient is not found for id: " + id);
        }

        return ingredientOptional.get();
    }
}
