package ca.wendyliu.spring5recipeapp.service;

import ca.wendyliu.spring5recipeapp.commands.RecipeCommand;
import ca.wendyliu.spring5recipeapp.converters.RecipeCommandToRecipe;
import ca.wendyliu.spring5recipeapp.converters.RecipeToRecipeCommand;
import ca.wendyliu.spring5recipeapp.domain.Recipe;
import ca.wendyliu.spring5recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Returns a list of recipes in database.
 */
// Gives us a logger
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    public Set<Recipe> getAllRecipes() {
        // enabled by @Slf4j
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException();
        }

        return recipeOptional.get();
    }

    // transactional, to avoid exception if we hit any lazily-loaded property
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        // Still a POJO, detached from Hibernate context
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        // Now saved in Hibernate
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe ID: {} ", savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }
}
