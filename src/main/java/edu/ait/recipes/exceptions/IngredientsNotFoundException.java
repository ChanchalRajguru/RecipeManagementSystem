package edu.ait.recipes.exceptions;

public class IngredientsNotFoundException extends RuntimeException {
    public IngredientsNotFoundException(String message) {
        super(message);
    }
}
