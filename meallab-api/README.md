# MealLab API Library

This module contains the API client library for communicating with TheMealDB REST API.

## Structure

- `src/main/java/` - Java source code
  - `com.meallab.api.model` - POJO classes (Recipe, Ingredient, SimplifiedMeal, response wrappers)
  - `com.meallab.api.service` - Service classes (MealDbApiClient)
  - `com.meallab.api.exception` - Custom exception classes
- `src/test/java/` - JUnit test classes
- `pom.xml` - Maven project configuration

## Features

- ✅ Search recipes by ingredient
- ✅ Search recipes by name
- ✅ Get recipe details by ID
- ✅ Get random recipe suggestion
- ✅ JSON deserialization with Jackson
- ✅ Comprehensive exception handling
- ✅ HTTP/2 support with timeouts
- ✅ Input validation and URL encoding

## Building

### Compile the project:
```bash
mvn clean compile
```

### Run tests:
```bash
mvn test
```

### Package as JAR:
```bash
mvn clean package
```

### Install to local Maven repository:
```bash
mvn clean install
```

## Usage Example

```java
import com.meallab.api.service.MealDbApiClient;
import com.meallab.api.model.*;
import com.meallab.api.exception.ApiException;

public class Example {
    static void main(String[] args) {
        MealDbApiClient client = new MealDbApiClient();
        
        try {
            // Search by ingredient
            MealListResponse results = client.searchByIngredient("chicken");
            System.out.println("Found " + results.getMealCount() + " recipes");
            
            // Get full recipe details
            SimplifiedMeal firstResult = results.getMealAt(0);
            Recipe recipe = client.getMealById(firstResult.getId());
            System.out.println("Recipe:  " + recipe.getName());
            System.out.println("Instructions: " + recipe.getInstructions());
            
            // Get ingredients
            for (Ingredient ingredient : recipe.getIngredients()) {
                System.out.println("  - " + ingredient);
            }
            
            // Random meal
            Recipe random = client.getRandomMeal();
            System.out.println("Random suggestion: " + random.getName());
            
        } catch (ApiException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## Maven Dependency

After installing to local repository, add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.meallab</groupId>
    <artifactId>meallab-api</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## API Endpoints Used

| Method | Endpoint | Returns |
|--------|----------|---------|
| `searchByIngredient()` | `filter.php?i={ingredient}` | List of simplified meals |
| `searchByName()` | `search.php?s={name}` | List of full recipes |
| `getMealById()` | `lookup.php?i={id}` | Single full recipe |
| `getRandomMeal()` | `random.php` | Single random recipe |

## Exception Handling

- **`ApiException`** - General API communication errors (network, HTTP errors, parsing)
- **`MealNotFoundException`** - Specific case when no results found (extends ApiException)
- **`IllegalArgumentException`** - Invalid input parameters (null, empty strings)

## Test Coverage

- **Unit Tests** - POJO and response wrapper classes
- **Integration Tests** - Real API communication

## Requirements

- Java 17 or higher
- Internet connection (for API calls)
- Maven 3.6+

## Author

Christos Galanis (KrisGlns)

## License

Academic project - M.Sc. Java Course Final Project