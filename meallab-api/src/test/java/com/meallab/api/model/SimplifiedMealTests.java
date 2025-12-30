package com.meallab.api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimplifiedMealTests {

    /*
     * Test default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        SimplifiedMeal meal = new SimplifiedMeal();

        assertNotNull(meal, "Object should be created");
    }

    /*
     * Test parameterized constructor.
     */
    @Test
    public void testParameterizedConstructor() {
        SimplifiedMeal meal = new SimplifiedMeal("123", "Chicken Curry", "http://image.jpg");

        assertEquals("123", meal.getId());
        assertEquals("Chicken Curry", meal.getName());
        assertEquals("http://image.jpg", meal.getThumbnailUrl());
    }

    /*
     * Test equals() with same ID.
     */
    @Test
    public void testEquals_SameId() {
        SimplifiedMeal meal1 = new SimplifiedMeal("123", "Name1", "url1");
        SimplifiedMeal meal2 = new SimplifiedMeal("123", "Name2", "url2");

        assertEquals(meal1, meal2, "Meals with same ID should be equal");
    }

    /*
     * Test equals() with different IDs.
     */
    @Test
    public void testEquals_DifferentId() {
        SimplifiedMeal meal1 = new SimplifiedMeal("123", "Name", "url");
        SimplifiedMeal meal2 = new SimplifiedMeal("456", "Name", "url");

        assertNotEquals(meal1, meal2, "Meals with different IDs should not be equal");
    }

    /*
     * Test hashCode() consistency.
     */
    @Test
    public void testHashCode() {
        SimplifiedMeal meal1 = new SimplifiedMeal("123", "Name", "url");
        SimplifiedMeal meal2 = new SimplifiedMeal("123", "Name", "url");

        assertEquals(meal1.hashCode(), meal2.hashCode(),
                "Equal meals should have equal hash codes");
    }
}
