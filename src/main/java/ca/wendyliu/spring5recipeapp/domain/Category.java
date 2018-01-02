package ca.wendyliu.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
// This allows us to avoid circular dependencies caused by bi-directional relationships in Recipe
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
