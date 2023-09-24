package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.BakedGood;
import models.Ingredient;
import utils.HashTable;
import utils.NodeList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IngredientController {
    public static IngredientController ingredientController;
    public ListView<Ingredient> ingredientMainList;
    public void initialize() {ingredientController=this;}

    //---------------------
    // Handling Methods
    //---------------------

    //Method to display all ingredients in the ingredientMainList Listview.
    public void updateIngredientListView() {
        ingredientMainList.getItems().clear();
        //The method goes through each entry in the ingredient hashtable and if the entry isn't null it is added to the ingredientMainList Listview.
        for (Ingredient i: BakeryApplication.ingredientHashTable.getHashTable()) {
            if(i != null){
                ingredientMainList.getItems().add(i);
            }
        }
    }

    //TODO
    //Method to delete all ingredients
    public void deleteAllIngredients() {

    }

    //Method to remove an ingredient by the user selecting an ingredient from the ingredientMainList Listview.
    public void removeIngredient(MouseEvent mouseEvent) {
        BakeryApplication.ingredientHashTable.delete(ingredientMainList.getSelectionModel().getSelectedItem(), ingredientMainList.getSelectionModel().getSelectedItem().getName());
        BakeryApplication.reloadContents();
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

    //Method to allow the user to change to the add ingredient scene.
    public void addIngredientPage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene3);
    }

    //Method to allow the user to change to the update ingredient scene.
    public void updateIngredientPage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene10);
    }


    //---------------------
    // Persistence Methods
    //---------------------

    //The load method uses the XStream component to read all the objects from the xml file stored on the hard disk. The read objects are loaded into the associated Hashtable.
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Ingredient.class};

        //setting up the XStream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("ingredients.xml"));
        BakeryApplication.ingredientHashTable = (HashTable<Ingredient>) in.readObject();
        in.close();
    }

    //The save method uses the XStream component to write all the objects in the Hashtable to the xml file stored on the hard disk.
    public void save() throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("ingredients.xml"));
            out.writeObject(BakeryApplication.ingredientHashTable);
            out.close();
        } catch (Exception e) {
            System.out.println("Error writing this file : " + e);
        }
    }
}
