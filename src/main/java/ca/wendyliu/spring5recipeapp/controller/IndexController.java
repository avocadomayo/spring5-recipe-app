package ca.wendyliu.spring5recipeapp.controller;

import ca.wendyliu.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("I'm in the controller!");

        model.addAttribute("recipes", recipeService.getAllRecipes());

        // Resolves to index.html (Thymeleaf template)
        return "index";
    }
}
