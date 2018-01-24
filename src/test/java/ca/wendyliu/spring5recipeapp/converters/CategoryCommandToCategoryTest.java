package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.CategoryCommand;
import ca.wendyliu.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "hello!";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        CategoryCommand command = new CategoryCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        Category category = converter.convert(command);

        assertNotNull(category);
        assertEquals(LONG_VALUE, category.getId());
        assertNotNull(DESCRIPTION, category.getDescription());
    }
}