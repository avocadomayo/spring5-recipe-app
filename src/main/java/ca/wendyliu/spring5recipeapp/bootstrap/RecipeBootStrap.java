package ca.wendyliu.spring5recipeapp.bootstrap;

import ca.wendyliu.spring5recipeapp.domain.*;
import ca.wendyliu.spring5recipeapp.repository.CategoryRepository;
import ca.wendyliu.spring5recipeapp.repository.RecipeRepository;
import ca.wendyliu.spring5recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
@Profile("default")
@Slf4j
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootStrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("onApplicationEvent triggered");
        recipeRepository.saveAll(initRecipeData());
    }

    private List<Recipe> initRecipeData() {
        List<Recipe> recipes = new ArrayList<>(2);

        // Get UoM data initialized in data-h2.sql
        Optional<UnitOfMeasure> tspOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!tspOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> tbspOpt = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tbspOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> cupOpt = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> pinchOpt = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> ounceOpt = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> dashOpt = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> cloveOpt = unitOfMeasureRepository.findByDescription("Clove");
        if (!cloveOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> pintOpt = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        Optional<UnitOfMeasure> pieceOpt = unitOfMeasureRepository.findByDescription("Piece");
        if (!pieceOpt.isPresent()) {
            throw new RuntimeException("Expected UoM not found.");
        }

        UnitOfMeasure tsp = tspOpt.get();
        UnitOfMeasure tbsp = tbspOpt.get();
        UnitOfMeasure cup = cupOpt.get();
        UnitOfMeasure pinch = pinchOpt.get();
        UnitOfMeasure ounce = ounceOpt.get();
        UnitOfMeasure dash = dashOpt.get();
        UnitOfMeasure clove = cloveOpt.get();
        UnitOfMeasure pint = pintOpt.get();
        UnitOfMeasure piece = pieceOpt.get();

        // Get Category data initialized in data-h2.sql
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

        Ingredient twoRipeAvocados = new Ingredient("Ripe avocados", new BigDecimal(2), piece);
        Ingredient halfTspSalt = new Ingredient("Kosher salt", new BigDecimal(0.5), tsp);
        Ingredient oneTbspLimeJuice = new Ingredient("Fresh lime juice", BigDecimal.ONE, tbsp);
        Ingredient mincedOnion = new Ingredient("Minced red onion", new BigDecimal(2), tbsp);
        Ingredient chiles = new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), piece);
        Ingredient cilantro = new Ingredient("Cilantro, finely chopped", new BigDecimal(2), tbsp);
        Ingredient blackPepper = new Ingredient("Freshly grated black pepper", new BigDecimal(1), dash);
        Ingredient tomato = new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), piece);

        // Add some recipe notes
        Notes guacamoleNotes = new Notes(perfectGuacamole, "Be careful handling chiles if using. Wash your " +
                "hands thoroughly after handling and do not touch your eyes or the area near your eyes with your " +
                "hands for several hours.");

        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
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
        perfectGuacamole.addIngredient(twoRipeAvocados)
                .addIngredient(halfTspSalt)
                .addIngredient(oneTbspLimeJuice)
                .addIngredient(mincedOnion)
                .addIngredient(chiles)
                .addIngredient(cilantro)
                .addIngredient(blackPepper)
                .addIngredient(tomato);

        perfectGuacamole.setNotes(guacamoleNotes);
        perfectGuacamole.getCategories().add(mexican);
        perfectGuacamole.getCategories().add(american);

        recipes.add(perfectGuacamole);

        // Tacos recipe
        Recipe chickenTacos = new Recipe();

        chickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacos.setPrepTime(20);
        chickenTacos.setCookTime(15);
        chickenTacos.setServings(4);
        chickenTacos.setSource("Simply Recipes");
        chickenTacos.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose " +
                "paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted " +
                "into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high " +
                "heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs " +
                "and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of " +
                "arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle " +
                "with the thinned sour cream. Serve with lime wedges.");
        chickenTacos.setDifficulty(Difficulty.MODERATE);

        Ingredient chiliPowder = new Ingredient("ancho chili powder", new BigDecimal(2), tbsp);
        Ingredient driedOregano = new Ingredient("dried oregano", BigDecimal.ONE, tsp);
        Ingredient driedCumin = new Ingredient("dried cumin", BigDecimal.ONE, tsp);
        Ingredient sugar = new Ingredient("sugar", BigDecimal.ONE, tsp);
        Ingredient salt = new Ingredient("salt", new BigDecimal(0.5), tsp);
        Ingredient garlic = new Ingredient("garlic, finely chopped", new BigDecimal(1), clove);
        Ingredient orangeZest = new Ingredient("finely grated orange zest", BigDecimal.ONE, tbsp);
        Ingredient orangeJuice = new Ingredient("freshly-squeezed orange juice", new BigDecimal(3), tbsp);
        Ingredient oliveOil = new Ingredient("olive oil", new BigDecimal(2), tbsp);
        Ingredient chickenThighs = new Ingredient("skinless, boneless chicken thighs", new BigDecimal(4), piece);
        Ingredient tortillas = new Ingredient("small corn tortillas", new BigDecimal(8), piece);
        Ingredient arugula = new Ingredient("packed baby arugula", new BigDecimal(3), cup);
        Ingredient avocados = new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), piece);
        Ingredient radishes = new Ingredient("radishes, thinly sliced", new BigDecimal(4), piece);
        Ingredient cherryTomatoes = new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pint);
        Ingredient redOnion = new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), piece);
        Ingredient tacoCilantro = new Ingredient("roughly chopped cilantro", BigDecimal.ONE, piece);
        Ingredient sourCream = new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cup);
        Ingredient lime = new Ingredient("lime, cut into wedges", BigDecimal.ONE, piece);

        chickenTacos.addIngredient(chiliPowder)
                .addIngredient(driedOregano)
                .addIngredient(driedCumin)
                .addIngredient(sugar)
                .addIngredient(salt)
                .addIngredient(garlic)
                .addIngredient(orangeZest)
                .addIngredient(orangeJuice)
                .addIngredient(oliveOil)
                .addIngredient(chickenThighs)
                .addIngredient(tortillas)
                .addIngredient(arugula)
                .addIngredient(avocados)
                .addIngredient(radishes)
                .addIngredient(cherryTomatoes)
                .addIngredient(redOnion)
                .addIngredient(tacoCilantro)
                .addIngredient(sourCream)
                .addIngredient(lime);

        Notes chickenNote = new Notes(chickenTacos, "Look for ancho chile powder with the Mexican " +
                "ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you " +
                "replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, " +
                "though the flavor won't be quite the same.)");
        chickenTacos.setNotes(chickenNote);
        chickenTacos.getCategories().addAll(Arrays.asList(mexican, american));

        recipes.add(chickenTacos);

        return recipes;
    }
}
