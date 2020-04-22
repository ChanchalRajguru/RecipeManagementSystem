package edu.ait.recipes.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    private Double quantity;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(name = "recipes")
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipeList;


    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    //Empty Constructor
    public Ingredients() {
    }

    public Ingredients(String name, Type type, Double quantity, Unit unit, List<Recipe> recipeList) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
        this.recipeList = recipeList;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", recipeList=" + recipeList +
                '}';
    }
}