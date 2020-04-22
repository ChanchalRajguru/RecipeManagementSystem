package edu.ait.recipes.repositores;

import edu.ait.recipes.dto.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    public List<Recipe> findRecipeByName(String name);

    public List<Recipe> findRecipeByAuthor(String author);

    public List<Recipe> findRecipeByPreparationTime(String preparationTime);

}

