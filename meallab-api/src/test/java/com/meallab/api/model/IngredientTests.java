package com.meallab.api.model;

import org.junit.jupiter.api.Test;
import static org.junit. jupiter.api.Assertions.*;

/*
 * Unit tests for the Ingredient class.
 *
 * Tests verify that the Ingredient class correctly:
 * - Stores ingredient name and measure
 * - Provides getters and setters
 * - Detects empty ingredients
 * - Formats toString() properly
 * - Implements equals() and hashCode() correctly
 */

public class IngredientTests {
    @Test
    public void testConstructorWithParameters() {
        // Arrange & Act
        Ingredient ingredient = new Ingredient("garlic", "3 cloves");

        // Assert
        assertEquals("garlic", ingredient.getName(), "Name should be 'garlic'");
        assertEquals("3 cloves", ingredient. getMeasure(), "Measure should be '3 cloves'");
    }

    @Test
    public void testDefaultConstructorAndSetters() {
        // Arrange
        Ingredient ingredient = new Ingredient();

        // Act
        ingredient.setName("olive oil");
        ingredient.setMeasure("1/4 cup");

        // Assert
        assertEquals("olive oil", ingredient. getName());
        assertEquals("1/4 cup", ingredient.getMeasure());
    }

    @Test
    public void testIsEmpty_WithEmptyIngredient() {
        // Arrange
        Ingredient emptyIngredient1 = new Ingredient(null, null);
        Ingredient emptyIngredient2 = new Ingredient("", "");
        Ingredient emptyIngredient3 = new Ingredient("  ", "  ");

        // Assert
        assertTrue(emptyIngredient1.isEmpty(), "Ingredient with null values should be empty");
        assertTrue(emptyIngredient2.isEmpty(), "Ingredient with empty strings should be empty");
        assertTrue(emptyIngredient3.isEmpty(), "Ingredient with whitespace should be empty");
    }

    @Test
    public void testToString() {
        // Arrange
        Ingredient ingredient = new Ingredient("penne rigate", "1 pound");

        // Act
        String result = ingredient.toString();

        // Assert
        assertEquals("1 pound penne rigate", result, "toString should format as 'measure name'");
    }

    @Test
    public void testEquals_WithEqualIngredients() {
        // Arrange
        Ingredient ingredient1 = new Ingredient("garlic", "3 cloves");
        Ingredient ingredient2 = new Ingredient("garlic", "3 cloves");

        // Assert
        assertEquals(ingredient1, ingredient2, "Ingredients with same values should be equal");
    }

    @Test
    public void testHashCode_EqualObjectsHaveEqualHashCodes() {
        // Arrange
        Ingredient ingredient1 = new Ingredient("salt", "1 tsp");
        Ingredient ingredient2 = new Ingredient("salt", "1 tsp");

        // Assert
        assertEquals(ingredient1.hashCode(), ingredient2.hashCode(),
                "Equal ingredients should have equal hash codes");
    }
}
