package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import models.Ingredient;
import models.IngredientQuantity;
import models.Recipe;
import utils.Node;
import utils.NodeList;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class RecipeController {
    public static RecipeController recipeController;
    public ListView<Recipe> recipeMainList;
    public ChoiceBox<BakedGood> bakedGoodChoiceBox;
    public void initialize(){
        recipeController = this;
    }

    //---------------------
    // Handling Methods
    //---------------------

    //Method to display all baked goods in the bakedGoodChoiceBox Listview.
    public void updateBakedGoodChoiceBox(){
        bakedGoodChoiceBox.getItems().clear();
        //The method goes through each entry in the baked good hashtable and if the entry isn't null it is added to the bakedGoodChoiceBox Listview.
        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null){
                bakedGoodChoiceBox.getItems().add(b);
            }
        }
    }

    //Method to updates the recipeMainList by taking the recipes for a chosen baked good and adding them to the bakedGoodChoiceBox Listview.
    public void updateRecipeList(){
        recipeMainList.getItems().clear();
        //The method takes the selected baked good and adds every recipe for that baked good to the recipeMainList Listview.
        for(Recipe r : bakedGoodChoiceBox.getSelectionModel().getSelectedItem().getRecipes()){
            recipeMainList.getItems().add(r);
        }
    }

    //Method to display all recipes for a chosen baked good in the bakedGoodChoiceBox Listview.
    public void populateRecipeList(ActionEvent event) {
        recipeMainList.getItems().clear();
        //The method takes the selected baked good and adds every recipe for that baked good to the recipeMainList Listview.
        for(Recipe r : bakedGoodChoiceBox.getSelectionModel().getSelectedItem().getRecipes()){
            recipeMainList.getItems().add(r);
        }
    }

    //Method to remove a recipe by the user selecting a recipe from the recipeMainList Listview.
    public void removeRecipe(MouseEvent mouseEvent) {
        bakedGoodChoiceBox.getSelectionModel().getSelectedItem().recipes.delete(recipeMainList.getSelectionModel().getSelectedItem());
        updateRecipeList();
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
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }

    //Method to allow the user to return to the ingredient scene.
    public void returnIngredient(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene6);
    }

    //Method to allow the user to change to the add recipe scene.
    public void addRecipePage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene4);
    }

    //Method to allow the user to change to the update recipe scene.
    public void updateRecipePage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene11);
    }
}


