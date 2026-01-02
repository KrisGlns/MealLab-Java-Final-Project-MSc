package com.meallab.api.demo;

import com.meallab.api.service.MealDbApiClient;
import com.meallab.api.model.*;
import com.meallab.api.exception.ApiException;
import com.meallab.api.exception.MealNotFoundException;

/*
 * Demo application to test the MealLab API library.
 *
 * This class demonstrates all the main features of the library:
 * - Searching by ingredient
 * - Searching by name
 * - Getting meal details by ID
 * - Getting random meal
 *
 * Run this class to verify the library works correctly.
 */

public class MyApp {

    // Create API client instance
    private static final MealDbApiClient client = new MealDbApiClient();

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  MealLab API Library - Demo Application");
        System.out.println("===========================================\n");

        // Run all demos
        demoSearchByIngredient();
        System.out.println("\n-------------------------------------------\n");

        demoSearchByName();
        System.out.println("\n-------------------------------------------\n");

        demoGetMealById();
        System.out.println("\n-------------------------------------------\n");

        demoRandomMeal();
        System.out.println("\n-------------------------------------------\n");

        System.out.println("===========================================");
        System.out.println("  All demos completed successfully!  ");
        System.out.println("===========================================");
    }

    private static void demoSearchByIngredient() {
        System.out.println("DEMO 1: Search by Ingredient");
        System.out.println("Searching for meals with 'chicken'...\n");

        try {
            // Search for meals with chicken
            MealListResponse response = client.searchByIngredient("chicken");

            System.out.println("Found " + response.getMealCount() + " meals with chicken:");

            // Display first 5 results
            int count = Math.min(5, response.getMealCount());
            for (int i = 0; i < count; i++) {
                SimplifiedMeal meal = response.getMealAt(i);
                System.out.println("   " + (i + 1) + ". " + meal.getName() + " (ID: " + meal.getId() + ")");
            }

            if (response.getMealCount() > 5) {
                System.out.println("   ... and " + (response.getMealCount() - 5) + " more");
            }

        } catch (MealNotFoundException e) {
            System.err.println("No meals found:  " + e.getMessage());
        } catch (ApiException e) {
            System.err.println("API Error: " + e.getMessage());
        }
    }

    private static void demoSearchByName() {
        System.out.println("DEMO 2: Search by Name");
        System.out.println("Searching for 'Arrabiata'...\n");

        try {
            // Search for meals by name
            MealResponse response = client.searchByName("Arrabiata");

            System.out.println("Found " + response.getMealCount() + " meal(s):");

            // Get first result
            Recipe meal = response.getFirstMeal();
            if (meal != null) {
                System.out.println("\n   Name: " + meal.getName());
                System.out.println("   Category: " + meal.getCategory());
                System.out.println("   Area: " + meal.getArea());
                System.out.println("   Ingredients: " + meal.getIngredients().size() + " items");

                // Display ingredients
                System.out.println("\n   Ingredients:");
                for (int i = 0; i < Math.min(5, meal.getIngredients().size()); i++) {
                    Ingredient ingredient = meal.getIngredients().get(i);
                    System.out.println("      - " + ingredient);
                }

                // Display instructions preview
                String instructions = meal.getInstructions();
                if (instructions != null && instructions.length() > 100) {
                    System.out.println("\n   Instructions (preview):");
                    System.out.println("      " + instructions.substring(0, 100) + "...");
                } else if (instructions != null) {
                    System.out.println("\n   Instructions:");
                    System.out.println("      " + instructions);
                }
            }

        } catch (MealNotFoundException e) {
            System.err.println("No meals found: " + e.getMessage());
        } catch (ApiException e) {
            System.err.println("API Error: " + e.getMessage());
        }
    }

    private static void demoGetMealById() {
        System.out.println("DEMO 3: Get Meal by ID");
        System.out.println("Fetching meal with ID '52772' (Teriyaki Chicken Casserole)...\n");

        try {
            // Get meal by specific ID
            Recipe meal = client.getMealById("52772");

            System.out.println("Retrieved meal:");
            System.out.println("\n   Name: " + meal.getName());
            System.out.println("   ID: " + meal.getId());
            System.out.println("   Category: " + meal.getCategory());
            System.out.println("   Area: " + meal.getArea());
            System.out.println("   YouTube: " + (meal.hasYoutubeVideo() ? meal.getYoutubeUrl() : "No video available"));
            System.out.println("   Tags: " + (meal.hasTags() ? meal.getTags() : "No tags"));

            // Display all ingredients
            System.out.println("\n   Ingredients (" + meal.getIngredients().size() + " total):");
            for (Ingredient ingredient : meal.getIngredients()) {
                System.out.println("      - " + ingredient);
            }

        } catch (MealNotFoundException e) {
            System.err.println("Meal not found: " + e.getMessage());
        } catch (ApiException e) {
            System.err.println("API Error: " + e.getMessage());
        }
    }

    private static void demoRandomMeal() {
        System.out.println("DEMO 4: Random Meal Suggestion");
        System.out.println("Getting a random meal...\n");

        try {
            // Get random meal
            Recipe meal = client.getRandomMeal();

            System.out.println("Random meal suggestion:");
            System.out.println("\n   " + meal.getName());
            System.out.println("   Category: " + meal.getCategory());
            System.out.println("   Area: " + meal.getArea());
            System.out.println("   Ingredients: " + meal.getIngredients().size() + " items");

            // Display first 3 ingredients
            System.out.println("\n   First few ingredients:");
            for (int i = 0; i < Math.min(3, meal.getIngredients().size()); i++) {
                System.out.println("      - " + meal.getIngredients().get(i));
            }

            System.out.println("\n   Tip: Try cooking this tonight!");

        } catch (ApiException e) {
            System.err.println("API Error: " + e.getMessage());
        }
    }

}
