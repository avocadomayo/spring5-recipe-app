package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.NotesCommand;
import ca.wendyliu.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID = 1L;
    private static final String NOTES = "notes!";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(NOTES);

        Notes notes = converter.convert(command);
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}