package models;

public class IngredientQuantity {

    private Ingredient ingredient;
    private int quantity;
    private int calories;

    //---------------------
    // Constructor Methods
    //---------------------

    public IngredientQuantity(Ingredient ingredient, int quantity){
        setIngredient(ingredient);
        setQuantity(quantity);
        setCalories((int) ((quantity/100)*ingredient.getCalPer100Unit()));
    }

    //---------------------
    // Setter Methods
    //---------------------

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    //---------------------
    // Getter Methods
    //---------------------

    public Ingredient getIngredient() {
        return ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public int getCalories(){
        return calories;
    }

    //---------------------
    // Handling Methods
    //---------------------

    @Override
    public String toString() {
        return ingredient.getName() + " " + quantity + ingredient.getUnitOfMeasurement();
    }

    public String contents() {
        return "IngredientQuantity{" +
                "ingredient=" + ingredient.toString() +
                ", quantity=" + quantity +
                ", calories=" + calories +
                '}';
    }
}
