package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Ingredient;

public class IngredientAddController {
    public TextField nameField;
    public TextField calorieField;
    public TextField unitField;
    public TextArea descriptionField;

    //---------------------
    // Handling Methods
    //---------------------

    //Method to add a brand-new ingredient to the hashtable.
    public void addIngredient(ActionEvent event) {
    //If statement checks if the values entered into the fields are the correct type.
        if ((isValidNameValue(nameField.getText())) && (isValidDescriptionValue(descriptionField.getText())) && (isValidCalorieValue(calorieField.getText())) && (isValidUnitValue(unitField.getText()))) {
            Ingredient ingredient = new Ingredient(nameField.getText(), descriptionField.getText(), Integer.parseInt(calorieField.getText()), unitField.getText());
            BakeryApplication.ingredientHashTable.add(ingredient, ingredient.getName());
            BakeryApplication.reloadContents();

    //When this method finishes it clears the fields in the JavaFX so the user can add another one.
            nameField.clear();
            descriptionField.clear();
            calorieField.clear();
            unitField.clear();

    //If this fails the system will print out an error message.
        } else System.out.println("Add failed, please try again.");
    }

    //---------------------
    // Validation Methods
    //---------------------

    //Method to ensure the ingredient name value only consists of letters.
    public static boolean isValidNameValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //Method to ensure the ingredient description only consists of letters.
    public static boolean isValidDescriptionValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //Method to ensure the ingredient calorie value only consists of numbers.
    public static boolean isValidCalorieValue(String value) {
        String expression = "^[0-9]*$";
        return value.matches(expression);
    }

    //Method to ensure the ingredient unit only consists of letters.
    public static boolean isValidUnitValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //---------------------
    // View Change Methods
    //---------------------

    //Method to allow the user to return to the home menu from this scene.
    public void home(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }

    //Method to allow the user to return to the baked good scene.
    public void returnBaked(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene5);
    }

    //Method to allow the user to return to the recipe scene.
    public void returnRecipe(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene7);
    }

    //Method to allow the user to return to the ingredient scene.
    public void returnIngredient(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene6);
    }
}
