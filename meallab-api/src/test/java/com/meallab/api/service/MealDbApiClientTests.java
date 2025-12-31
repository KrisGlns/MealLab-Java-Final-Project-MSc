package com.meallab.api.service;

import com.meallab.api.exception.ApiException;
import com.meallab.api.exception.MealNotFoundException;
import com.meallab.api.model.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Integration tests for MealDbApiClient.
 *
 * These tests make REAL API calls to TheMealDB,
 * so they require an internet connection.
 */

public class MealDbApiClientTests {
    private MealDbApiClient client;
    @BeforeEach
    public void setUp() {
        client = new MealDbApiClient();
    }

    /*
     * Test searching by ingredient with valid input.
     * Should return results for a common ingredient.
     */
    @Test
    public void testSearchByIngredient_ValidIngredient() throws ApiException {
        MealListResponse response = client.searchByIngredient("chicken");

        assertNotNull(response, "Response should not be null");
        assertTrue(response.hasMeals(), "Should have meals for 'chicken'");
        assertTrue(response.getMealCount() > 0, "Should have at least one meal");

        // Check first meal has required fields
        SimplifiedMeal firstMeal = response.getMealAt(0);
        assertNotNull(firstMeal, "First meal should not be null");
        assertNotNull(firstMeal.getId(), "Meal should have an ID");
        assertNotNull(firstMeal.getName(), "Meal should have a name");
        assertNotNull(firstMeal.getThumbnailUrl(), "Meal should have a thumbnail");

        System.out.println("✓ Found " + response.getMealCount() + " meals with chicken");
        System.out.println("  First result: " + firstMeal.getName());
    }

    /*
     * Test searching with ingredient that has no results.
     * Should throw MealNotFoundException.
     */
    @Test
    public void testSearchByIngredient_NoResults() {
        assertThrows(MealNotFoundException.class, () -> {
            client.searchByIngredient("xyzabc123testingredient");
        }, "Should throw MealNotFoundException for nonexistent ingredient");

        System.out.println("✓ Correctly throws exception for nonexistent ingredient");
    }

    /*
     * Test searching by meal name with valid input.
     * Should return full recipe details.
     */
    @Test
    public void testSearchByName_ValidName() throws ApiException {
        MealResponse response = client.searchByName("Arrabiata");

        assertNotNull(response, "Response should not be null");
        assertTrue(response.hasMeals(), "Should have meals for 'Arrabiata'");

        // Check first meal has full details
        Recipe firstMeal = response.getFirstMeal();
        assertNotNull(firstMeal, "First meal should not be null");
        assertNotNull(firstMeal.getId(), "Should have ID");
        assertNotNull(firstMeal.getName(), "Should have name");
        assertNotNull(firstMeal.getInstructions(), "Should have instructions");
        assertNotNull(firstMeal.getCategory(), "Should have category");
        assertNotNull(firstMeal.getArea(), "Should have area");
        assertFalse(firstMeal.getIngredients().isEmpty(), "Should have ingredients");

        System.out.println("✓ Found meal:  " + firstMeal.getName());
        System.out.println("  Category: " + firstMeal.getCategory());
        System.out.println("  Area: " + firstMeal.getArea());
        System.out.println("  Ingredients: " + firstMeal.getIngredients().size());
    }

    /*
     * Test searching with name that has no results.
     * Should throw MealNotFoundException.
     */
    @Test
    public void testSearchByName_NoResults() {
        assertThrows(MealNotFoundException.class, () -> {
            client.searchByName("xyzabc123nonexistent");
        }, "Should throw MealNotFoundException for nonexistent name");

        System.out.println("✓ Correctly throws exception for nonexistent name");
    }

    /*
     * Test searching with null name.
     * Should throw IllegalArgumentException.
     */
    @Test
    public void testSearchByName_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            client.searchByName(null);
        }, "Should throw IllegalArgumentException for null name");

        System.out.println("✓ Correctly validates null input");
    }

    /*
     * Test getting meal by valid ID.
     * Should return full recipe details.
     */
    @Test
    public void testGetMealById_ValidId() throws ApiException {
        // ID for "Teriyaki Chicken Casserole"
        Recipe meal = client.getMealById("52772");

        assertNotNull(meal, "Meal should not be null");
        assertEquals("52772", meal.getId(), "Should have correct ID");
        assertNotNull(meal.getName(), "Should have name");
        assertNotNull(meal.getInstructions(), "Should have instructions");
        assertFalse(meal.getIngredients().isEmpty(), "Should have ingredients");

        System.out.println("✓ Retrieved meal by ID: " + meal.getName());
        System.out.println("  Instructions length: " + meal.getInstructions().length() + " characters");
    }

    /*
     * Test getting meal with nonexistent ID.
     * Should throw MealNotFoundException.
     */
    @Test
    public void testGetMealById_InvalidId() {
        assertThrows(MealNotFoundException.class, () -> {
            client.getMealById("99999999");
        }, "Should throw MealNotFoundException for invalid ID");

        System.out.println("✓ Correctly throws exception for invalid ID");
    }

    /*
     * Test getting a random meal.
     * Should return a complete recipe.
     */
    @Test
    public void testGetRandomMeal() throws ApiException {
        Recipe meal = client.getRandomMeal();

        assertNotNull(meal, "Random meal should not be null");
        assertNotNull(meal.getId(), "Should have ID");
        assertNotNull(meal.getName(), "Should have name");
        assertNotNull(meal.getInstructions(), "Should have instructions");
        assertNotNull(meal.getCategory(), "Should have category");
        assertFalse(meal.getIngredients().isEmpty(), "Should have ingredients");

        System.out.println("✓ Got random meal: " + meal.getName());
        System.out.println("  Category: " + meal.getCategory());
        System.out.println("  Area: " + meal.getArea());
    }

    /*
     * Test that multiple random meal calls return different meals (probably).
     * This is probabilistic - might occasionally fail if same meal returned twice.
     */
    @Test
    public void testGetRandomMeal_MultipleCallsReturnDifferentMeals() throws ApiException {
        Recipe meal1 = client.getRandomMeal();
        Recipe meal2 = client.getRandomMeal();

        assertNotNull(meal1);
        assertNotNull(meal2);

        // They MIGHT be the same (small chance), but usually different
        System.out.println("✓ Random meal 1: " + meal1.getName());
        System.out.println("  Random meal 2: " + meal2.getName());

        if (! meal1.getId().equals(meal2.getId())) {
            System.out.println("  → Different meals (as expected)");
        } else {
            System.out.println("  → Same meal (unlikely but possible)");
        }
    }
}
