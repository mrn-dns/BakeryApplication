package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.BakedGood;
import models.Ingredient;

public class IngredientUpdateController {
    public TextField nameField,calorieField,unitField;
    public TextArea descriptionField;
    public ChoiceBox<Ingredient> ingredientChoice;
    public static IngredientUpdateController ingredientUpdateController;
    public void initialize() {ingredientUpdateController=this;}

    //---------------------
    // Handling Methods
    //---------------------

    //Method to fill all fields in the update ingredient scene with the chosen baked goods data.
    public void fillInformation() {
        if(!ingredientChoice.getSelectionModel().isEmpty()) {
            nameField.setText(ingredientChoice.getSelectionModel().getSelectedItem().getName());
            calorieField.setText(String.valueOf(ingredientChoice.getSelectionModel().getSelectedItem().getCalPer100Unit()));
            unitField.setText(ingredientChoice.getSelectionModel().getSelectedItem().getUnitOfMeasurement());
            descriptionField.setText(ingredientChoice.getSelectionModel().getSelectedItem().getDescription());
        }
    }

    //Method to populate the ingredientChoice ChoiceBox with all ingredients.
    public void populateIngredientGoodChoiceBox() {
        ingredientChoice.getItems().clear();
        for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
            if(i != null){
                ingredientChoice.getItems().add(i);
            }
        }
    }

    //Method that allows the user to edit the existing data in an ingredient with new data.
    public void updateIngredient(ActionEvent event) {
        BakeryApplication.ingredientHashTable.delete(ingredientChoice.getSelectionModel().getSelectedItem(),ingredientChoice.getSelectionModel().getSelectedItem().getName());
        Ingredient ingredient = new Ingredient(nameField.getText(),descriptionField.getText(),Integer.parseInt(calorieField.getText()),unitField.getText());
        BakeryApplication.ingredientHashTable.add(ingredient, ingredientChoice.getSelectionModel().getSelectedItem().getName());
        nameField.clear();
        calorieField.clear();
        descriptionField.clear();
        unitField.clear();
        BakeryApplication.reloadContents();
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
