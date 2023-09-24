package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import models.Ingredient;
import models.IngredientQuantity;
import models.Recipe;
import utils.Node;
import utils.NodeList;

import java.util.Comparator;

public class SearchView1Controller <L>{
    public  static SearchView1Controller searchView1Controller;
    public ListView<Object> searchResults;
    public TextField searchTerm;
    public RadioButton sortAlphabetically;
    public RadioButton sortByCalorie;
    public Button searchButton;
    public ChoiceBox<String> searchType;
    public ChoiceBox<String> searchBy;
    public void initialize(){
        searchView1Controller = this;
    }

    //---------------------
    // Handling Methods
    //---------------------

    //Method to populate the searchType ChoiceBox with the options Baked Good, Ingredient Pool, and Recipe.
    public void populateSearchTypeChoiceBox(){
        searchType.getItems().clear();
        searchType.getItems().add("Baked Good");
        searchType.getItems().add("Ingredient Pool");
        searchType.getItems().add("Recipe");
    }

    //Method that takes the selected search type and returns the appropriate search options.
    public void populateSearchByChoiceBox(){
        if(searchType.getSelectionModel().getSelectedItem() != null && searchType.getSelectionModel().getSelectedItem().equals("Recipe")) {
            searchBy.getItems().clear();
            searchBy.getItems().addAll("Steps", "Contains Ingredient");
        } else if (searchType.getSelectionModel().getSelectedItem() != null) {
            searchBy.getItems().clear();
            searchBy.getItems().addAll("Name", "Calories less than", "Calories greater than");
        }
    }

    //Method that takes the selected search type and returns the appropriate search options.
    public void choiceBoxSelected(MouseEvent mouseEvent) {
        if(searchType.getSelectionModel().getSelectedItem() != null){
            populateSearchByChoiceBox();
        }
    }

    //Method to allow the user to search for any item in the application.
    public void search(ActionEvent event) {
        String type = searchType.getSelectionModel().getSelectedItem();
        String by = searchBy.getSelectionModel().getSelectedItem();
        String term = searchTerm.getText();
        Comparator<BakedGood> bakedGoodByName = (a, b) -> a.getBakedName().compareTo(b.getBakedName());
        Comparator<BakedGood> bakedGoodByCalories = (a, b) -> a.findLeastCaloric().getTotalCalories() - b.findLeastCaloric().getTotalCalories();
        Comparator<Ingredient> ingredientByName = (a, b) -> a.getName().compareTo(b.getName());
        Comparator<Ingredient> ingredientByCalories = (a, b) -> a.getCalPer100Unit() - b.getCalPer100Unit();
        Comparator<Recipe> recipeByCalories = (a, b) -> a.getTotalCalories()-b.getTotalCalories();

        //Method to search baked goods by name.
        if(type.equals("Baked Good") && by.equals("Name")){
            NodeList<L> hits = searchBakedGoodsByName(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                System.out.println(hits.getLastNode());
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByName);
                populateSearchResults(hits);
            //Or sorted by total calories.
            } else if(sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByCalories);
                populateSearchResults(hits);
            }

        //Method to search baked goods less than specified calories.
        } else if(type.equals("Baked Good") && by.equals("Calories less than")){
            NodeList<L> hits = searchBakedGoodsCaloriesLess(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByName);
                populateSearchResults(hits);
            //Or sorted by total calories.
            } else if (sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByCalories);
                populateSearchResults(hits);
            }

        //Method to search baked goods greater than specified calories.
        } else if (type.equals("Baked Good") && by.equals("Calories greater than")){
            NodeList<L> hits = searchBakedGoodsCaloriesGreater(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByName);
                populateSearchResults(hits);
            //Or sorted by total calories.
            } else if (sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), bakedGoodByCalories);
                populateSearchResults(hits);
            }

        //Method to search ingredients by name.
        } else if(type.equals("Ingredient Pool") && by.equals("Name")){
            NodeList<L> hits = searchIngredientsName(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByName);
                populateSearchResults(hits);
            //Or sorted by calories.
            } else if(sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByCalories);
                populateSearchResults(hits);
            }

        //Method to search ingredients less than specified calories.
        } else if(type.equals("Ingredient Pool") && by.equals("Calories less than")){
            NodeList<L> hits = searchIngredientsCaloriesLess(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByName);
                populateSearchResults(hits);
            //Or sorted by calories.
            } else if(sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByCalories);
                populateSearchResults(hits);
            }

        //Method to search ingredients greater than specified calories.
        } else if(type.equals("Ingredient Pool") && by.equals("Calories greater than")){
            NodeList<L> hits = searchIngredientsCaloriesGreater(term);
            //The results can be sorted alphabetically.
            if(sortAlphabetically.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByName);
                populateSearchResults(hits);
            //Or sorted by calories.
            } else if(sortByCalorie.isSelected()){
                hits.sort(hits.head, hits.getLastNode(), ingredientByCalories);
                populateSearchResults(hits);
            }

        //Method to search recipes for specific steps.
        } else if(type.equals("Recipe") && by.equals("Steps")){
            NodeList<L> hits = searchRecipeSteps(term);
            //The results are sorted by total calories.
            if (sortByCalorie.isSelected()) {
                hits.sort(hits.head, hits.getLastNode(), recipeByCalories);
                populateSearchResults(hits);
            }

        //Method to search recipes for specific ingredients.
        } else if(type.equals("Recipe") && by.equals("Contains Ingredient")){
            NodeList<L> hits = searchRecipeIngredients(term);
            //The results are sorted by total calories.
            if (sortByCalorie.isSelected()) {
                hits.sort(hits.head, hits.getLastNode(), recipeByCalories);
                populateSearchResults(hits);
            }
        }
    }

    //Method to allow the user to search for a baked good by name from all baked goods.
    public NodeList<L> searchBakedGoodsByName(String term){
        NodeList<L> hits = new NodeList<>();

        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if (b != null){
                if(b.getBakedName().toLowerCase().contains(term.toLowerCase())){
                    hits.addNode((L) b);
                }
            }
        }
        return hits;
    }

    //Method to allow the user to search for a baked good with calories less than specified from all baked goods.
    public  NodeList<L> searchBakedGoodsCaloriesLess(String term){
        NodeList<L> hits = new NodeList<>();
        int calorie = Integer.parseInt(term);

        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null && b.findLeastCaloric().getTotalCalories() <= calorie){
                hits.addNode((L) b);
            }
        }
        return hits;
    }

    //Method to allow the user to search for a baked good with calories greater than specified from all baked goods.
    public  NodeList<L> searchBakedGoodsCaloriesGreater(String term){
        NodeList<L> hits = new NodeList<>();
        int calorie = Integer.parseInt(term);

        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null && b.findLeastCaloric().getTotalCalories() >= calorie){
                hits.addNode((L) b);
            }
        }
        return hits;
    }

    //Method to allow the user to search for an ingredient by name from all ingredients.
    public NodeList<L> searchIngredientsName(String term){
        NodeList<L> hits = new NodeList<>();

        for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
            if(i != null && i.getName().toLowerCase().contains(term.toLowerCase())){
                hits.addNode((L) i);
            }
        }
        return hits;
    }

    //Method to allow the user to search for an ingredient with calories less than specified from all ingredients.
    public NodeList<L> searchIngredientsCaloriesLess(String term){
        NodeList<L> hits = new NodeList<>();
        int calorie = Integer.parseInt(term);

        for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
            if(i != null && i.getCalPer100Unit() <= calorie){
                hits.addNode((L) i);
            }
        }
        return hits;
    }

    //Method to allow the user to search for an ingredient with calories greater than specified from all ingredients.
    public NodeList<L> searchIngredientsCaloriesGreater(String term){
        NodeList<L> hits = new NodeList<>();
        int calorie = Integer.parseInt(term);

        for(Ingredient i : BakeryApplication.ingredientHashTable.getHashTable()){
            if(i != null && i.getCalPer100Unit() >= calorie){
                hits.addNode((L) i);
            }
        }
        return hits;
    }

    //Method to allow the user to search for a specific step from all recipes.
    public NodeList<L> searchRecipeSteps(String term){
        NodeList<L> hits = new NodeList<>();

        for(BakedGood b : BakeryApplication.bakedList.getHashTable()) {
            if (b != null) {
                for (Recipe r : b.getRecipes()) {
                    for (String str : r.getSteps()) {
                        if (str.toLowerCase().contains(term.toLowerCase())) {
                            hits.addNode((L) b);
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return hits;
    }

    //Method to allow the user to search for an ingredient from all recipes.
    public NodeList<L> searchRecipeIngredients(String term){
        NodeList<L> hits = new NodeList<>();

        for(BakedGood bakedGood : BakeryApplication.bakedList.getHashTable()){
            if(bakedGood != null){
                for(Recipe recipe : bakedGood.getRecipes()){
                    for(IngredientQuantity ingredientQuantity: recipe.getIngredients()){
                        if(ingredientQuantity.contents().toLowerCase().contains(term.toLowerCase())){
                            hits.addNode((L) bakedGood);
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return hits;
    }

    //Method to return the results of the search to the searchResults ListView.
    public  void populateSearchResults(NodeList<L> list){
        searchResults.getItems().clear();
        for(L l : list){
            searchResults.getItems().add(l);
        }
    }

    //---------------------
    // View Change Methods
    //---------------------

    //Method to allow the user to return to the home menu from this scene.
    public void home(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }
}
