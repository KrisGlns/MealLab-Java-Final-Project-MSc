package com.meallab.api.exception;

/*
 * Exception thrown when a requested meal cannot be found in the API.
 *
 * This occurs when:
 * - Searching for a meal ID that doesn't exist
 * - Searching for an ingredient/name with no results
 * - The API returns {"meals": null}
 *
 * This is a more specific exception than ApiException, allowing
 * the application to handle "not found" scenarios differently
 * (e.g., showing a "No results" message instead of an error).
 */

public class MealNotFoundException extends ApiException{
    public MealNotFoundException(String message) {
        super(message);
    }
}
