package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import utils.NodeList;
import utils.Utils;

public class BakedGoodAddController {
    public TextField name, country, picture;
    public TextArea description;

    //---------------------
    // Handling Methods
    //---------------------

    //Method to add a brand-new baked good to the hashtable.
    public void addBakedGood(ActionEvent event) {
        //If statement checks if the values entered into the fields are the correct type.
            BakedGood bakedGood = new BakedGood(name.getText(), country.getText(), description.getText(), picture.getText());
        if ((isValidNameValue(name.getText())) && (isValidCountryValue(country.getText())) && (isValidDescriptionValue(description.getText()))) {
            System.out.println(name.getText() + " originating from " + country.getText() + " is " + description.getText() + " Picture: " + picture.getText());
            BakeryApplication.bakedList.add(bakedGood, bakedGood.getBakedName());
            BakeryApplication.bakedList.displayHashTable();
            BakeryApplication.reloadContents();

            //When this method finishes it clears the fields in the JavaFX so the user can add another one.
            name.clear();
            country.clear();
            picture.clear();
            description.clear();

            //If this fails the system will print out an error message.
        } else {
            System.out.println("Add failed, please try again.");
        }
    }


    //---------------------
    // Validation Methods
    //---------------------

    //Method to ensure the baked good name value only consists of letters.
    public static boolean isValidNameValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //Method to ensure the baked good country only consists of letters.
    public static boolean isValidCountryValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //Method to ensure the baked good description value only consists of letters.
    public static boolean isValidDescriptionValue(String value) {
        String expression = "^[a-zA-Z ]*$";
        return value.matches(expression);
    }

    //---------------------
    // View Change Methods
    //---------------------

    //Method to allow the user to return to the home menu from this scene.
    public void home(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }

    //Method to allow the user to change to the add baked good scene.
    public void addBakedGood(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene2);
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
