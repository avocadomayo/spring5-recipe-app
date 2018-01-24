package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.CategoryCommand;
import ca.wendyliu.spring5recipeapp.commands.IngredientCommand;
import ca.wendyliu.spring5recipeapp.commands.NotesCommand;
import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.Difficulty;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "yummy!";
    private static final Integer PREP_TIME = 15;
    private static final Integer COOK_TIME = 20;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "Simply Recipes";
    private static final String URL = "google.com";
    private static final String DIRECTIONS = "Easy as 123!";
    private static final Difficulty DIFFICULTY = Difficulty.MODERATE;

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory()
        );
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setDescription("avocado");
        IngredientCommand ingredient2 = new IngredientCommand();
        HashSet<IngredientCommand> ingredients = new HashSet<>(Arrays.asList(ingredient1, ingredient2));
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        command.setIngredients(ingredients);
        command.setDifficulty(DIFFICULTY);
        command.setNotes(new NotesCommand());

        CategoryCommand category1 = new CategoryCommand();
        category1.setDescription("American");
        CategoryCommand category2 = new CategoryCommand();
        category2.setDescription("Mexican");
        CategoryCommand category3 = new CategoryCommand();
        category3.setDescription("Japanese");
        HashSet<CategoryCommand> categories = new HashSet<>(Arrays.asList(category1, category2, category3));

        command.setCategories(categories);

        Recipe recipe = converter.convert(command);

        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertThat("size of ingredient set", recipe.getIngredients().size(), is(ingredients.size()));
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertThat("size of categories set", recipe.getCategories().size(), is(categories.size()));
    }
}