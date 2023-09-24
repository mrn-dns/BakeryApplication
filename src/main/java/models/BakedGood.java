package models;

import utils.NodeList;

import java.util.Objects;

public class BakedGood {

    public String bakedName, bakedPlace, bakedDesc, bakedPicture;
    public NodeList<Recipe> recipes = new NodeList<>();

    //---------------------
    // Constructor Methods
    //---------------------

    public BakedGood(String bakedName, String bakedPlace, String bakedDesc, String bakedPicture) {
        setBakedName(bakedName);
        setBakedPlace(bakedPlace);
        setBakedDesc(bakedDesc);
        setBakedPicture(bakedPicture);
    }



    //---------------------
    // Setter Methods
    //---------------------

    public void setBakedName(String bakedName) {
        this.bakedName = bakedName;
    }

    public void setBakedPlace(String bakedPlace) {
        this.bakedPlace = bakedPlace;
    }

    public void setBakedDesc(String bakedDesc) {
        this.bakedDesc = bakedDesc;
    }

    public void setBakedPicture(String bakedPicture) {
        this.bakedPicture = bakedPicture;
    }

    //---------------------
    // Getter Methods
    //---------------------

    public String getBakedName() {
        return bakedName;
    }

    public String getBakedPlace() {
        return bakedPlace;
    }

    public String getBakedDesc() {
        return bakedDesc;
    }

    public String getBakedPicture() {
        return bakedPicture;
    }

    public NodeList<Recipe> getRecipes(){
        return recipes;
    }

    //---------------------
    // Handling Methods
    //---------------------

    public void addRecipe(Recipe recipe){
        recipes.addNode(recipe);
    }
    //Method to find the recipe with the most calories in a baked good.
    public Recipe findMostCaloric(){
        Recipe mostCaloric = recipes.temp.contents;
        for(Recipe r : recipes){
            if(r.getTotalCalories() > mostCaloric.getTotalCalories()){
                mostCaloric = r;
            }
        }
        return mostCaloric;
    }

    //Method to find the recipe with the least calories in a baked good.
    public Recipe findLeastCaloric(){
        Recipe leastCaloric = recipes.temp.contents;
        for(Recipe r : recipes){
            if(r.getTotalCalories() < leastCaloric.getTotalCalories()){
                leastCaloric = r;
            }
        }
        return leastCaloric;
    }

    @Override
    public String toString() {
        return bakedName + " originating from " + bakedPlace + " is " + bakedDesc + " Picture: " + bakedPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BakedGood bakedGood = (BakedGood) o;
        return Objects.equals(bakedName, bakedGood.bakedName) && Objects.equals(bakedPlace, bakedGood.bakedPlace) && Objects.equals(bakedDesc, bakedGood.bakedDesc) && Objects.equals(bakedPicture, bakedGood.bakedPicture) && Objects.equals(recipes, bakedGood.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bakedName, bakedPlace, bakedDesc, bakedPicture, recipes);
    }
}
