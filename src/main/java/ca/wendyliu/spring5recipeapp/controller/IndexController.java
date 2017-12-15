package ca.wendyliu.spring5recipeapp.controller;

import ca.wendyliu.spring5recipeapp.domain.Category;
import ca.wendyliu.spring5recipeapp.domain.UnitOfMeasure;
import ca.wendyliu.spring5recipeapp.repository.CategoryRepository;
import ca.wendyliu.spring5recipeapp.repository.UnitOfMeasureRepository;
import ca.wendyliu.spring5recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getAllRecipes());

        // Resolves to index.html (Thymeleaf template)
        return "index";
    }
}
