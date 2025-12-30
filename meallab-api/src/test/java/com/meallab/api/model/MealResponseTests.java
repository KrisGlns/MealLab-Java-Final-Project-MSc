package com.meallab.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

public class MealResponseTests {
    private MealResponse response;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setUp() {
        // Create test recipes
        recipe1 = new Recipe("123", "Recipe 1", "Category1", "Area1", "Instructions1", "url1");
        recipe2 = new Recipe("456", "Recipe 2", "Category2", "Area2", "Instructions2", "url2");
    }

    /*
     * Test default constructor initializes empty list.
     */
    @Test
    public void testDefaultConstructor() {
        response = new MealResponse();

        assertNotNull(response.getMeals(), "Meals list should not be null");
        assertTrue(response.getMeals().isEmpty(), "Meals list should be empty");
        assertFalse(response.hasMeals(), "Should not have meals");
        assertEquals(0, response.getMealCount(), "Meal count should be 0");
    }

    /*
     * Test constructor with meals list.
     */
    @Test
    public void testConstructorWithMeals() {
        response = new MealResponse(Arrays.asList(recipe1, recipe2));

        assertTrue(response.hasMeals(), "Should have meals");
        assertEquals(2, response.getMealCount(), "Should have 2 meals");
        assertEquals(recipe1, response.getMeals().get(0));
        assertEquals(recipe2, response.getMeals().get(1));
    }

    /*
     * Test constructor with null list (defensive).
     */
    @Test
    public void testConstructorWithNull() {
        response = new MealResponse(null);

        assertNotNull(response.getMeals(), "Should return empty list, not null");
        assertFalse(response.hasMeals(), "Should not have meals");
    }

    /*
     * Test getFirstMeal() with single meal.
     */
    @Test
    public void testGetFirstMeal_WithSingleMeal() {
        response = new MealResponse(Collections.singletonList(recipe1));

        Recipe first = response.getFirstMeal();
        assertNotNull(first, "First meal should not be null");
        assertEquals(recipe1, first, "Should return the first recipe");
    }

    /*
     * Test getFirstMeal() with multiple meals.
     */
    @Test
    public void testGetFirstMeal_WithMultipleMeals() {
        response = new MealResponse(Arrays.asList(recipe1, recipe2));

        Recipe first = response. getFirstMeal();
        assertEquals(recipe1, first, "Should return the first recipe");
    }

    /*
     * Test getMealCount() with various scenarios.
     */
    @Test
    public void testGetMealCount() {
        // Empty
        response = new MealResponse();
        assertEquals(0, response. getMealCount());

        // One meal
        response = new MealResponse(Collections.singletonList(recipe1));
        assertEquals(1, response.getMealCount());

        // Multiple meals
        response = new MealResponse(Arrays.asList(recipe1, recipe2));
        assertEquals(2, response. getMealCount());

        // Null meals
        response. setMeals(null);
        assertEquals(0, response.getMealCount());
    }

    /*
     * Test toString() method.
     */
    @Test
    public void testToString() {
        response = new MealResponse(Arrays.asList(recipe1, recipe2));
        String result = response.toString();

        assertTrue(result.contains("2"), "Should contain meal count");
        assertTrue(result.contains("true"), "Should indicate hasMeals=true");
    }
}
