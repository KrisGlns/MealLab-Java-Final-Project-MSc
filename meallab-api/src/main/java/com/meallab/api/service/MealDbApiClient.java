package com.meallab.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meallab.api.exception.ApiException;
import com.meallab.api.exception.MealNotFoundException;
import com.meallab.api.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/*
 * Client for interacting with TheMealDB API.
 *
 * This class provides methods to:
 * - Search for meals by ingredient
 * - Search for meals by name
 * - Get detailed meal information by ID
 * - Get a random meal suggestion
 *
 * It uses Java's built-in HttpClient for HTTP requests
 * and Jackson for JSON deserialization.
 *
 * Usage example:
 * <pre>
 * MealDbApiClient client = new MealDbApiClient();
 * try {
 *     List&lt;SimplifiedMeal&gt; results = client.searchByIngredient("chicken");
 *     for (SimplifiedMeal meal : results) {
 *         System.out.println(meal.getName());
 *     }
 * } catch (ApiException e) {
 *     System.err.println("API error: " + e.getMessage());
 * }
 * </pre>
 */

public class MealDbApiClient {
    /*
     * Base URL for TheMealDB API (version 1)
     */
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1";

    /*
     * Endpoint for filtering meals by ingredient
     */
    private static final String FILTER_BY_INGREDIENT_URL = BASE_URL + "/filter.php?i=";

    /*
     * Endpoint for searching meals by name
     */
    private static final String SEARCH_BY_NAME_URL = BASE_URL + "/search.php?s=";

    /*
     * Endpoint for looking up a meal by ID
     */
    private static final String LOOKUP_BY_ID_URL = BASE_URL + "/lookup.php?i=";

    /*
     * Endpoint for getting a random meal
     */
    private static final String RANDOM_MEAL_URL = BASE_URL + "/random.php";

    /*
     * Request timeout in seconds
     */
    private static final int TIMEOUT_SECONDS = 10;

    /*
     * HTTP client for making requests.
     * Reusable across multiple requests for better performance.
     */
    private final HttpClient httpClient;

    /*
     * Jackson ObjectMapper for JSON deserialization.
     * Converts JSON strings to Java objects.
     */
    private final ObjectMapper objectMapper;

    public MealDbApiClient() {
        // Build HTTP client with configuration
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)  // Use HTTP/2 for better performance
                .connectTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))  // Connection timeout
                .followRedirects(HttpClient.Redirect.NORMAL)  // Follow redirects automatically
                .build();

        // Create Jackson ObjectMapper for JSON processing
        this.objectMapper = new ObjectMapper();
    }

    /*
     * Constructor for dependency injection (useful for testing with mock HttpClient).
     */
    public MealDbApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    // API Methods

    /*
     * Searches for meals by ingredient.
     *
     * Returns a simplified list of meals that contain the specified ingredient.
     * To get full details (instructions, ingredients), call getMealById()
     * with the meal's ID.
     *
     * Example:
     * <pre>
     * ListSimplifiedMeal results = client.searchByIngredient("chicken");
     * </pre>
     */
    public MealListResponse searchByIngredient(String ingredient) throws ApiException {
        // Validate input
        if (ingredient == null || ingredient.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient cannot be null or empty");
        }

        try {
            // URL-encode the ingredient to handle special characters
            String encodedIngredient = URLEncoder.encode(ingredient.trim(), StandardCharsets.UTF_8);
            String url = FILTER_BY_INGREDIENT_URL + encodedIngredient;

            // Make HTTP request and get response
            String jsonResponse = makeHttpRequest(url);

            // Deserialize JSON to MealListResponse
            MealListResponse response = objectMapper.readValue(jsonResponse, MealListResponse.class);

            // Check if results were found
            if (!response.hasMeals()) {
                throw new MealNotFoundException("No meals found for ingredient: " + ingredient);
            }

            return response;

        } catch (IOException e) {
            throw new ApiException("Failed to parse API response for ingredient: " + ingredient, e);
        }
    }

    /*
     * Searches for meals by name.
     *
     * Returns full meal details (including instructions and ingredients).
     * The search is flexible - it will match partial names.
     *
     * Example:
     * <pre>
     * MealResponse results = client.searchByName("Arrabiata");
     * </pre>
     */
    public MealResponse searchByName(String name) throws ApiException {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        try {
            // URL-encode the name
            String encodedName = URLEncoder.encode(name.trim(), StandardCharsets.UTF_8);
            String url = SEARCH_BY_NAME_URL + encodedName;

            // Make HTTP request
            String jsonResponse = makeHttpRequest(url);

            // Deserialize JSON to MealResponse
            MealResponse response = objectMapper.readValue(jsonResponse, MealResponse.class);

            // Check if results were found
            if (!response.hasMeals()) {
                throw new MealNotFoundException("No meals found with name: " + name);
            }

            return response;

        } catch (IOException e) {
            throw new ApiException("Failed to parse API response for name: " + name, e);
        }
    }

    /*
     * Gets detailed information about a specific meal by its ID.
     *
     * Returns full recipe details including ingredients, measurements,
     * instructions, category, area, and media links.
     *
     * Example:
     * <pre>
     * Recipe recipe = client.getMealById("52772");
     * System.out.println(recipe.getInstructions());
     * </pre>
     */
    public Recipe getMealById(String mealId) throws ApiException {
        // Validate input
        if (mealId == null || mealId.trim().isEmpty()) {
            throw new IllegalArgumentException("Meal ID cannot be null or empty");
        }

        try {
            // Construct URL
            String url = LOOKUP_BY_ID_URL + mealId.trim();

            // Make HTTP request
            String jsonResponse = makeHttpRequest(url);

            // Deserialize JSON to MealResponse
            MealResponse response = objectMapper.readValue(jsonResponse, MealResponse.class);

            // Get the first (and only) meal from response
            Recipe meal = response.getFirstMeal();
            if (meal == null) {
                throw new MealNotFoundException("No meal found with ID: " + mealId);
            }

            return meal;

        } catch (IOException e) {
            throw new ApiException("Failed to parse API response for meal ID: " + mealId, e);
        }
    }

    /*
     * Gets a random meal suggestion.
     *
     * Returns a complete random recipe with all details.
     * Great for "Surprise Me" or "What should I cook?" features.
     *
     * Example:
     * <pre>
     * Recipe randomMeal = client.getRandomMeal();
     * System.out.println("Try cooking: " + randomMeal.getName());
     * </pre>
     */
    public Recipe getRandomMeal() throws ApiException {
        try {
            // Make HTTP request
            String jsonResponse = makeHttpRequest(RANDOM_MEAL_URL);

            // Deserialize JSON to MealResponse
            MealResponse response = objectMapper.readValue(jsonResponse, MealResponse.class);

            // Get the random meal
            Recipe meal = response.getFirstMeal();
            if (meal == null) {
                throw new ApiException("API returned no random meal");
            }

            return meal;

        } catch (IOException e) {
            throw new ApiException("Failed to parse API response for random meal", e);
        }
    }

    // Helper Methods

    /*
     * Makes an HTTP GET request to the specified URL and returns the response body.
     *
     * This method handles:
     * - Building the HTTP request
     * - Sending the request
     * - Receiving the response
     * - Checking for HTTP errors
     * - Extracting the response body
     */
    private String makeHttpRequest(String url) throws ApiException {
        try {
            // Build HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                    .header("Accept", "application/json")  // We expect JSON response
                    .GET()
                    .build();

            // Send request and receive response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check HTTP status code
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new ApiException(
                        "API request failed with status code " + statusCode +
                                " for URL: " + url
                );
            }

            // Return the response body (JSON string)
            return response.body();

        } catch (IOException e) {
            throw new ApiException("Network error while communicating with API:  " + e.getMessage(), e);
        } catch (InterruptedException e) {
            // Restore interrupted status
            Thread.currentThread().interrupt();
            throw new ApiException("Request was interrupted: " + e.getMessage(), e);
        }
    }

//    // Utility Methods
//
//    /*
//     * Closes the HTTP client and releases resources.
//     *
//     * Call this when you're done using the client (e.g., on application shutdown).
//     * After calling this, the client should not be used anymore.
//     *
//     * Note: HttpClient doesn't have an explicit close() method, but this
//     * method is provided for future compatibility and resource management.
//     */
//    public void shutdown() {
//        // HttpClient in Java 11+ doesn't need explicit closing,
//        // but we provide this method for consistency and future-proofing
//        // The executor service will be cleaned up by garbage collection
//    }
}
