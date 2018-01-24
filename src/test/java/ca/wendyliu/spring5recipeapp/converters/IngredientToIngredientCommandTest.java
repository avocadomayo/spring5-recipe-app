package ca.wendyliu.spring5recipeapp.converters;

import ca.wendyliu.spring5recipeapp.commands.IngredientCommand;
import ca.wendyliu.spring5recipeapp.domain.Ingredient;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import ca.wendyliu.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {

    private static final Long INGREDIENT_ID = 1L;
    private static final Long UOM_ID = 2L;
    private static final String DESCRIPTION = "hello!";
    private static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.TEN;
    private static final Recipe RECIPE = new Recipe();

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(BIG_DECIMAL_VALUE);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertEquals(INGREDIENT_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
        // TODO: What to do about RECIPE?
    }

    @Test
    public void convertWithNullUoM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(BIG_DECIMAL_VALUE);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertEquals(INGREDIENT_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, command.getAmount());
        assertNull(command.getUom());
    }
}