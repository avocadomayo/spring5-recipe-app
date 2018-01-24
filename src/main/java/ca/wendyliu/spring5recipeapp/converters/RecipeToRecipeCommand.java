package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommandConverter;
    private final NotesToNotesCommand notesToNotesCommandConverter;
    private final CategoryToCategoryCommand categoryToCategoryCommandConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommandConverter,
                                 NotesToNotesCommand notesToNotesCommandConverter,
                                 CategoryToCategoryCommand categoryToCategoryCommandConverter) {
        this.ingredientToIngredientCommandConverter = ingredientToIngredientCommandConverter;
        this.notesToNotesCommandConverter = notesToNotesCommandConverter;
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setDirections(source.getDirections());
        command.setIngredients(
                source.getIngredients()
                .stream()
                .map(i -> ingredientToIngredientCommandConverter.convert(i))
                .collect(Collectors.toSet()));
        command.setDifficulty(source.getDifficulty());
        command.setNotes(notesToNotesCommandConverter.convert(source.getNotes()));
        command.setCategories(
                source.getCategories()
                .stream()
                .map(c -> categoryToCategoryCommandConverter.convert(c))
                .collect(Collectors.toSet())
        );
        return command;
    }
}
