package com.meallab.api.model;

import java.util.Objects;

/*
 * Represents a recipe ingredient with its name and measurement.
 *
 * This class is a simple POJO (Plain Old Java Object) that combines
 * an ingredient name (e.g., "olive oil") with its measurement
 * (e.g., "1/4 cup").
 *
 * The TheMealDB API returns ingredients and measures as separate
 * fields (strIngredient1-20, strMeasure1-20), but this class
 * provides a cleaner way to work with ingredient data.
 */

public class Ingredient {

    private String name;
    private String measure;

    /*
     * Default no-argument constructor.
     * Required by Jackson library for JSON deserialization.
     * Creates an Ingredient with null values for name and measure.
     */
    public Ingredient(){
    }

    /*
     * Constructor with parameters for easy object creation.
     */
    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMeasure(){
        return measure;
    }

    public void setMeasure(String measure){
        this.measure = measure;
    }

    /** Utility Methods */

    /*
     * Checks if this ingredient is empty (both name and measure are null or empty).
     *
     * This is useful because the API returns 20 ingredient slots, but most recipes
     * only use 10-15 of them. The rest are empty strings.
     *
     * Returns true if the ingredient has no name or measure, false otherwise
     */
    public boolean isEmpty(){
        return (name == null || name.trim().isEmpty()) &&
                (measure == null || measure.trim().isEmpty());
    }

    /** Override Methods */
    @Override
    public String toString(){
        String measureStr = (measure != null && !measure.isEmpty()) ? measure + " " : "";
        String nameStr = (name != null && !name.isEmpty()) ? name : "";
        return measureStr + nameStr;
    }

    @Override
    public boolean equals(Object obj){
        // Comparing to itself
        if(this == obj){
            return true;
        }

        // Check if object is null or different class
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        // Cast to Ingredient and compare fields
        Ingredient other = (Ingredient) obj;
        return Objects.equals(name, other.name) &&
                Objects.equals(measure, other.measure);
    }

    /*
     * Generates a hash code for this ingredient.
     *
     * This method must be overridden whenever equals() is overridden.
     * It's used by hash-based collections (HashMap, HashSet) to efficiently
     * store and retrieve objects.
     *
     * The contract:  if two objects are equal (according to equals()),
     * they must have the same hash code.
     *
     * Returns a hash code value for this ingredient
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, measure);
    }
}
