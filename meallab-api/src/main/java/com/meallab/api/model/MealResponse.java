package com.meallab.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/*
 * Wrapper class for API responses that returns full meal/recipe details.
 *
 * The TheMealDB API returns recipes wrapped in a "meals" array:
 * {
 *   "meals": [
 *     { recipe data }
 *   ]
 * }
 *
 * This class is used for:
 * - Lookup meal by ID (returns 1 meal)
 * - Random meal (returns 1 meal)
 * - Search by name (returns multiple meals with full details)
 *
 * Jackson deserializes the JSON into this wrapper, which contains
 * a list of Recipe objects.
 */

public class MealResponse {

    @JsonProperty("meals")
    private List<Recipe> meals;

    public MealResponse() {
        this.meals = new ArrayList<>();
    }

    /*
     * Constructor with meals list.
     * Useful for testing and manual object creation.
     */
    public MealResponse(List<Recipe> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
    }

    public List<Recipe> getMeals() {
        return meals != null ? meals : new ArrayList<>();
    }

    public void setMeals(List<Recipe> meals) {
        this.meals = meals;
    }

    // Utility Methods

    /*
     * Checks if the response contains any meals.
     *
     * The API returns {"meals": null} when no results are found,
     * so we need to check for both null and empty list.
     */
    public boolean hasMeals() {
        return meals != null && ! meals.isEmpty();
    }

    /*
     * Gets the first meal from the response.
     *
     * Useful for API calls that return a single meal:
     * - Lookup by ID
     * - Random meal
     */
    public Recipe getFirstMeal() {
        return hasMeals() ? meals.get(0) : null;
    }

    public int getMealCount() {
        return hasMeals() ? meals.size() : 0;
    }

    // Override Methods

    /*
     * Returns a string representation of this response.
     */
    @Override
    public String toString() {
        return "MealResponse{" +
                "mealCount=" + getMealCount() +
                ", hasMeals=" + hasMeals() +
                '}';
    }
}
