package com.meallab.api.exception;

/*
 * Base exception class for all MealDB API related errors.
 *
 * This exception is thrown when there are issues communicating with
 * the TheMealDB API, such as:
 * - Network connectivity problems
 * - Invalid API responses
 * - HTTP errors (4xx, 5xx status codes)
 * - JSON parsing failures
 *
 * By creating a custom exception, we can handle API-specific errors
 * separately from generic Java exceptions.
 */

public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }

    /*
     * Constructs a new ApiException with the specified detail message and cause.
     *
     * This is useful when wrapping other exceptions (e.g., IOException,
     * JsonProcessingException) to provide context about what API operation failed.
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
