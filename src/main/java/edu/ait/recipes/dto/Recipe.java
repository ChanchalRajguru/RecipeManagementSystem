package edu.ait.recipes.dto;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    private String description;

    private String author;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "date_created", columnDefinition = "DATE")
    private LocalDate dateCreated;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredients> ingredients;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    public Recipe(String name, String description, String author, String preparationTime, LocalDate dateCreated, List<Ingredients> ingredients) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.preparationTime = preparationTime;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", preparationTime='" + preparationTime + '\'' +
                ", dateCreated=" + dateCreated +
                ", ingredients=" + ingredients +
                '}';
    }
}
