package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import models.Ingredient;
import models.IngredientQuantity;
import models.Recipe;
import utils.Node;
import utils.NodeList;

public class RecipeUpdateController {
    public TextArea stepField;
    public ListView<Ingredient> ingredientsList;
    public ListView<String> stepsList;
    public TextField quantityField;
    public ListView<IngredientQuantity> chosenIngredientList;
    public ChoiceBox<Recipe> recipeChoice;
    public static RecipeUpdateController recipeUpdateController;
    public ChoiceBox<BakedGood> bakedGoodChoice;
    public NodeList<String> prevDelete = new NodeList<>();
    private NodeList<String> steps = new NodeList<>();
    private NodeList<IngredientQuantity> ingredients = new NodeList<>();
    public void initialize() {recipeUpdateController=this;}

    String stepToEdit = null;

    //---------------------
    // Handling Methods
    //---------------------

    //Method to fill all listViews in the update recipe scene with the chosen recipes' data.
    public void fillInformation() {
            stepsList.getItems().clear();
            ingredientsList.getItems().clear();
            chosenIngredientList.getItems().clear();
            //For loop adds all ingredients into the ingredientsList ListView.
            for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
                if(i != null) ingredientsList.getItems().add(i);
                System.out.println(i);
            }
            //For loop adds all steps for the selected recipe to the stepsList ListView.
            for(String r : recipeChoice.getSelectionModel().getSelectedItem().getSteps()) {
                stepsList.getItems().add(r);
            }
            //For loop adds all ingredients that are part of the recipe to the chosenIngredientList ListView.
            for(IngredientQuantity i : recipeChoice.getSelectionModel().getSelectedItem().getIngredients()) {
                if(i != null) chosenIngredientList.getItems().add(i);
                System.out.println(i);
            }
            quantityField.setText(recipeChoice.getSelectionModel().getSelectedItem().getTotalCalories()+"");
    }

    //Method to populate the bakedChoice ChoiceBox with all baked goods
    public void populateBakedGoodChoiceBox() {
        bakedGoodChoice.getItems().clear();
        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null){
                bakedGoodChoice.getItems().add(b);
            }
        }
    }

    //Method to populate the recipeChoice ChoiceBox with all recipes from the selected baked good.
    public void populateRecipeChoiceBox() {
        recipeChoice.getItems().clear();
        if(recipeChoice.getSelectionModel().isEmpty()) {
            if(recipeChoice.getSelectionModel()!=null){
                for (Recipe r : bakedGoodChoice.getSelectionModel().getSelectedItem().recipes) {
                    recipeChoice.getItems().add(r);
                }
            }
        }
    }

    //Method to populate the recipeChoice ChoiceBox with all recipes from the selected baked good.
    public void updateRecipeChoiceBox(MouseEvent mouseEvent) {
        if(bakedGoodChoice.getSelectionModel().getSelectedItem() != null){
            populateRecipeChoiceBox();
        }
    }

    //Method that allows the user to edit the existing data in a recipe with new data.
    public void updateRecipe(ActionEvent event) {
        for(Recipe r : bakedGoodChoice.getSelectionModel().getSelectedItem().recipes)
            if(r.toString().equals(recipeChoice.getSelectionModel().getSelectedItem().toString()))
                bakedGoodChoice.getSelectionModel().getSelectedItem().recipes.delete(r);
        bakedGoodChoice.getSelectionModel().getSelectedItem().addRecipe(new Recipe(steps, ingredients));
        ingredients = new NodeList<>();
        steps = new NodeList<>();
        stepField.clear();
        ingredientsList.getItems().clear();
        stepsList.getItems().clear();
        quantityField.clear();
        chosenIngredientList.getItems().clear();
        bakedGoodChoice.getSelectionModel().clearSelection();
        recipeChoice.getSelectionModel().clearSelection();
    }

    //Method to allow the user to add steps to an existing recipe.
    public void addStep(ActionEvent event) {
        NodeList<String> steps = recipeChoice.getSelectionModel().getSelectedItem().getSteps();

        if(stepToEdit != null) {
            Node<String> updateNode = null;

            while (steps.temp != null) {
                if (steps.temp.getContents().equalsIgnoreCase(stepToEdit)) {
                    updateNode = steps.temp;
                    steps.temp = steps.head;
                    break;
                }
                steps.temp = steps.temp.next;
            }
            steps.temp = steps.head;
            if (updateNode != null) {
                updateNode.setContents(stepToEdit.substring(0, 3) + stepField.getText());
            }
            stepsList.getItems().clear();
            for (String r : recipeChoice.getSelectionModel().getSelectedItem().getSteps()) {
                stepsList.getItems().add(r);
            }
            stepField.clear();
            stepToEdit = null;
        }else {
            if(!stepField.getText().isEmpty()){
                int index = Integer.parseInt(steps.head.contents.substring(0,1))+1;
                steps.addNode(index+". "+stepField.getText());
            }
            stepsList.getItems().clear();
            for (String r : recipeChoice.getSelectionModel().getSelectedItem().getSteps()) {
                stepsList.getItems().add(r);
            }
        }
    }

    //Method to allow the user to edit steps that exist in a recipe.
    public void EditStep(ActionEvent event) {
        if(stepsList.getSelectionModel().getSelectedItem() != null){
            stepToEdit = stepsList.getSelectionModel().getSelectedItem();
            stepField.setText(stepToEdit.substring(3));
        }
    }

    //Method to allow the user to delete steps that exist in a recipe.
    public void deleteStep() {
        if(stepsList.getSelectionModel().getSelectedItem() != null){
            NodeList<String> steps = recipeChoice.getSelectionModel().getSelectedItem().getSteps();
            prevDelete.addNode(stepsList.getSelectionModel().getSelectedItem());
            steps.delete(stepsList.getSelectionModel().getSelectedItem());

            int index = 1;
            Node<String> prevNode = steps.getLastNode();
            Node<String> curNode = prevNode;

            while(curNode != steps.head){
                if(Integer.parseInt(curNode.contents.substring(0,1)) != index){
                    String tempStr = curNode.getContents();
                    curNode.setContents(index+". "+tempStr.substring(3));
                }
                index++;
                while(steps.temp != null){
                    if(steps.temp.next == curNode){
                        curNode = steps.temp;
                        steps.temp = steps.head;
                        break;
                    }
                    steps.temp = steps.temp.next;
                }
            }
            if(Integer.parseInt(steps.temp.contents.substring(0,1)) != index){
                String tempStr = curNode.getContents();
                curNode.setContents(index+". "+tempStr.substring(3));
            }



            stepsList.getItems().clear();
            for(String r : recipeChoice.getSelectionModel().getSelectedItem().getSteps()) {
                stepsList.getItems().add(r);
            }
        }
    }

    //Method to allow the user to add an ingredient to an existing recipe.
    public void addIngredient(ActionEvent event) {
        if(isValidQuantityValue(quantityField.getText())) {
            //If statement checks if the values entered into the field are the correct type.
            IngredientQuantity newIngredient;
            newIngredient = recipeChoice.getSelectionModel().getSelectedItem().getIngredients().addNode(new IngredientQuantity(ingredientsList.getSelectionModel().getSelectedItem(), Integer.parseInt(quantityField.getText()))).getContents();
            chosenIngredientList.getItems().add(newIngredient);

            //If this fails the system will print out an error message.
        } else System.out.println("Add failed, please try again.");
    }

    //Method to allow the user to delete ingredients from a recipe.
    public void deleteIngredient() {
        //Select the ingredient you want to remove and press the delete button
        chosenIngredientList.getItems().remove(chosenIngredientList.getSelectionModel().getSelectedItem());
    }

    //Method to fill all listViews in the update recipe scene with the chosen recipes' data.
    public void updateInformation(MouseEvent mouseEvent) {
        if(recipeChoice.getSelectionModel().getSelectedItem() != null){
            fillInformation();
        }
    }

    //---------------------
    // Validation Methods
    //---------------------

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
