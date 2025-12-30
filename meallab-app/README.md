# MealLab Desktop Application

JavaFX desktop application for recipe management and meal planning.

## Structure

- `src/main/java/` - Java source code
    - `com.meallab.app` - Main application class
    - `com.meallab.app.controller` - JavaFX controllers
    - `com.meallab.app.view` - FXML view files
    - `com.meallab.app.model` - Application-specific models
    - `com.meallab.app.service` - Business logic services
    - `com.meallab.app.util` - Utility classes
- `src/main/resources/` - FXML files, CSS, images
- `src/test/java/` - JUnit test classes
- `pom.xml` - Maven project configuration

## Features

- Recipe search by ingredient/name
- Random recipe suggestions
- Favorite recipes list
- Cooked recipes list
- Detailed recipe view with instructions
- Persistent data storage (JSON)
- User-friendly JavaFX interface

## Running

```bash
mvn javafx:run