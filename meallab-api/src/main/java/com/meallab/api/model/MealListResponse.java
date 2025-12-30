package com.meallab.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/*
 * Wrapper class for API responses that return simplified meal listings.
 *
 * The TheMealDB API returns simplified meal data when searching by ingredient:
 * {
 *   "meals": [
 *     {
 *       "idMeal": "52772",
 *       "strMeal":  "Teriyaki Chicken",
 *       "strMealThumb": "https://..."
 *     }
 *   ]
 * }
 *
 * This response contains only basic info (no ingredients, no instructions).
 * To get full details, you must call the lookup-by-ID endpoint with the meal ID.
 *
 * This class is used for:
 * - Filter by ingredient (returns multiple simplified meals)
 */

public class MealListResponse {

    @JsonProperty("meals")
    private List<SimplifiedMeal> meals;

    /*
     * Default constructor.
     * Required by Jackson for deserialization.
     */
    public MealListResponse() {
        this.meals = new ArrayList<>();
    }

    public MealListResponse(List<SimplifiedMeal> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
    }

    public List<SimplifiedMeal> getMeals() {
        return meals != null ? meals : new ArrayList<>();
    }

    public void setMeals(List<SimplifiedMeal> meals) {
        this.meals = meals;
    }

    // Utility Methods

    public boolean hasMeals() {
        return meals != null && !meals.isEmpty();
    }

    public int getMealCount() {
        return hasMeals() ? meals.size() : 0;
    }

    /*
     * Gets a meal by its position in the list.
     */
    public SimplifiedMeal getMealAt(int index) {
        if (hasMeals() && index >= 0 && index < meals.size()) {
            return meals.get(index);
        }
        return null;
    }

    // Override Methods

    @Override
    public String toString() {
        return "MealListResponse{" +
                "mealCount=" + getMealCount() +
                ", hasMeals=" + hasMeals() +
                '}';
    }
}
