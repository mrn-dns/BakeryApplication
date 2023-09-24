package com.example.ca_2_baking_information_system;

import controllers.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.BakedGood;
import models.Ingredient;
import models.Recipe;
import utils.HashTable;

import java.io.IOException;

public class BakeryApplication extends Application {
    public static Scene scene1,scene2,scene3,scene4,scene5,scene6,scene7,scene8,scene9,scene10,scene11,scene12;
    public static Stage primaryStage;
    public static HashTable<BakedGood> bakedList = new HashTable<>(BakedGood.class,100);
    public static HashTable<Ingredient> ingredientHashTable = new HashTable<>(Ingredient.class, 100);

    //Method to reload every ListView in the application.
    public static void reloadContents() {
        BakedGoodController.bakedGoodController.updateBakedListView();
        IngredientController.ingredientController.updateIngredientListView();
        RecipeController.recipeController.updateBakedGoodChoiceBox();
        RecipeAddController.recipeAddController.populateBakedGoodChoiceBox();
        RecipeAddController.recipeAddController.populateIngredientListView();
        BakedGoodUpdateController.bakedGoodUpdateController.populateBakedGoodChoiceBox();
        IngredientUpdateController.ingredientUpdateController.populateIngredientGoodChoiceBox();
        //RecipeUpdateController.recipeUpdateController.populateRecipeChoiceBox();
        RecipeUpdateController.recipeUpdateController.populateBakedGoodChoiceBox();
        //SearchView1Controller.searchView1Controller.populateSearchByChoiceBox();
        SearchView1Controller.searchView1Controller.populateSearchTypeChoiceBox();
        MainViewController.mainViewController.initialize();
    }

    @Override
    //Method to load all the scenes into the application, load the save data on start, open the main menu scene and set the title, and save the application on exit.
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("mainViewBakery" +
                ".fxml"));
        scene1 = new Scene(fxmlLoader.load(), 900, 600);
        scene1.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("addBakedGoodView.fxml"));
        scene2 = new Scene(fxmlLoader.load(), 900, 600);
        scene2.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("addIngredientView.fxml"));
        scene3 = new Scene(fxmlLoader.load(), 900, 600);
        scene3.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("addRecipe.fxml"));
        scene4 = new Scene(fxmlLoader.load(), 900, 600);
        scene4.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("bakedGood-view.fxml"));
        scene5 = new Scene(fxmlLoader.load(), 900, 600);
        scene5.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("ingredient-view.fxml"));
        scene6 = new Scene(fxmlLoader.load(), 900, 600);
        scene6.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("recipe-view.fxml"));
        scene7 = new Scene(fxmlLoader.load(), 900, 600);
        scene7.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("SearchView.fxml"));
        scene8 = new Scene(fxmlLoader.load(), 900, 600);
        scene8.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("updateBakedGoodView.fxml"));
        scene9 = new Scene(fxmlLoader.load(), 900, 600);
        scene9.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("updateIngredientView.fxml"));
        scene10 = new Scene(fxmlLoader.load(), 900, 600);
        scene10.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        fxmlLoader = new FXMLLoader(BakeryApplication.class.getResource("updateRecipe.fxml"));
        scene11 = new Scene(fxmlLoader.load(), 900, 600);
        scene11.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());

        MainViewController.mainViewController.BakeryLoad();
        reloadContents();

        stage.setTitle("Baked at SETU");
        stage.setScene(scene1);
        stage.show();
        primaryStage = stage;
        primaryStage.setOnCloseRequest(new EventHandler<>() {
            public void handle(WindowEvent we) {
                MainViewController.mainViewController.BakerySave();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}