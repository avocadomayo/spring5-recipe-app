package ca.wendyliu.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        System.out.println("Some message to say... 1234");

        // Resolves to index.html (Thymeleaf template)
        return "index";
    }
}
