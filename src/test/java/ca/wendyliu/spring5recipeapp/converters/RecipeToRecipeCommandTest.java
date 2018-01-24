package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "yummy!";
    private static final Integer PREP_TIME = 15;
    private static final Integer COOK_TIME = 20;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "Simply Recipes";
    private static final String URL = "google.com";
    private static final String DIRECTIONS = "Easy as 123!";
    private static final Difficulty DIFFICULTY = Difficulty.MODERATE;

    private RecipeToRecipeCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        Set<Ingredient> ingredients = new HashSet<>(Arrays.asList(new Ingredient()));
        recipe.setIngredients(ingredients);

        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());

        Category category1 = new Category();
        category1.setDescription("Chinese");
        Category category2 = new Category();
        category2.setDescription("Malaysian");
        Set<Category> categories = new HashSet<>(Arrays.asList(category1, category2));
        recipe.setCategories(categories);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertThat("size of ingredients", ingredients.size(), is(command.getIngredients().size()));
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertThat("size of categories", categories.size(), is(command.getCategories().size()));
    }

    @Test
    public void convertWithNullIngredients() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());

        Category category1 = new Category();
        category1.setDescription("Chinese");
        Category category2 = new Category();
        category2.setDescription("Malaysian");
        Set<Category> categories = new HashSet<>(Arrays.asList(category1, category2));
        recipe.setCategories(categories);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertThat("size of ingredients", command.getIngredients().size(), is(0));
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertThat("size of categories", categories.size(), is(command.getCategories().size()));
    }

    @Test
    public void convertWithNullNotes() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        Set<Ingredient> ingredients = new HashSet<>(Arrays.asList(new Ingredient()));
        recipe.setIngredients(ingredients);

        recipe.setDifficulty(DIFFICULTY);

        Category category1 = new Category();
        category1.setDescription("Chinese");
        Category category2 = new Category();
        category2.setDescription("Malaysian");
        Set<Category> categories = new HashSet<>(Arrays.asList(category1, category2));
        recipe.setCategories(categories);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertThat("size of ingredients", ingredients.size(), is(command.getIngredients().size()));
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNull(command.getNotes());
        assertThat("size of categories", categories.size(), is(command.getCategories().size()));
    }

    @Test
    public void convertWithNullCategories() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        Set<Ingredient> ingredients = new HashSet<>(Arrays.asList(new Ingredient()));
        recipe.setIngredients(ingredients);

        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(new Notes());

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertThat("size of ingredients", ingredients.size(), is(command.getIngredients().size()));
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertThat("size of categories", command.getCategories().size(), is(0));
    }
}