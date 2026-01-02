package com.meallab.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * Main JavaFX application class for MealLab.
 *
 * This is the entry point for the desktop application.
 * It extends javafx.application.Application and overrides the start() method
 * to set up the primary window (Stage) and initial scene.
 *
 * The application provides:
 * - Recipe search by ingredient and name
 * - Random recipe suggestions
 * - Favorites list management
 * - Cooked recipes list management
 * - Persistent data storage
 */

public class MealLabApplication extends Application {

    /*
     * The main entry point for the JavaFX application.
     *
     * This method is called after the JavaFX runtime initializes.
     * It sets up the primary window (Stage) with the initial scene.
     */
    @Override
    public void start(Stage primaryStage){

        // Set window title
        primaryStage.setTitle("MealLab - Recipe Management System");

        // Create a simple label
        Label welcomeLabel = new Label("Welcome to MealLab. Application is starting ...");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-padding: 50px");

        // Create a Layout container
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel);

        // Create a scene with the layout
        Scene scene = new Scene(root, 800, 600);

        // Set the scene on the stage
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();

        System.out.println("MealLab Application started successfully!");
    }

    /*
     * This method is called when the application should stop.
     *
     * Override this to perform cleanup tasks:
     * - Save data to files
     * - Close database connections
     * - Release resources
     */
    @Override
    public void stop(){
        System.out.println("MealLab Application is shutting down...");
    }

    /*
     * Main method - launches the JavaFX application.
     *
     * This is the traditional Java entry point.
     * It calls Application.launch() which handles the JavaFX lifecycle.
     */
    public static void main(String[] args){
        // Launch the JavaFX application
        // This will call init() → start() → (application runs) → stop()
        launch(args);
    }
}
