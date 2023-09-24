package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import models.BakedGood;
import models.Ingredient;
import models.IngredientQuantity;
import models.Recipe;
import utils.NodeList;
import utils.Utils;

public class RecipeAddController {
    public TextArea stepField;
    public TextField quantityField;
    public ListView<Ingredient> ingredientsList;
    public ListView<IngredientQuantity> chosenIngredientList;
    public ListView<String> stepList;
    public ChoiceBox<BakedGood> bakedGoodsChoice;
    public Button updateChoiceBoxButton;
    public int count = 1;
    private NodeList<String> steps = new NodeList<>();
    private NodeList<IngredientQuantity> ingredients = new NodeList<>();
    public  static RecipeAddController recipeAddController;
    public void initialize() {
        recipeAddController = this;
    }

    //---------------------
    // Handling Methods
    //---------------------

    //Method to update bakedGoodsChoice ChoiceBox with all baked goods.
    public void updateChoiceBox(ActionEvent event) {
        populateBakedGoodChoiceBox();
    }

    //Method to display any steps added to the recipe being created.
    public void populateStepListView(){
        stepList.getItems().clear();
        for(String s : steps){
            stepList.getItems().add(s);
        }
    }

    //Method to display all baked goods in the bakedGoodsChoice ChoiceBox.
    public void populateBakedGoodChoiceBox(){
        bakedGoodsChoice.getItems().clear();
        //The method goes through each entry in the baked good hashtable in if the entry isn't null it is added to the bakedGoodsChoice ChoiceBox.
        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null){
                System.out.println(b);
                bakedGoodsChoice.getItems().add(b);
            }
        }
    }

    //Method to display all ingredients in the ingredientsList Listview.
    public void populateIngredientListView(){
        ingredientsList.getItems().clear();
        //The method goes through each entry in the ingredient hashtable and if the entry isn't null it is added to the ingredientsList Listview.
        for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
            if(i != null){
                ingredientsList.getItems().add(i);
            }
        }
    }

    //Method to display all chosen ingredients for a recipe in the chosenIngredientList Listview.
    public void populateChosenIngredientsListView(){
        chosenIngredientList.getItems().clear();
        for(IngredientQuantity i : ingredients){
            chosenIngredientList.getItems().add(i);
        }
    }

    //Method to add a step to a recipe.
    public void addStep(ActionEvent event) {
        if(isValidStepValue(stepField.getText())) {
            steps.addNode(count + ". " + stepField.getText());

            //Each time a new step is added to the recipe the local variable count increases, the field is cleared, and the listviews and choice box are reloaded.
            count++;
            stepField.clear();
            populateStepListView();
            populateBakedGoodChoiceBox();
            populateIngredientListView();

    //If this fails the system will print out an error message.
        } else System.out.println("Add failed, please try again");
    }

    //Method to add an ingredient to the recipe with its quantity.
    public void addIngredient(ActionEvent event) {
        if(isValidQuantityValue(quantityField.getText())) {
    //If statement checks if the values entered into the field are the correct type.
            IngredientQuantity newIngredient;
            newIngredient = ingredients.addNode(new IngredientQuantity(ingredientsList.getSelectionModel().getSelectedItem(), Integer.parseInt(quantityField.getText()))).getContents();
            chosenIngredientList.getItems().add(newIngredient);

    //If this fails the system will print out an error message.
        } else System.out.println("Add failed, please try again.");
    }

    //Method to add a brand-new recipe to the LinkedList.
    public void addRecipe(ActionEvent event) {
        bakedGoodsChoice.getSelectionModel().getSelectedItem().addRecipe(new Recipe(steps, ingredients));
        count = 1;
        steps = new NodeList<String>();
        ingredients = new NodeList<IngredientQuantity>();
        quantityField.clear();
        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null){
                for(Recipe r : b.recipes){
                    System.out.println(r.toString());
                }
            }
        }
        BakeryApplication.reloadContents();
        ingredients = new NodeList<>();
        steps = new NodeList<>();
        populateStepListView();
        populateBakedGoodChoiceBox();
        populateIngredientListView();
        populateChosenIngredientsListView();
    }

    //---------------------
    // Validation Methods
    //---------------------

    //Method to ensure the recipe step value only consists of letters.
    public static boolean isValidStepValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //Method to ensure the ingredient quantity value only consists of numbers.
    public static boolean isValidQuantityValue(String value) {
        String expression = "^[0-9]*$";
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
    public void returnToBakedGood(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene5);
    }

    //Method to allow the user to return to the recipe scene.
    public void returnToRecipe(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene7);
    }

    //Method to allow the user to return to the ingredient scene.
    public void returnToIngredient(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene6);
    }
}
