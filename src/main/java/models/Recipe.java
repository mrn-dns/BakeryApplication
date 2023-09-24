package models;

import controllers.RecipeController;
import utils.Node;
import utils.NodeList;

public class Recipe {
    NodeList<IngredientQuantity> ingredients = new NodeList<>();
    NodeList<String> steps = new NodeList<>();
    int calories;

    //---------------------
    // Constructor Methods
    //---------------------

    public Recipe(NodeList<String> steps, NodeList<IngredientQuantity> ingredients){
        setSteps(steps);
        setIngredients(ingredients);
        calories = getTotalCalories();
    }

    //---------------------
    // Getter Methods
    //---------------------

    public NodeList<IngredientQuantity> getIngredients() {
        return ingredients;
    }

    public NodeList<String> getSteps() {
        return steps;
    }

    //---------------------
    // Setter Methods
    //---------------------

    public void setIngredients(NodeList<IngredientQuantity> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(NodeList<String> steps) {
        this.steps = steps;
    }

    //---------------------
    // Handling Methods
    //---------------------

    public void addStep(String step){
        steps.addNode(step);
    }

    public void addIngredients(Ingredient ingredient, int quantity){
        ingredients.addNode(new IngredientQuantity(ingredient, quantity));
    }

    //Method to get the total calories in a recipe by adding the calories of each ingredient together.
    public int getTotalCalories(){
        int count = 0;
        for(IngredientQuantity i : ingredients){
            count += i.getCalories();
        }
        return count;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
