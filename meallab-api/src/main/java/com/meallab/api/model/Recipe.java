package com.meallab.api.model;

import com.fasterxml. jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Represents a complete recipe/meal from TheMealDB API.
 *
 * This POJO (Plain Old Java Object) maps to the JSON response from TheMealDB.
 * It contains all recipe information including name, category, instructions,
 * ingredients, and media links.
 *
 * The API returns ingredients and measures in 20 separate fields
 * (strIngredient1-20, strMeasure1-20), but this class converts them into
 * a clean List<Ingredient> for easier use.
 *
 * Jackson annotations are used to map JSON field names to Java properties.
 */

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore fields we don't need from API
public class Recipe {

    @JsonProperty("idMeal")
    private String id;

    @JsonProperty("strMeal")
    private String name;

    @JsonProperty("strCategory")
    private String category;

    @JsonProperty("strArea")
    private String area;

    @JsonProperty("strInstructions")
    private String instructions;

    @JsonProperty("strMealThumb")
    private String thumbnailUrl;

    @JsonProperty("strTags")
    private String tags;

    @JsonProperty("strYoutube")
    private String youtubeUrl;

    private List<Ingredient> ingredients;

    // The API provides 20 ingredient and measure fields
    // We'll use these temporarily during deserialization, then convert to List<Ingredient>
    @JsonProperty("strIngredient1") private String ingredient1;
    @JsonProperty("strIngredient2") private String ingredient2;
    @JsonProperty("strIngredient3") private String ingredient3;
    @JsonProperty("strIngredient4") private String ingredient4;
    @JsonProperty("strIngredient5") private String ingredient5;
    @JsonProperty("strIngredient6") private String ingredient6;
    @JsonProperty("strIngredient7") private String ingredient7;
    @JsonProperty("strIngredient8") private String ingredient8;
    @JsonProperty("strIngredient9") private String ingredient9;
    @JsonProperty("strIngredient10") private String ingredient10;
    @JsonProperty("strIngredient11") private String ingredient11;
    @JsonProperty("strIngredient12") private String ingredient12;
    @JsonProperty("strIngredient13") private String ingredient13;
    @JsonProperty("strIngredient14") private String ingredient14;
    @JsonProperty("strIngredient15") private String ingredient15;
    @JsonProperty("strIngredient16") private String ingredient16;
    @JsonProperty("strIngredient17") private String ingredient17;
    @JsonProperty("strIngredient18") private String ingredient18;
    @JsonProperty("strIngredient19") private String ingredient19;
    @JsonProperty("strIngredient20") private String ingredient20;

    @JsonProperty("strMeasure1") private String measure1;
    @JsonProperty("strMeasure2") private String measure2;
    @JsonProperty("strMeasure3") private String measure3;
    @JsonProperty("strMeasure4") private String measure4;
    @JsonProperty("strMeasure5") private String measure5;
    @JsonProperty("strMeasure6") private String measure6;
    @JsonProperty("strMeasure7") private String measure7;
    @JsonProperty("strMeasure8") private String measure8;
    @JsonProperty("strMeasure9") private String measure9;
    @JsonProperty("strMeasure10") private String measure10;
    @JsonProperty("strMeasure11") private String measure11;
    @JsonProperty("strMeasure12") private String measure12;
    @JsonProperty("strMeasure13") private String measure13;
    @JsonProperty("strMeasure14") private String measure14;
    @JsonProperty("strMeasure15") private String measure15;
    @JsonProperty("strMeasure16") private String measure16;
    @JsonProperty("strMeasure17") private String measure17;
    @JsonProperty("strMeasure18") private String measure18;
    @JsonProperty("strMeasure19") private String measure19;
    @JsonProperty("strMeasure20") private String measure20;

    /*
     * Default no-argument constructor.
     * Required by Jackson for JSON deserialization.
     *
     * After Jackson creates the object and sets all fields,
     * we process the 20 ingredient/measure pairs into a clean list.
     */
    public Recipe() {
        this.ingredients = new ArrayList<>();
    }

    /**
     * Constructor for manual object creation (useful for testing).
     *
     * @param id Recipe ID
     * @param name Recipe name
     * @param category Recipe category
     * @param area Geographic area/cuisine
     * @param instructions Cooking instructions
     * @param thumbnailUrl Image URL
     */
    public Recipe(String id, String name, String category, String area,
                  String instructions, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.thumbnailUrl = thumbnailUrl;
        this.ingredients = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    /*
     * Gets the list of ingredients.
     * If not yet processed, builds the list from individual fields.
     *
     * Returns List of non-empty ingredients
     */
    public List<Ingredient> getIngredients() {
        // Lazy initialization - build list if empty
        if (ingredients. isEmpty()) {
            buildIngredientList();
        }
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // Helper Methods

    /*
     * Converts the 20 separate ingredient/measure fields into a List<Ingredient>.
     *
     * This method:
     * 1. Pairs each strIngredient with its corresponding strMeasure
     * 2. Creates Ingredient objects
     * 3. Filters out empty ingredients
     * 4. Adds to the ingredients list
     *
     * Called automatically when getIngredients() is first accessed.
     */
    private void buildIngredientList() {
        // Array of all ingredient names
        String[] ingredientNames = {
                ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
                ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
                ingredient11, ingredient12, ingredient13, ingredient14, ingredient15,
                ingredient16, ingredient17, ingredient18, ingredient19, ingredient20
        };

        // Array of all measures
        String[] measures = {
                measure1, measure2, measure3, measure4, measure5,
                measure6, measure7, measure8, measure9, measure10,
                measure11, measure12, measure13, measure14, measure15,
                measure16, measure17, measure18, measure19, measure20
        };

        // Combine pairs and add non-empty ones to list
        for (int i = 0; i < 20; i++) {
            Ingredient ingredient = new Ingredient(ingredientNames[i], measures[i]);

            // Only add if ingredient has content
            if (!ingredient.isEmpty()) {
                ingredients.add(ingredient);
            }
        }
    }

    /*
     * Gets tags as a list instead of comma-separated string.
     *
     * Returns List of individual tags, or empty list if no tags
     */
    public List<String> getTagsList() {
        List<String> tagList = new ArrayList<>();

        if (tags != null && !tags.trim().isEmpty()) {
            // Split by comma and trim whitespace
            String[] tagArray = tags.split(",");
            for (String tag : tagArray) {
                String trimmedTag = tag.trim();
                if (!trimmedTag.isEmpty()) {
                    tagList.add(trimmedTag);
                }
            }
        }

        return tagList;
    }

    /*
     * Checks if this recipe has a YouTube video.
     *
     * Returns true if YouTube URL exists and is not empty
     */
    public boolean hasYoutubeVideo() {
        return youtubeUrl != null && !youtubeUrl.trim().isEmpty();
    }

    /*
     * Checks if this recipe has tags.
     *
     * Returns true if tags exist and are not empty
     */
    public boolean hasTags() {
        return tags != null && !tags. trim().isEmpty();
    }

    // Override Methods

    /**
     * Returns a string representation of this recipe.
     * Format: "Recipe{id='52772', name='Teriyaki Chicken', category='Chicken', area='Japanese'}"
     *
     * @return A string representation for debugging
     */
    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", ingredients=" + getIngredients().size() + " items" +
                '}';
    }

    /*
     * Compares this recipe with another object for equality.
     * Two recipes are equal if they have the same ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Recipe recipe = (Recipe) obj;
        return Objects.equals(id, recipe.id);
    }

    /*
     * Generates a hash code based on the recipe ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
