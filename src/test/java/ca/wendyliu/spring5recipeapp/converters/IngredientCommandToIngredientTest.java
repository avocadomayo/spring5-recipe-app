package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.IngredientCommand;
import ca.wendyliu.spring5recipeapp.commands.UnitOfMeasureCommand;
import ca.wendyliu.spring5recipeapp.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long INGREDIENT_ID = 1L;
    private static final Long UOM_ID = 2L;
    private static final String DESCRIPTION = "hello!";
    private static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.TEN;

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        IngredientCommand command = new IngredientCommand();
        command.setId(INGREDIENT_ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(BIG_DECIMAL_VALUE);

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        command.setUom(uomCommand);

        // TODO: recipe
        Ingredient ingredient = converter.convert(command);
        assertNotNull(ingredient);
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUoM() {
        IngredientCommand command = new IngredientCommand();
        command.setId(INGREDIENT_ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(BIG_DECIMAL_VALUE);

        Ingredient ingredient = converter.convert(command);
        assertNotNull(ingredient);
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, ingredient.getAmount());
        assertNull(ingredient.getUom());
    }
}