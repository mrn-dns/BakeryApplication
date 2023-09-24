package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Ingredient;
import models.IngredientQuantity;
import utils.NodeList;

public class IngredientQuantityController {

    public IngredientQuantityController() {
        this.ingredientQuantities = new NodeList<>();
    }

    public static NodeList<IngredientQuantity> ingredientQuantities;


    public NodeList<IngredientQuantity> getIngredientQuantity() {
        return ingredientQuantities;
    }

    private ListView IngredientQuantityListView;

    private ChoiceBox<Ingredient> AddIngredient;

    private TextField AddQuantityUnit;

    private TextField AddCalories;

    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) {
        ingredientQuantities.addNode(ingredientQuantity);
    }

    public void deleteAllIngredientQuantities() {
        ingredientQuantities.reset();
    }

    public void addIngredientQuantitiesInfo (ActionEvent event) {
        IngredientQuantity ingredientQuantity;
        ingredientQuantity = new IngredientQuantity(AddIngredient.getValue(), Integer.parseInt(AddQuantityUnit.getText()));

        IngredientQuantityListView.getItems().add(ingredientQuantity.toString());

        addIngredientQuantity(ingredientQuantity);
    }

    public void home(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }
}
