package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.BakedGood;

public class BakedGoodUpdateController {
    public TextField nameField,pictureField,countryField;
    public TextArea descriptionField;
    public ChoiceBox<BakedGood> bakedChoice;
    public static BakedGoodUpdateController bakedGoodUpdateController;
    public void initialize() {bakedGoodUpdateController=this;}

    //---------------------
    // Handling Methods
    //---------------------

    //Method to fill all fields in the update baked good scene with the chosen baked goods data.
    public void fillInformation() {
        if(!bakedChoice.getSelectionModel().isEmpty()) {
            nameField.setText(bakedChoice.getSelectionModel().getSelectedItem().getBakedName());
            pictureField.setText(bakedChoice.getSelectionModel().getSelectedItem().getBakedPicture());
            countryField.setText(bakedChoice.getSelectionModel().getSelectedItem().getBakedPlace());
            descriptionField.setText(bakedChoice.getSelectionModel().getSelectedItem().getBakedDesc());
        }
    }

    //Method to populate the bakedChoice ChoiceBox with all baked goods.
    public void populateBakedGoodChoiceBox() {
        bakedChoice.getItems().clear();
        for(BakedGood b : BakeryApplication.bakedList.getHashTable()){
            if(b != null){
                bakedChoice.getItems().add(b);
            }
        }
    }

    //Method that allows the user to edit the existing data in a baked good with new data.
    public void updateBakedGood(ActionEvent event) {
             BakeryApplication.bakedList.delete(bakedChoice.getSelectionModel().getSelectedItem(),bakedChoice.getSelectionModel().getSelectedItem().getBakedName());
             BakedGood baked = new BakedGood(nameField.getText(),countryField.getText(),descriptionField.getText(),pictureField.getText());
             BakeryApplication.bakedList.add(baked, baked.getBakedName());
             nameField.clear();
             countryField.clear();
             descriptionField.clear();
             pictureField.clear();
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
}
