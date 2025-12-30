package com.meallab.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/*
 * Represents a simplified meal object returned by ingredient search.
 *
 * When searching by ingredient (filter. php?i=chicken), the API returns
 * only basic information:  meal name, thumbnail, and ID.
 *
 * To get full details (instructions, ingredients), you must make a
 * second API call using the meal ID.
 */

public class SimplifiedMeal {

    @JsonProperty("idMeal")
    private String id;

    @JsonProperty("strMeal")
    private String name;

    @JsonProperty("strMealThumb")
    private String thumbnailUrl;

    public SimplifiedMeal() {
    }

    public SimplifiedMeal(String id, String name, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    //Override Methods

    /*
     * Returns a string representation of this simplified meal.
     */
    @Override
    public String toString() {
        return "SimplifiedMeal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /*
     * Compares this meal with another object for equality.
     * Two meals are equal if they have the same ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        SimplifiedMeal that = (SimplifiedMeal) obj;
        return Objects.equals(id, that.id);
    }

    /*
     * Generates a hash code based on the meal ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
