package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.NotesCommand;
import ca.wendyliu.spring5recipeapp.domain.Notes;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long NOTES_ID = 1L;
    private static final Long RECIPE_ID = 2L;
    private static final String NOTES = "Cook gud";

    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        notes.setRecipe(recipe);
        notes.setRecipeNotes(NOTES);

        NotesCommand command = converter.convert(notes);

        assertNotNull(command);
        assertEquals(NOTES_ID, command.getId());
        assertEquals(NOTES, command.getRecipeNotes());
    }
}