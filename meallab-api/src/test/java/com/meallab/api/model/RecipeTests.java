package com.meallab.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RecipeTests {

    private Recipe recipe;

    /*
     * Set up a test recipe before each test.
     * This runs before EVERY Test method.
     */
    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
        recipe.setId("52772");
        recipe.setName("Teriyaki Chicken Casserole");
        recipe.setCategory("Chicken");
        recipe.setArea("Japanese");
        recipe.setInstructions("Preheat oven to 350° F...");
        recipe.setThumbnailUrl("https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg");
        recipe.setTags("Meat,Casserole");
        recipe.setYoutubeUrl("https://www.youtube.com/watch?v=4aZr5hZXP_s");
    }

    /*
     * Test basic getters and setters.
     */
    @Test
    public void testBasicFields() {
        assertEquals("52772", recipe.getId());
        assertEquals("Teriyaki Chicken Casserole", recipe.getName());
        assertEquals("Chicken", recipe.getCategory());
        assertEquals("Japanese", recipe.getArea());
        assertEquals("Preheat oven to 350° F...", recipe.getInstructions());
        assertEquals("https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                recipe.getThumbnailUrl());
    }

    /*
     * Test the parameterized constructor.
     */
    @Test
    public void testConstructorWithParameters() {
        Recipe newRecipe = new Recipe("123", "Test Recipe", "Dessert", "Italian",
                "Mix ingredients...", "http://image.jpg");

        assertEquals("123", newRecipe.getId());
        assertEquals("Test Recipe", newRecipe.getName());
        assertEquals("Dessert", newRecipe.getCategory());
        assertEquals("Italian", newRecipe.getArea());
    }

    /*
     * Test manual addition of ingredients.
     */
    @Test
    public void testAddingIngredients() {
        recipe.getIngredients().add(new Ingredient("soy sauce", "3/4 cup"));
        recipe.getIngredients().add(new Ingredient("water", "1/2 cup"));

        assertEquals(2, recipe.getIngredients().size());
        assertEquals("soy sauce", recipe.getIngredients().get(0).getName());
        assertEquals("3/4 cup", recipe.getIngredients().get(0).getMeasure());
    }

    /*
     * Test getTagsList() method.
     * Should split comma-separated tags into a list.
     */
    @Test
    public void testGetTagsList() {
        List<String> tags = recipe.getTagsList();

        assertEquals(2, tags.size(), "Should have 2 tags");
        assertEquals("Meat", tags.get(0));
        assertEquals("Casserole", tags.get(1));
    }

    /*
     * Test getTagsList() with empty tags.
     */
    @Test
    public void testGetTagsList_WithEmptyTags() {
        recipe.setTags("");
        List<String> tags = recipe. getTagsList();

        assertTrue(tags.isEmpty(), "Tags list should be empty when tags is empty string");
    }

    /*
     * Test hasYoutubeVideo() method.
     */
    @Test
    public void testHasYoutubeVideo_WithVideo() {
        assertTrue(recipe. hasYoutubeVideo(), "Should have YouTube video");
    }

    /*
     * Test toString() method.
     */
    @Test
    public void testToString() {
        String result = recipe.toString();

        assertTrue(result.contains("52772"), "toString should contain ID");
        assertTrue(result.contains("Teriyaki Chicken Casserole"), "toString should contain name");
        assertTrue(result.contains("Chicken"), "toString should contain category");
    }

    /*
     * Test equals() method - recipes with same ID should be equal.
     */
    @Test
    public void testEquals_SameId() {
        Recipe recipe1 = new Recipe();
        recipe1.setId("123");
        recipe1.setName("Recipe A");

        Recipe recipe2 = new Recipe();
        recipe2.setId("123");
        recipe2.setName("Recipe B");  // Different name, same ID

        assertEquals(recipe1, recipe2, "Recipes with same ID should be equal");
    }

    /**
     * Test hashCode() - equal recipes should have equal hash codes.
     */
    @Test
    public void testHashCode_EqualRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId("123");

        Recipe recipe2 = new Recipe();
        recipe2.setId("123");

        assertEquals(recipe1.hashCode(), recipe2.hashCode(),
                "Equal recipes should have equal hash codes");
    }
}
