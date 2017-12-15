package ca.wendyliu.spring5recipeapp.bootstrap;

import ca.wendyliu.spring5recipeapp.domain.*;
import ca.wendyliu.spring5recipeapp.repository.CategoryRepository;
import ca.wendyliu.spring5recipeapp.repository.RecipeRepository;
import ca.wendyliu.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public DevBootStrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initRecipeData();
    }

    private void initRecipeData() {
        // Get UoM data initialized in data.sql
        Optional<UnitOfMeasure> tspOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!tspOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> tbspOpt = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tbspOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> dashOpt = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> pieceOpt = unitOfMeasureRepository.findByDescription("Piece");
        if (!pieceOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        UnitOfMeasure tsp = tspOpt.get();
        UnitOfMeasure tbsp = tbspOpt.get();
        UnitOfMeasure dash = dashOpt.get();
        UnitOfMeasure piece = pieceOpt.get();

        // Get Category data initialized in data.sql
        Optional<Category> mexicanOpt = categoryRepository.findByDescription("Mexican");
        if (!mexicanOpt.isPresent()) {
            throw new RuntimeException("Expected Category not found.");
        }

        Optional<Category> americanOpt = categoryRepository.findByDescription("American");
        if (!americanOpt.isPresent()) {
            throw new RuntimeException("Expected Category not found.");
        }

        Category mexican = mexicanOpt.get();
        Category american = americanOpt.get();

        // Create a new recipe
        Recipe perfectGuacamole = new Recipe();

        Ingredient twoRipeAvocados = new Ingredient("Ripe avocados", new BigDecimal(2), piece, perfectGuacamole);
        Ingredient halfTspSalt = new Ingredient("Kosher salt", new BigDecimal(0.5), tsp, perfectGuacamole);
        Ingredient oneTbspLimeJuice = new Ingredient("Fresh lime juice", BigDecimal.ONE, tbsp, perfectGuacamole);
        Ingredient mincedOnion = new Ingredient("Minced red onion", new BigDecimal(2), tbsp, perfectGuacamole);
        Ingredient chiles = new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), piece, perfectGuacamole);
        Ingredient cilantro = new Ingredient("Cilantro, finely chopped", new BigDecimal(2), tbsp, perfectGuacamole);
        Ingredient blackPepper = new Ingredient("Freshly grated black pepper", new BigDecimal(1), dash, perfectGuacamole);
        Ingredient tomato = new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), piece, perfectGuacamole);

        // Add some recipe notes
        Notes guacamoleNotes = new Notes(perfectGuacamole, "Be careful handling chiles if using. Wash your " +
                "hands thoroughly after handling and do not touch your eyes or the area near your eyes with your " +
                "hands for several hours.");

        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setPrepTime(5);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("1. Cut avocado, remove flesh: Cut the avocados in half. Remove seed. " +
                "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "Place in a bowl.\n" + "2. Mash with a fork: Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in" +
                " the lime juice will provide some balance to the richness of the avocado and will help delay the " +
                "avocados from turning brown. \n" +
                "4. Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover " +
                "it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the " +
                "guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add " +
                "it just before serving.");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.getIngredients().addAll(Arrays.asList(twoRipeAvocados, halfTspSalt, oneTbspLimeJuice, mincedOnion, chiles, cilantro, blackPepper, tomato));
        perfectGuacamole.setNotes(guacamoleNotes);
        perfectGuacamole.getCategories().add(mexican);
        perfectGuacamole.getCategories().add(american);

        recipeRepository.save(perfectGuacamole);
    }
}
