package edu.ait.recipes.repositores;

import edu.ait.recipes.dto.Ingredients;
import edu.ait.recipes.dto.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
    public List<Ingredients> findIngredientsByName(String name);

    public List<Ingredients> findIngredientsByType(Type type);

}
