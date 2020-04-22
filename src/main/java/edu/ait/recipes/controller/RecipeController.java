package edu.ait.recipes.controller;

import edu.ait.recipes.dto.Recipe;
import edu.ait.recipes.exceptions.RecipeNotFoundException;
import edu.ait.recipes.repositores.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("recipes")
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    @GetMapping("recipes/{id}")
    public Optional<Recipe> getById(@PathVariable Integer id){
        return recipeRepository.findById(id);
    }

    @DeleteMapping("recipes/{recipeId}")
    public void deleteRecipeById(@PathVariable int recipeId){
        try{
            recipeRepository.deleteById(recipeId);
        }catch (EmptyResultDataAccessException e){
            throw new RecipeNotFoundException("Unable to delete recipe with id " + recipeId);
        }
    }

    @PostMapping("recipes/")
    public ResponseEntity createRecipe(@RequestBody Recipe newRecipe){
        Recipe recipe = recipeRepository.save(newRecipe);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(recipe.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("recipes/")
    public ResponseEntity updateRecipe(@RequestBody Recipe newRecipe){
        if(newRecipe.getId() != null){
            recipeRepository.save(newRecipe);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            //create the location header
            Recipe recipe = recipeRepository.save(newRecipe);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newRecipe.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    //Derived Queries
    @GetMapping("/recipes/name/{name}")
    public List<Recipe> getRecipeByName(@PathVariable String name) {

        return recipeRepository.findRecipeByName(name);
    }

    @GetMapping("/recipes/author/{author}")
    public List<Recipe> getRecipeByAuthor(@PathVariable String author) {

        return recipeRepository.findRecipeByAuthor(author);
    }

    @GetMapping("/recipes/preparationTime/{preparationTime}")
    public List<Recipe> getRecipeByPreparationTime(@PathVariable String preparationTime) {

        return recipeRepository.findRecipeByPreparationTime(preparationTime);
    }
}