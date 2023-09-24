package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import models.Ingredient;
import models.IngredientQuantity;
import models.Recipe;
import utils.HashTable;
import utils.NodeList;

public class MainViewController {
    public static MainViewController mainViewController;

    public static HashTable<BakedGood> bakedGoods;
    public static HashTable<Ingredient> ingredients;
    public static NodeList<IngredientQuantity> ingredientQuantities;
    public static NodeList<Recipe> recipes;
    public Button searchButton;
    public void initialize() {
        mainViewController = this;
        listView.getItems().clear();
        for(BakedGood bakedGood : BakeryApplication.bakedList.getHashTable()){
            if(bakedGood != null){
            listView.getItems().add(bakedGood);}
        }
    }

    public MainViewController() {
        bakedGoods = new HashTable<>(BakedGood.class, 100);
        ingredients = new HashTable<>(Ingredient.class, 100);
        ingredientQuantities = new NodeList<>();
        recipes = new NodeList<>();
    }

    //---------------------
    // View Change Methods
    //---------------------

    //Method to allow the user to go to the baked good scene.
    public void bakedGoodMain(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene5);
    }

    //Method to allow the user to go to the recipe scene.
    public void recipeMain(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene7);
    }

    //Method to allow the user to go to the ingredient scene.
    public void ingredientMain(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene6);
    }

    //Method to allow the user to go to the search scene.
    public void searchPage(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene8);
    }

    //---------------------
    // Persistence Methods
    //---------------------

    @FXML
    public void BakerySave() {
        try {
            System.out.println("Save successful.");
            BakedGoodController.bakedGoodController.save();
            IngredientController.ingredientController.save();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    @FXML
    public void BakeryLoad() {
        try {
            BakedGoodController.bakedGoodController.load();
            IngredientController.ingredientController.load();
            BakeryApplication.reloadContents();
            System.out.println("Load successful.");
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    //---------------------
    // Drill Down Methods
    //---------------------

    @FXML
    private Button BakedGoodButton;
    @FXML
    private Button RecipeButton;
    @FXML
    private ListView<Object> listView;
    private BakedGood currentBakedGood;
    private Recipe currentRecipe;

    @FXML
    //Method that allows the user to change from a baked goods recipe view back to the baked good view.
    void bakedGoodButton(ActionEvent event) {
        listView.getItems().clear();
        for(BakedGood bakedGood : BakeryApplication.bakedList.getHashTable()) {
            if (bakedGood != null){
                listView.getItems().add((Object) bakedGood);
            }
        }
        currentBakedGood = null;
    }

    @FXML
    //Method that allows the user to change from a recipes ingredient view back to the recipe view.
    void recipeButton(ActionEvent event) {
        listView.getItems().clear();
        for(Recipe recipe : currentBakedGood.getRecipes()){
            listView.getItems().add(recipe);
        }
        currentRecipe = null;
    }

    //Method to allow the user to navigate the drill-down menu.
    public void handle(MouseEvent mouseEvent) {
        //If statement checks if the selected item is a baked food.
        if(listView.getSelectionModel().getSelectedItem() instanceof BakedGood){
            //If this is true the ListView will be populated with the selected baked goods recipes.
            currentBakedGood = (BakedGood) listView.getSelectionModel().getSelectedItem();
            NodeList<Recipe> RecipeList = ((BakedGood) listView.getSelectionModel().getSelectedItem()).getRecipes();
            listView.getItems().clear();
            for(Recipe recipe : RecipeList){
                listView.getItems().add(recipe);
            }
        }
        //If statement checks if the selected item is a recipe.
        if(listView.getSelectionModel().getSelectedItem() instanceof Recipe){
            //If this is true the ListView will be populated with the selected recipes ingredients.
            currentRecipe = (Recipe) listView.getSelectionModel().getSelectedItem();
            NodeList<IngredientQuantity> IngredientList = ((Recipe) listView.getSelectionModel().getSelectedItem()).getIngredients();
            listView.getItems().clear();
            for(IngredientQuantity ingredientQuantity : IngredientList){
                listView.getItems().add(ingredientQuantity);
            }
        }
    }
}