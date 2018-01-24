package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredientConverter;
    private final NotesCommandToNotes notesCommandToNotesConverter;
    private final CategoryCommandToCategory categoryCommandToCategoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredientConverter,
                                 NotesCommandToNotes notesCommandToNotesConverter,
                                 CategoryCommandToCategory categoryCommandToCategoryConverter) {
        this.ingredientCommandToIngredientConverter = ingredientCommandToIngredientConverter;
        this.notesCommandToNotesConverter = notesCommandToNotesConverter;
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setIngredients(
                source.getIngredients()
                .stream()
                .map(ingredientCommandToIngredientConverter::convert)
                .collect(Collectors.toSet())
        );
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesCommandToNotesConverter.convert(source.getNotes()));
        recipe.setCategories(
                source.getCategories()
                .stream()
                .map(categoryCommandToCategoryConverter::convert)
                .collect(Collectors.toSet())
        );
        return recipe;
    }
}
