package edu.ait.recipes.controller;

        import edu.ait.recipes.dto.Ingredients;
        import edu.ait.recipes.dto.Type;
        import edu.ait.recipes.exceptions.IngredientsNotFoundException;
        import edu.ait.recipes.repositores.IngredientsRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.EmptyResultDataAccessException;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

        import java.net.URI;
        import java.util.List;
        import java.util.Optional;

@RestController
public class IngredientsController {

    @Autowired
    IngredientsRepository ingredientsRepository;

    @GetMapping("ingredients")
    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @GetMapping("ingredients/{id}")
    public Optional<Ingredients> getById(@PathVariable Integer id) {
        return ingredientsRepository.findById(id);
    }

    @DeleteMapping("ingredients/{ingredientId}")
    public void deleteIngredientById(@PathVariable int ingredientId) {
        try {
            ingredientsRepository.deleteById(ingredientId);
        } catch (EmptyResultDataAccessException e) {
            throw new IngredientsNotFoundException("Unable to delete ingredient with id " + ingredientId);
        }
    }

    @PostMapping("ingredients/")
    public ResponseEntity createIngredient(@RequestBody Ingredients newIngredient) {
        Ingredients ingredients = ingredientsRepository.save(newIngredient);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(ingredients.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("ingredients/")
    public ResponseEntity updateIngredient(@RequestBody Ingredients newIngredient) {
        if (newIngredient.getId() != null) {
            ingredientsRepository.save(newIngredient);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            //create the location header
            Ingredients ingredients = ingredientsRepository.save(newIngredient);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newIngredient.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/ingredients/name/{name}")
    public List<Ingredients> getByIngredientName(@PathVariable String name) {

        return ingredientsRepository.findIngredientsByName(name);
    }

    @GetMapping("/ingredients/type/{type}")
    public List<Ingredients> getIngredientsByType(@PathVariable Type type) {

        return ingredientsRepository.findIngredientsByType(type);
    }
}
