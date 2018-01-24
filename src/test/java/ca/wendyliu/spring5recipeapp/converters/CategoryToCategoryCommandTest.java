package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.CategoryCommand;
import ca.wendyliu.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "hello!";

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}