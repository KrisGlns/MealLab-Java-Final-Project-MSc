package com.meallab.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MealListResponseTests {
    private MealListResponse response;
    private SimplifiedMeal meal1;
    private SimplifiedMeal meal2;

    @BeforeEach
    public void setUp() {
        meal1 = new SimplifiedMeal("123", "Meal 1", "url1");
        meal2 = new SimplifiedMeal("456", "Meal 2", "url2");
    }

    /*
     * Test constructor with meals list.
     */
    @Test
    public void testConstructorWithMeals() {
        response = new MealListResponse(Arrays.asList(meal1, meal2));

        assertTrue(response. hasMeals(), "Should have meals");
        assertEquals(2, response.getMealCount(), "Should have 2 meals");
    }

    /*
     * Test constructor with null (defensive).
     */
    @Test
    public void testConstructorWithNull() {
        response = new MealListResponse(null);

        assertNotNull(response.getMeals(), "Should return empty list, not null");
        assertFalse(response. hasMeals(), "Should not have meals");
    }

    /*
     * Test getMealAt() with valid index.
     */
    @Test
    public void testGetMealAt_ValidIndex() {
        response = new MealListResponse(Arrays.asList(meal1, meal2));

        assertEquals(meal1, response.getMealAt(0));
        assertEquals(meal2, response.getMealAt(1));
    }

    /*
     * Test getMealAt() with invalid index.
     */
    @Test
    public void testGetMealAt_InvalidIndex() {
        response = new MealListResponse(Collections.singletonList(meal1));

        assertNull(response.getMealAt(-1), "Negative index should return null");
        assertNull(response.getMealAt(10), "Out of bounds index should return null");
    }
}
