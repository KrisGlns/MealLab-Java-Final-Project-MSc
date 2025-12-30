# MealLab API Library

This module contains the API client library for communicating with TheMealDB REST API.

## Structure

- `src/main/java/` - Java source code
    - `com. meallab.api` - Main API client classes
    - `com.meallab.api.model` - POJO classes (Recipe, Ingredient, etc.)
    - `com.meallab.api.service` - Service classes for API calls
    - `com.meallab.api.exception` - Custom exception classes
- `src/test/java/` - JUnit test classes
- `pom.xml` - Maven project configuration

## Features

- Search recipes by ingredient
- Search recipes by name
- Get recipe details by ID
- Get random recipe suggestion
- JSON deserialization
- Comprehensive exception handling

## Building

This module will be packaged as a JAR file and installed to the local Maven repository for use by the MealLab App.

```bash
mvn clean install